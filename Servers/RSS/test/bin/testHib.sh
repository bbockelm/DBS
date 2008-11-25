cd ../..
unalias ls
CLASSPATH=.
for i in $(ls $PWD/lib/*.jar) ; do echo $i; CLASSPATH=$CLASSPATH:$i; done
#CLASSPATH=/home/sekhri/java/sdk/lib/javaee.jar:/home/sekhri/java/sdk/lib/webservices-tools.jar:$CLASSPATH
#export CLASSPATH
alias ls='ls --color'


CLASSPATH=$PWD/build/WEB-INF/classes:$CLASSPATH
cd test/bin
#cd ../../build/WEB-INF/classes
java -cp $CLASSPATH gov.fnal.rss.dm.test.Test
#cd -

