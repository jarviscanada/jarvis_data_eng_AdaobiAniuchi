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

hostname=$(hostname -f)
vmstat_t=$(vmstat -t)
vmstat_mb=$(vmstat --unit M)
vmstat_d=$(vmstat -d)
df_cmd=$(df -BM /)

memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_mb" | awk 'NR>2 {print $15}' | xargs)
cpu_kernel=$(echo "$vmstat_mb" | awk 'NR>2 {print $14}' | xargs)
disk_io=$(echo "$vmstat_d" | grep "^sda" | awk '{print $10}' | xargs)
disk_available=$(echo "$df_cmd" | awk 'NR>1 {print substr($4, 1, length($4)-1)}' | xargs)
timestamp=$(echo "$vmstat_t" | awk 'NR>2 {print $18" "$19}' | xargs)

host_id="(SELECT id FROM host_info WHERE host_name='$hostname')";
insert_statement="INSERT INTO host_usage("timestamp", host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
             VALUES('$timestamp', $host_id, $memory_free, $cpu_idle, $cpu_kernel, $disk_io, $disk_available);"

export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_statement"
exit $?