#!/bin/sh

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne 5 ]; then
  echo 'Requires psql_host, psql_port, db_name, psql_user and psql_password arguments in the given order.'
  exit 1
fi

lscpu_out=$(lscpu)
hostname=$(hostname -f)
vmstat_with_timestamp=$(vmstat -t)
memory_info=$(free -kt)

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | awk '{print $0}' | sed -n -e 's/^Model name: //p' | xargs)
cpu_mhz=$(echo "$lscpu_out" | awk '{print $0}' | sed -n -e 's/^CPU MHz: //p' | xargs)
l2_cache=$(echo "$lscpu_out" | awk '{print $0}' | sed -n -e 's/^L2 cache: //p' | awk '{print substr($0, 1, length($0)-1)}' | xargs)
total_mem=$(echo "$memory_info" | grep "^Total:" | awk '{print $2}' | xargs)
timestamp=$(echo "$vmstat_with_timestamp" | awk 'NR>2 {print $18" "$19}' | xargs)

insert_statement="INSERT INTO host_info (host_name, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, "timestamp")
                  VALUES ('$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp');"

export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_statement"
exit $?
