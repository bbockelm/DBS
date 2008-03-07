export JAVA_HOME=/home/cmsdbs/sw/slc3_ia32_gcc323/external/java-jdk/1.5.0.p6
export CLASSPATH=.
lib=$PWD/lib
for i in $(ls $lib/*.jar ) ; do
	export CLASSPATH=$i:$CLASSPATH
done
export PATH=$JAVA_HOME/bin:$PATH

