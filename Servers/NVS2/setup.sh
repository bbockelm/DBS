unalias ls
#export JAVA_HOME=/usr/java/jdk1.5.0_10
export J2EE_HOME=/home/sekhri/java/sdk
#export J2EE_HOME=/home/sekhri/j2ee/SDK/
export JAVA_HOME=$J2EE_HOME/jdk
export PATH=$JAVA_HOME/bin:$PATH
#export JAVA_HOME=/home/sekhri/j2ee/SDK/jdk/
CLASSPATH=.
#for i in $(ls $PWD/lib/*.jar) ; do echo $i; CLASSPATH=$CLASSPATH:$i; done
#CLASSPATH=/home/sekhri/java/sdk/lib/javaee.jar:/home/sekhri/java/sdk/lib/webservices-tools.jar:$CLASSPATH
export CLASSPATH
alias ls='ls --color'
