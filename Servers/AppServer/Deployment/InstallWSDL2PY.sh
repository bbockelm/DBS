#!/bin/sh
if [ "$1" == "" ]; then
   echo "No Commandline argument"
   echo "USAGE: $0 <install-dir>"
   exit 1 
fi
echo "Staring WSDL2PY Installation in $1"
DBSDependDir=$1
mkdir -p $DBSDependDir
cd $DBSDependDir
#wget http://voxel.dl.sourceforge.net/sourceforge/pywebsvcs/ZSI-2.0-rc1.tar.gz
#tar -zxf ZSI-2.0-rc1.tar.gz
#cd ZSI-2.0-rc1
#
wget http://umn.dl.sourceforge.net/sourceforge/pywebsvcs/ZSI-1.7.tar.gz
tar ZSI-1.7.tar.gz
cd ZSI-1.7
python setup.py bdist
cd dist
#tar zxvf ZSI-2.0_rc1.linux-i686.tar.gz 
tar zxvf ZSI-1.7.linux-i686.tar.gz
cd ../..
mkdir wsdl2py
cd wsdl2py
cp -r ../ZSI-1.7/dist/usr/local/bin .
cp -r ../ZSI-1.7/dist/usr//local/lib/python2.4/site-packages/ZSI/ .
#cp -r ../ZSI-2.0-rc1/dist/usr/bin .
#cp -r ../ZSI-2.0-rc1/dist/usr/lib/python2.3/site-packages/ZSI/ .
cd ..
#rm -rf ZSI-2.0-rc1 ZSI-2.0-rc1.tar.gz
echo "wsdl2py is installed in $DBSDependDir/wsdl2py"
echo "Please set GSOAPHOME=$DBSDependDir/wsdl2py in your PYTHONPATH"

