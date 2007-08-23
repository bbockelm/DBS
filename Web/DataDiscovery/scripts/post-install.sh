#!/bin/sh

if [ $# -ne 2 ]; then
   echo -e "\nUsage: post-install.sh <host> <port>\n"
   exit
fi

_host=$1
host="http://"`echo $_host | sed "s,http://,,g"`
port=$2
DIR=$DDHOME

# Make log dirs
mkdir -p $DIR/Logs

# Fix template files
cat $DDHOME/Templates/CherryServer3.conf | sed "s/\$port/$port/g" > $DIR/CherryServer3.conf
cat >> $DIR/DBSDD.conf << EOF
#
# DO NOT EDIT, AUTO-GENERATED SETTINGS
#
URL=$host:$port
LOGGERDIR=$DDHOME/Logs
MASTHEAD=$host:$port/js/masthead.js
EOF

# Fix masthead from WEBTOOLS
cat $DDHOME/WEBTOOLS/Common/Templates/masthead.tmpl | \
sed "s,\$context.CmdLineArgs.opts.baseUrl,$host:$port,g" > \
$DIR/js/masthead.js
