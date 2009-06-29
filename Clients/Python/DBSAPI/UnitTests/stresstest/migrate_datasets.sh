source conf.sh
uuid=`uuidgen`
if [ -f $timeLog ]
then
	mv $timeLog "${timeLog}_${uuid}"
fi

migrateDataset()
{
	start=1
	count=0
	for i in $(cat $datasets) ; 
	do 
		if [ "$start" -eq "$1" ] 
		then
			if [ "$count" -lt "$2" ]
			then
				((++count))
				#echo "Inside.. Migrate."
				echo $i; 
				./migrate_one_dataset.sh $srcUrl $dstUrl $i $timeLog &
			fi
		else ((++start))
		fi
	done
}
#migrateDataset 1 1
migrateDataset 2 10
#migrateDataset 2 20
#migrateDataset 2 30
