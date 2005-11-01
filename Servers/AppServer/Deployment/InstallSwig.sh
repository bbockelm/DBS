#!/bin/sh
if [ "$1" == "" ]; then
   echo "No Commandline argument"
   echo "USAGE: $0 <install-dir>"
   exit 1 
fi
echo "Staring SWIG Installation in $1"
DBSDependDir=$1
mkdir -p $DBSDependDir
cd $DBSDependDir
wget http://cogent.dl.sourceforge.net/sourceforge/swig/swig-1.3.27.tar.gz
tar -zxf swig-1.3.27.tar.gz
cd swig-1.3.27
./configure --prefix=$DBSDependDir/swig
make 
make install
cd ..
rm -rf swig-1.3.27  swig-1.3.27.tar.gz
echo "swig is installed in $DBSDependDir/swig"
echo "Please set SWIG_HOME=$DBSDependDir/swig in your environment"
