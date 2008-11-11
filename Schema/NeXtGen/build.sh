export ANT_HOME=/home/sekhri/apache-ant-1.6.5
export JAVA_HOME=/usr/java/jdk1.5.0_14
export ORACLE_HOME=/home/anzar/DBS-ORACLE/oracle-10.2.0.1
export ANT_OPTS='-Xmx128m -Xms128m'
export LD_LIBRARY_PATH=/home/anzar/DBS-ORACLE/oracle-10.2.0.1/lib
export TNS_ADMIN=/home/anzar/DBS-ORACLE/oracle-10.2.0.1/network/admin
export PATH=$JAVA_HOME/bin:$ANT_HOME/bin:$ORACLE_HOME/bin:$PATH
# By default build.xml CreatSynonyms that we do not use anymore. 
#So following line in NOT REQUIRED EVER
#  NEVER RUN THIS NOW   ------------    ant --noconfig -buildfile build.xml    
ant --noconfig -buildfile build.xml RolesNGrants
ant --noconfig -buildfile build.xml CreateViews


