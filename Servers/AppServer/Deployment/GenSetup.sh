#!/bin/sh

if [ "$ORACLE_HOME" == "" ]; then
   echo "ORACLE_HOME not set cannot continue"
   echo "In not done already, run InstallOracleClient first" 
   exit 1
fi


if [ "$1" == "" ]; then
   echo "No Commandline argument"
   echo "USAGE: $0 <install-dir>"
   exit 1
fi

echo "Install Dir is : $1"

DBSDependDir=$1

echo "Generating example setup_dbs.sh"
cat > "setup_dbs.sh" <<EOF
export LOG4CXX=$DBSDependDir/log4cxx
export ODBCHOME=$DBSDependDir/unixODBC
export ORACLE_ODBC_HOME=$DBSDependDir/oracleodbcinstall
export GSOAPHOME=$DBSDependDir/gSOAP
export SWIG_HOME=$DBSDependDir/swig
export WSDL2PYHOME=$DBSDependDir/wsdl2py
export DBSHOME=\$PWD
export APPSERVER_PORT=27983
export ORACLE_HOME=$ORACLE_HOME
export LD_LIBRARY_PATH=\$ODBCHOME/lib:\$LOG4CXX/lib:\$ORACLE_HOME/lib:\$ORACLE_ODBC_HOME/lib:\$GSOAPHOME/lib:\$LD_LIBRARY_PATH
export PYTHONINCLUDE=/usr/include/python2.3/
export DBSCONFIG=\$DBSHOME/etc/server.conf
export PATH=\$SWIG_HOME/bin:\$GSOAPHOME/bin:\$WSDL2PYHOME/bin:\$DBSHOME/bin:\$PATH
#echo "Check Proper swig version (1.3.27 or later) added to path and proper python include to PYTHONINCLUDE"
#echo "ADD To PYTHONPATH DMS/DBS/prototypes/proto_0/python"
export PYTHONPATH=\$DBSHOME/interface/Python:\$DBSHOME/../../Clients/PythonAPI:\$WSDL2PYHOME:\$PYTHONPATH
EOF

