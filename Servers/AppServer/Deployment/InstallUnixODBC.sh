#!/bin/sh
if [ "$1" == "" ]; then
   echo "No Commandline argument"
   echo "USAGE: $0 <install-dir>"
   exit 1
fi

echo "Starting unixODBS Installation in $1"
DBSDependDir=$1

mkdir -p $DBSDependDir
cd $DBSDependDir

wget http://puzzle.dl.sourceforge.net/sourceforge/unixodbc/unixODBC-2.2.11.tar.gz
tar zxf unixODBC-2.2.11.tar.gz
cd unixODBC-2.2.11
#./autogen.sh
echo "Doing ./configure --prefix=$DBSDependDir/unixODBC"
./configure --prefix=$DBSDependDir/unixODBC
make 
make install
cd ..
rm -rf unixODBC-2.2.11.tar.gz unixODBC-2.2.11
echo "unixODBS is installed in $DBSDependDir/unixODBC"
echo "Please set ODBCHOME=$DBSDependDir/unixODBC in your environment"
echo "Configuration file $DBSDependDir/etc/odbcinst.ini"
echo "Configuration file $DBSDependDir/etc/odbc.ini"
#export ODBCHOME=$DBSDependDir/unixODBC


