timestamp=`date +%m%y%d%M%S`
uuid=`uuidgen`
dirName="tmp/${timestamp}_${uuid}"
#echo $dirName 
mkdir -p $dirName
srcUrl=$1
dstUrl=$2
dataset=$3
log=$4
dsLog="LOG.txt"
cp ../../UserExamples/dbsMigrateWithParents.py $dirName
cd $dirName
(time python2.4 dbsMigrateWithParents.py $srcUrl $dstUrl $dataset > $dsLog) 2>> $log
#(time python2.4 dbsMigrateWithParents.py $srcUrl $dstUrl $dataset ) &> $log
cd -
