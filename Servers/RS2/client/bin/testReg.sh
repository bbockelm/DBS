CLASSPATH=$PWD/../build:../../dm/build:../lib/jbossall-client.jar:$CLASSPATH
java -cp $CLASSPATH gov.fnal.rs.client.test.RegTest cmsdbssrv.cern.ch
