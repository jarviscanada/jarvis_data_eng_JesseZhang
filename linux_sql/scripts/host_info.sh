#! /bin/sh

#Setup arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Validate the number of arguments
if [ $# -ne 5 ]; then
  echo "Please enter arguments for hostname, port, database'name, username, password"
  exit 1
fi

#Store hardware specifications into variables
specs=`lscpu`

#Parse variables and retrieve information
hostname=$(hostname -f)
cpu_number=$(echo "$specs"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$specs"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$specs"  | egrep  "^Model[[:blank:]]name:" | awk '{ for (i=3; i<=NF; i++) print $i }' | xargs)
cpu_mhz=$(echo "$specs"  | egrep  "^CPU[[:blank:]]MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$specs"  | egrep  "^L2[[:blank:]]cache:" | awk '{print $3}' | grep -o '[[:digit:]]*' | xargs)
total_mem=$(cat /proc/meminfo | egrep "^MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(date +%F_%T | sed 's/_/ /')

#Construct the INSERT statement
insert_stmt="INSERT INTO host_info(id, hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp)
VALUES(DEFAULT, '$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp')"

#Set password environment variable
export PGPASSWORD=$psql_password

#Execute the INSERT statement
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?