export LOG4CXX=/home/sekhri/apps/log4cxx
export ODBCHOME=/home/sekhri/apps/unixodbc/
export ORACLE_ODBC_HOME=/home/sekhri/apps/oracle/oracle_odbc_driver
export DBSHOME=$PWD
export ORACLE_HOME=/home/sekhri/apps/oracle/oracle-10.2.0.1
export SWIG_HOME=/home/sekhri/apps/swig/

export GSOAPHOME=/home/sekhri/apps/gsoap/
export LD_LIBRARY_PATH=$ODBCHOME/lib:$LOG4CXX/lib:$ORACLE_HOME/lib:$ORACLE_ODBC_HOME/lib:$LD_LIBRARY_PATH
export PATH=$SWIG_HOME/bin:$GSOAPHOME/bin:$PATH
export PYTHONINCLUDE=/usr/include/python2.3/
export DBSCONFIG=$DBSHOME/etc/server.conf
export PATH=$SWIG_HOME/bin:$PATH
export PYTHONPATH=$DBSHOME/interface/Python:$DBSHOME/../../Clients/PythonAPI:$PYTHONPATH


