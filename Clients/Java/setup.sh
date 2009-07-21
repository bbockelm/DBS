CLASSPATH=.
cd lib
for i in $(ls $PWD/*.jar) ; do
	echo $i
	CLASSPATH=$CLASSPATH:$i
done
export CLASSPATH
cd -
