export LOG4CXX=/data/Depend//log4cxx
export ODBCHOME=/data/Depend//unixODBC
export ORACLE_ODBC_HOME=/data/Depend//oracleodbcinstall
export GSOAPHOME=/data/Depend//gSOAP
export SWIG_HOME=/data/Depend//swig
export WSDL2PYHOME=/data/Depend//wsdl2py
export ZSIHOME=/data/Depend/ZSI-1.7
export DBSHOME=$PWD
export APPSERVER_PORT=27983
export ORACLE_HOME=/data/Depend/oracle-10.2.0.1/
export LD_LIBRARY_PATH=$ODBCHOME/lib:$LOG4CXX/lib:$ORACLE_HOME/lib:$ORACLE_ODBC_HOME/lib:$GSOAPHOME/lib:$LD_LIBRARY_PATH
export PYTHONINCLUDE=/usr/include/python2.3/
export DBSCONFIG=$DBSHOME/etc/server.conf
export PATH=$SWIG_HOME/bin:$GSOAPHOME/bin:$WSDL2PYHOME/bin:$DBSHOME/bin:$PATH
#echo "Check Proper swig version (1.3.27 or later) added to path and proper python include to PYTHONINCLUDE"
#echo "ADD To PYTHONPATH DMS/DBS/prototypes/proto_0/python"
export PYTHONPATH=$DBSHOME/interface/Python:$DBSHOME/../../Clients/PythonAPI:$WSDL2PYHOME:$ZSIHOME:$PYTHONPATH

