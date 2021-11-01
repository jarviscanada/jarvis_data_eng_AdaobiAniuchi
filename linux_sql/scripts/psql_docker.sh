#!/bin/sh

cmd=$1
db_username=$2
db_password=$3

sudo systemctl status docker || systemctl start docker

container_status=$(docker container inspect jrvs-psql | grep -oP "(\"ExitCode\":\s\d)" | awk '{print $2}' | xargs)

case $cmd in
  create)

  if [ -n "$container_status" ] && [ "$container_status" -eq 0 ]; then
		echo 'Container already exists'
		exit 1
	fi

  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi

	docker volume create pgdata

	export PGPASSWORD=$db_password
	export PGUSERNAME=$db_username

	docker run --name jrvs-psql -e POSTGRES_PASSWORD="$PGPASSWORD" -e POSTGRES_USER="$PGUSERNAME" -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
	exit 0
	;;

  start|stop)
  if [ -z "$container_status" ]; then
    echo 'Container has not yet been created'
    exit 1
  fi

	docker container "$cmd" jrvs-psql
	exit 0
	;;

  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac
