#!/bin/sh
if [ "$1" == "" ]; then
   echo "No Commandline argument"
   echo "USAGE: $0 <install-dir>"
   exit 1
fi
echo "Staring oracle_odbc_driver install in $1"
DBSDependDir=$1

mkdir -p $DBSDependDir
cd $DBSDependDir

echo "oracle_odbc_driver can ONLY be installed "
echo "if you have done unixODBC and ORACLE Client installations first"

#export CVSROOT=:pserver:anonymous@cdcvs.fnal.gov:/cvs/cd_read_only
#export CVS_RSH=ssh
#cvs -d `echo $CVSROOT | awk -F@ '{print $1":anoncvs\@"$2}'` login
#cvs -Q co oracle_odbc_driver

# Following is temporary hack, once code in CVS is updated, will use lines above
wget http://home.fnal.gov/~anzar/oracle_odbc_driver.tgz
tar zxf oracle_odbc_driver.tgz


cd oracle_odbc_driver
#./autogen.sh
echo "Doing ./configure --prefix=$DBSDependDir/oracleodbcinstall --enable-trace=no --with-oraclehome=$ORACLE_HOME  --with-unixODBC=$ODBCHOME"
./configure --prefix=$DBSDependDir/oracleodbcinstall --enable-trace=no --with-oraclehome=$ORACLE_HOME  --with-unixODBC=$ODBCHOME
make 
make install
cd ..
rm -rf oracle_odbc_driver oracle_odbc_driver.tgz
echo "oracle_odbc_driver is installed in $DBSDependDir/oracleodbcinstall"
echo "Please set ORACLE_ODBC_HOME=$DBSDependDir/oracleodbcinstall in your environment"



