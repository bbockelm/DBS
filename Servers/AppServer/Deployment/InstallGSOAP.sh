#!/bin/sh
if [ "$1" == "" ]; then
   echo "No Commandline argument"
   echo "USAGE: $0 <install-dir>"
   exit 1 
fi
echo "Staring GSOAP Installation in $1"
DBSDependDir=$1
mkdir -p $DBSDependDir
cd $DBSDependDir
wget http://umn.dl.sourceforge.net/sourceforge/gsoap2/gsoap_2.7.6e.tar.gz 
tar -zxf gsoap_2.7.6e.tar.gz
cd gsoap-2.7
./configure --prefix=$DBSDependDir/gSOAP
make 
make install
cd ..
cp gsoap-2.7/soapcpp2/wsdl/*.h $DBSDependDir/gSOAP/include
rm -rf gsoap-2.7 gsoap_2.7.6e.tar.gz
echo "gSOAP is installed in $DBSDependDir/gSOAP"
echo "Please set GSOAPHOME=$DBSDependDir/gSOAP in your environment"

