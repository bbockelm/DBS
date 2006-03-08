export LOG4CXX=/home/anzar/DBSDependTest/log4cxx
export ODBCHOME=/home/anzar/DBSDependTest/unixODBC
export ORACLE_ODBC_HOME=/home/anzar/DBSDependTest/oracleodbcinstall
export DBSHOME=$PWD
export ORACLE_HOME=/home/anzar/DBS-ORACLE/oracle-10.2.0.1/
export SWIG_HOME=/home/anzar/DBSDependTest/swig

export GSOAPHOME=/home/sekhri/apps/gsoap/
export LD_LIBRARY_PATH=$ODBCHOME/lib:$LOG4CXX/lib:$ORACLE_HOME/lib:$ORACLE_ODBC_HOME/lib:$LD_LIBRARY_PATH
export PATH=$SWIG_HOME/bin:$GSOAPHOME/bin:$PATH
export PYTHONINCLUDE=/usr/include/python2.3/
export DBSCONFIG=$DBSHOME/etc/server.conf
export PATH=$SWIG_HOME/bin:$PATH
export PYTHONPATH=$DBSHOME/interface/Python:$DBSHOME/../../Clients/PythonAPI:$PYTHONPATH


