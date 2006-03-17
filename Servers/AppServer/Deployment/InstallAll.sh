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



./InstallGSOAP.sh $1
if [ $? != 0 ]; then
        exit 1
fi

export GSOAPHOME=$DBSDependDir/gSOAP


./InstallWSDL2PY.sh $1
if [ $? != 0 ]; then
        exit 1
fi
export WSDL2PYHOME=$DBSDependDir/wsdl2py


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

./GenSetup.sh $1

mv setup_dbs.sh ..

