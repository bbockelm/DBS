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

mkdir -p $DBSDependDir

export DBSDependDir=$1
./InstallLog4CXX.sh $1
if [ $? != 0 ]; then
	exit 1
fi
./InstallUnixODBC.sh $1
if [ $? != 0 ]; then
	exit 1
fi

export LOG4CXX=$DBSDependDir/log4cxx
export ODBCHOME=$DBSDependDir/unixODBC

./InstallOracleODBC.sh $1
if [ $? != 0 ]; then
	exit 1
fi
export ORACLE_ODBC_HOME=$DBSDependDir/oracleodbcinstall

./InstallSwig.sh $1
if [ $? != 0 ]; then
	exit 1
fi

export SWIG_HOME=$DBSDependDir/swig
echo "Configuration file $DBSDependDir/etc/odbcinst.ini"
echo "Configuration file $DBSDependDir/etc/odbc.ini"


echo "Generating Example odbcint.ini........"
cat > "$ODBCHOME/etc/odbcinst.ini.example" <<EOF
[oracle]
Description     =
Driver          = $ORACLE_ODBC_HOME/lib/liboraodbc.so
Driver64        = $ORACLE_ODBC_HOME/lib/liboraodbc.so
Setup           = $ORACLE_ODBC_HOME/lib/liboraodbc.so
Setup64         = $ORACLE_ODBC_HOME/lib/liboraodbc.so
UsageCount      = 1
CPTimeout       =
CPReuse         =
EOF

cat > "$ODBCHOME/etc/odbc.ini.example" <<EOF
[OracleTest]
Description     = oracle
Driver          = oracle
Trace           = No
TraceFile       =
DB              = cmsr
USER            = cms_dbs_reader
PASSWORD        = xxxxxxxxxxxxx
EOF

cd ..
echo
echo "Generating example setup_dbs.shn in $PWD"
echo
cat > "setup_dbs.sh" <<EOF
export LOG4CXX=$DBSDependDir/log4cxx
export ODBCHOME=$DBSDependDir/unixODBC
export ORACLE_ODBC_HOME=$DBSDependDir/oracleodbcinstall
export DBSHOME=$PWD
export ORACLE_HOME=$ORACLE_HOME
export SWIG_HOME=$SWIG_HOME
export LD_LIBRARY_PATH=\$ODBCHOME/lib:\$LOG4CXX/lib:\$ORACLE_HOME/lib:\$ORACLE_ODBC_HOME/lib:\$LD_LIBRARY_PATH
export PYTHONINCLUDE=/usr/include/python2.3/
export DBSCONFIG=\$DBSHOME/etc/server.conf
export PATH=\$SWIG_HOME/bin:\$PATH
DBSBASE=`echo \$DBSHOME| awk -FServer '{print $1}'`
PYTHONPATH=\$DBSHOME/interface/Python:\$DBSBASE/prototypes/proto_0/python:\$DBSBASE/prototypes/proto_0/python/lib:\$PYTHONPATH
echo "Check Proper swig version (1.3.27 or later) added to path and proper python include to PYTHONINCLUDE"
EOF
echo
echo "Have a look at CONFIGURE file for Configuration instructions"
echo

