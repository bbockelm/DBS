source conf.sh
while [ 1 ]
do
	totalNoOfEntries=`cat $timeLog |grep real|wc|awk '{print $1}'`
	if [ "$totalNoOfEntries" -eq "$1" ]
	then
		#echo "All threads completed ..."
		break
	else
		#echo "Some threads still working"
		sleep 5
	fi
done
#echo $totalNoOfEntries
./calculate_average.sh
