export JAVA_HOME=/usr/java/jdk1.5.0_02/
export CLASSPATH=.
lib=$PWD/lib
for i in $(ls $lib/*.jar ) ; do
	export CLASSPATH=$i:$CLASSPATH
done
export PATH=$JAVA_HOME/bin:$PATH
