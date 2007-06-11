#!/bin/bash
#
if [ $# != 4 ]; then
     echo "Usage  : fixContext.sh <dbtype> <dbhostname> <dbusername> <dbpassword>"
     echo ""
     echo "dbtypes: MYSQL, ORACLE"
     echo ""
     echo "Example: fixContext.sh MYSQL localhost test testpw"
     exit
fi


server=$1
dbhostname=$2
dbusername=$3
dbpassword=$4
TOMCAT_WEBAPP=$VO_CMS_SW_DIR/$SCRAM_ARCH/external/apache-tomcat/5.5.20/webapps/
WARFILE=DBS.war

# Make a temporary directory, go there for isolated operation
rm -rf JARFIX
mkdir -p JARFIX
cp $TOMCAT_WEBAPP/DBS.war JARFIX/DBSSRC.war
cd JARFIX

if [ $server == "MYSQL" ]; then

cat > context.xml << EOF
<Context path="/servlet/DBSServlet" docBase="DBSServlet" debug="5" reloadable="true" crossContext="true">
       <Resource name="jdbc/dbs"
               auth="Container"
               type="javax.sql.DataSource"
               maxActive="0"
               maxIdle="1"
               maxWait="-1"
               username="__insert_username__"
               password="__insert_password__"
               driverClassName="org.gjt.mm.mysql.Driver"
               url="jdbc:mysql://__insert_hostname__:3306/dbs_new_era_v25?autoReconnect=true"/>
<SupportedSchemaVersion schemaversion="DBS_1_0_4" />
<SupportedClientVersions clientversions="DBS_1_0_4" />
<DBSBlockConfig maxBlockSize="2000000000000" maxBlockFiles="100" />
</Context>
EOF

elif [ $server == "ORACLE" ]; then

cat > context.xml << EOF
<Context path="/servlet/DBSServlet" docBase="DBSServlet" debug="5" reloadable="true" crossContext="true">
       <Resource name="jdbc/dbs"
               auth="Container"
               type="javax.sql.DataSource"
               maxActive="28"
               maxIdle="1"
               maxWait="-1"
               username="__insert_username__"
               password="__insert_password__"
               removeAbandoned="true"
               removeAbandonedTimeout="100"
               validationQuery="select * from dual;"
               driverClassName="oracle.jdbc.driver.OracleDriver"
               url="jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = cmsr1-v.cern.ch)(PORT = 10121)) (ADDRESS = (PROTOCOL = TCP)(HOST = cmsr2-v.cern.ch)(PORT = 10121)) (ADDRESS = (PROTOCOL = TCP)(HOST = cmsr3-v.cern.ch)(PORT = 10121)) (ADDRESS = (PROTOCOL = TCP)(HOST = cmsr4-v.cern.ch)(PORT = 10121)) (ADDRESS = (PROTOCOL = TCP)(HOST = cmsr5-v.cern.ch)(PORT = 10121)) (ADDRESS = (PROTOCOL = TCP)(HOST = cmsr6-v.cern.ch)(PORT = 10121)) (ADDRESS = (PROTOCOL = TCP)(HOST = cmsr7-v.cern.ch)(PORT = 10121)) (ADDRESS = (PROTOCOL = TCP)(HOST = cmsr8-v.cern.ch)(PORT = 10121)) (ENABLE=BROKEN) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = cmsr.cern.ch) (FAILOVER_MODE = (TYPE = SELECT)(METHOD = BASIC)(RETRIES = 200)(DELAY = 15))))"/>
<SupportedSchemaVersion schemaversion="DBS_1_0_4" />
<SupportedClientVersions clientversions="DBS_1_0_4" />
<DBSBlockConfig maxBlockSize="2000000000000" maxBlockFiles="100" />
</Context>
EOF

else
  echo "Unsupported db type, should be either MYSQL or ORACLE"
  exit
fi

##################################################################
#  Open the war file
jar xf DBSSRC.war
# remove the original, in case user choses same name for target
if [ $? -eq 0 ]; then
	rm -f DBSSRC.war
fi
#
# Insert username, password and hostname
cat context.xml |\
sed -e "s%__insert_username__%$dbusername%g" | \
sed -e "s%__insert_password__%$dbpassword%g" |\
sed -e "s%__insert_hostname__%$dbhostname%g" > context.xml.tmp
mv context.xml.tmp context.xml

#
# Rebuild the war file
cp context.xml META-INF
cp context.xml WEB-INF
jar cf $WARFILE META-INF WEB-INF
#
if [ $? -eq 0 ]; then
        echo "$WARFILE built successfully"
	#Deploy to TOMCAT
	/bin/cp -f $WARFILE $TOMCAT_WEBAPP
	if [ $? -eq 0 ]; then
           echo "Deployment successful"
	else 
           echo "Deployment failed"	
           echo "Please capture error report and send it to"
           echo "cms-dbs-support@cern.ch"
           echo "and you may report a bug into"
           echo "https://savannah.cern.ch/bugs/?func=additem&group=dbs"
	fi
else 
   echo "Unable to create distribution $WARFILE"
   echo "Please capture error report and send it to"
   echo "cms-dbs-support@cern.ch"
   echo "and report a bug into"
   echo "https://savannah.cern.ch/bugs/?func=additem&group=dbs"
fi
#
cd .. # one level up from JARFIX
rm -rf JARFIX

