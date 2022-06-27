#! /bin/sh

#Setup arguments
cmd=$1
db_username=$2
db_password=$3

#Start docker
sudo systemctl status docker || systemctl start docker

#Check container status
docker container inspect jrvs-psql
container_status=$?

#Do create|stop|start
case $cmd in
  create)

  if [ $container_status -eq 0 ]; then
		echo 'Container already exists'
		exit 1
	fi

  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi

  #Create the container
	docker volume create pgdata
	docker run --name "$db_username" -e POSTGRES_PASSWORD="$db_password" -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
	exit $?
	;;

  start|stop)

  if [ $container_status -ne 0 ]; then
  	echo 'Container has not been created'
  	exit 1
  fi

  #Start or stop the container
	docker container "$cmd" jrvs-psql
	exit $?
	;;

  #Other cases
  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac

exit 0