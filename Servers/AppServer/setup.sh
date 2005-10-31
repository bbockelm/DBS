#export ODBCHOME=/home/sekhri/apps/unixodbc/
export ODBCHOME=/home/anzar/devDBS/driver/unixodbc/
#export LOG4CXX=/home/sekhri/apps/log4cxx
export LOG4CXX=/home/anzar/SoftRepo/log4cxx-0.9.7/log4cxx/
export DBSHOME=$PWD
export ORACLE_HOME=/home/anzar/DBS-ORACLE/oracle-10.2.0.1/
export ORACLE_ODBC_HOME=/home/anzar/devDBS/oracle_odbc_driver-0.5.5
export LD_LIBRARY_PATH=$ODBCHOME/lib:$LOG4CXX/lib:$ORACLE_HOME/lib:$ORACLE_ODBC_HOME/lib:$LD_LIBRARY_PATH
export PYTHONINCLUDE=/usr/include/python2.3/
export PATH=/home/sekhri/swig/bin:$PATH
export PYTHONPATH=$DBSHOME/interface/Python:/home/anzar/devDBS/DMS/DBS/prototypes/proto_0/python:$PYTHONPATH
echo "ADD To PYTHON PATH DMS/DBS/prototypes/proto_0/python"
DBSCONFIG=$DBSHOME/etc/server.conf

