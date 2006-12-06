export JAVA_HOME=/usr/java/jdk1.5.0_06/
export ANT_HOME=/home/sekhri/apache-ant-1.6.5
export DBS_HOME=`pwd`
export DBS_CONFIG=$DBS_HOME/etc/context.xml

ret=0

if [ "${DBS_HOME}" == "" ]; then
        echo "Error! Please set your DBS_HOME variable and source this file again"
        ret=1
fi

if [ "${JAVA_HOME}" == "" ]; then
	echo "Error! Please set your JAVA_HOME variable and source this file again"
	ret=1
fi
if [ "${ANT_HOME}" == "" ]; then
	echo "Error! Please set your ANT_HOME variable and source this file again"
	ret=1
fi

if [ $ret == 0 ]; then
	export PATH=${JAVA_HOME}/bin:${ANT_HOME}/bin:$PATH
	alias ant='ant  --noconfig'
fi


