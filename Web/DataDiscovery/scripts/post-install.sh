#!/bin/sh

if [ $# -ne 2 ]; then
   echo -e "\nUsage: post-install.sh <host> <port>\n"
   exit
fi

_host=$1
if [ -n `echo $_host | grep "http://"` ] ; then
host=$_host
fi
if [ -n `echo $_host | grep "https://"` ] ; then
host=$_host
else
host="http://"`echo $_host | sed "s,http://,,g"`
fi
port=$2
DIR=$DDHOME

# Make log dirs
mkdir -p $DIR/Logs

# Fix template files
cat $DDHOME/Templates/CherryServer3.conf | sed "s/\$port/$port/g" > $DIR/CherryServer3.conf
/bin/cp -f $DDHOME/Templates/DBSDD.conf $DIR
cat >> $DIR/DBSDD.conf << EOF
#
# DO NOT EDIT, AUTO-GENERATED SETTINGS
#
# If you want your Data Discovery site visible, make appropriate change here and setup URL
### you may change URL to be in a form $host:$port
# URL=$host
# PORT=$port
LOGGERDIR=$DDHOME/Logs
EOF

cat > $DIR/DBParam << EOF
######################################################################
# This is example of DBParam file, please make appropriate changes
# for your system. 
# The "Section" represents a name of your DBS instance
# The "Database" is DBS DB in use at your local site
# The "Host" represents MySQL host:port in use, it can have optional 
# port number, e.g. localhost:3307 (default port is 3306)
# The "Url" is DBS server URL
Section                 dbs_testbed
Interface               MYSQL
Database                dbsdb
AuthDBUsername          root
AuthDBPassword          cmsdbs
Host                    127.0.0.1:3307
Url                     https://$host:8448/DBS/servlet/DBSServlet
######################################################################
EOF

# Fix masthead from WEBTOOLS
#cat $DDHOME/WEBTOOLS/Common/Templates/masthead.tmpl | \
#sed "s,\$context.CmdLineArgs.opts.baseUrl,$host:$port,g" > \
#$DIR/js/masthead.js
