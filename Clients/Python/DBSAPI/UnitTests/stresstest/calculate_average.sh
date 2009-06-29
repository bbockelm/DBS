source conf.sh
totalNoOfEntries=`cat $timeLog |grep real|wc|awk '{print $1}'`
#echo "total $totalNoOfEntries"
minAvg=`cat $timeLog |grep real| awk '{print $2}'|awk -Fm '{sum = sum + $1} END {print sum}'| awk -v TOTAL=$totalNoOfEntries '{ avg = $1/TOTAL } END {print avg }'`
secAvg=`cat $timeLog |grep real| awk '{print $2}'|awk -Fm '{print $2}'|awk -Fs '{sum = sum + $1} END {print sum}'| awk -v TOTAL=$totalNoOfEntries '{ avg = $1/TOTAL } END {print avg}'`
tmp=`echo "Average TIME took to complete test is  $minAvg MINS : $secAvg SECS"`
echo $tmp
echo $tmp | mail -s "Stress Test" $email

