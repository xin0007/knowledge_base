#! /bin/bash 

#set -x
# java -Xmx2048m -Djava.library.path=C:\Microsoft  -jar target/simple-metadata-crawler-1.0-SNAPSHOT.jar
CMD="java -jar simple-metadata-crawler-0.1.0.jar"
DATADIR=${DATADIR:-"./data"}
DATE=$(date +%Y-%m-%d)



# url, user, password, file_name_without_extension, optional catalogs regex filter
function crawl
{
	#DATE=$(date +%Y-%m-%d)
	OUTDIR=$DATADIR/$DATE
	mkdir -p $OUTDIR
	OUT_NAME=$OUTDIR"/"$4"_"$DATE
	
    rm -rf $OUT_NAME".log"
	LOG_COMMAND="tee -a $OUT_NAME.log"
	
	echo "Processing $4: $1" | $LOG_COMMAND
	echo $(date +%Y-%m-%d--%H-%M-%S) 2>&1 | $LOG_COMMAND
	START=$(date +%s.%N)
	
	catalogs=${5:-""}
	echo "Catalogs: $catalogs" | $LOG_COMMAND
	export JDBC_PASSWORD="$3" && $CMD --url $1 --catalogs "$catalogs" --user $2  --format json -o $OUT_NAME".json" 2>&1 | $LOG_COMMAND
	export JDBC_PASSWORD=""
	
	END=$(date +%s.%N)
	DIFF=$(echo "$END - $START" | bc)
	echo "Processing $4 ended in $DIFF seconds"  | $LOG_COMMAND
}

# EHR Test
# crawl "jdbc:oracle:thin:@172.16.106.51:1521/ehrtest" "sfsc" "sfsc123" "FCF" -- schema名称

# redmine
# crawl "jdbc:mysql://172.16.226.165:3306" "redmine_read" "redmine_read" "redmine"

# mysql test
# crawl "jdbc:mysql://mysql-lab.fsg.inner:3306" "root" "abc123" "test"

crawl "jdbc:postgresql://172.16.149.11/operation" "itssc_operation" "abc123"