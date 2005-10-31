#!/bin/sh
if [ "$1" == "" ]; then
   echo "No Commandline argument"
   echo "USAGE: $0 <install-dir>"
   exit 1 
fi
echo "Staring LOG4CXX Installation in $1"
DBSDependDir=$1
mkdir -p $DBSDependDir
cd $DBSDependDir
wget http://apache.tradebit.com/pub/logging/log4cxx/log4cxx-0.9.7.tar.gz
tar zxf log4cxx-0.9.7.tar.gz
rm -f log4cxx-0.9.7.tar.gz
cd log4cxx-0.9.7
./autogen.sh
./configure --prefix=$DBSDependDir/log4cxx
make 
make install
cd ..
rm -rf log4cxx-0.9.7
echo "log4cxx is installed in $DBSDependDir/log4cxx"
echo "Please set LOG4CXX=$DBSDependDir/log4cxx in your environment"
#
#export LOG4CXX=$DBSDependDir/log4cxx

