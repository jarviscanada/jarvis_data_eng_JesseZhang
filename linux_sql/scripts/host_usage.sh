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

#Store inital info into variables
vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)
disk_filesystem=$(df -BM /)

#Parse varaibles and retrieve infomation
memory_free=$(echo "$vmstat_mb" | awk 'NR == 3 {print $4}' | xargs )
cpu_idle=$(echo "$vmstat_mb" | awk 'NR == 3 {print $15}' | xargs )
cpu_kernel=$(echo "$vmstat_mb" | awk 'NR == 3 {print $14}' | xargs )
disk_io=$(vmstat -d | awk 'NR == 3 {print $10}' | xargs )
disk_available=$(echo "$disk_filesystem" | awk 'NR == 2 {print $4}' | grep -o '[[:digit:]]*' | xargs )

timestamp=$(date +%F_%T | sed 's/_/ /')

#Find the matching id in host_info table
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

#Construct the INSERT statement
insert_stmt="INSERT INTO host_usage(timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
VALUES('$timestamp', $host_id, '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available')"

#Set password environment variable
export PGPASSWORD=$psql_password

#Execute the INSERT statement
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?