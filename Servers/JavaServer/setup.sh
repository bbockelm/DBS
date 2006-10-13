ret=0
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
