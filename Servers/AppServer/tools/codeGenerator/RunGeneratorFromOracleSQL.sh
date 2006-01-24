#!/bin/sh
#
if [ $# -lt 1 ]; then
   echo "Not Enough Args provide"
   echo "USAGE: $0 <SchemaFilesPath> "
   exit 1
fi
#  Read Parameters
SchemaFilesPath=$1
#SchemaFilesPath=$PWD/LatestSQL
#
#
if [ ! -f DBSOracle.sql ]; then
  echo "Need to Invoke the Sql file merger" 
  sqlFiles=`cat $SchemaFilesPath/Oracle.sql | sed -e "s%@%%g"`
  for asqlfile in $sqlFiles; do
    echo "Adding...$SchemaFilesPath/$asqlfile"
    cat $SchemaFilesPath/$asqlfile >> DBSOracle.sql
  done
fi
# 
#
cp processSQL.py processSQL.py.ORIGINAL
cp processSQL.py.ORACLE processSQL.py
#
echo "Invoking code generator".
#
python2 GenCPPFromSQL.py
#
cp ObjectLayerTables.cpp  ../../src/objectlayer/
cp ObjectLayerTables.hpp ../../include/
cp RowNSchemaBinding.cpp ../../src/objectlayer/
cp TableFactory.cpp ../../src/objectlayer/
cp NameMaper.cpp ../../src/objectlayer/
#cp ClientAPIData.hpp ../../include/
#cp ClientAPIData.cpp ../../src/clientlayer/
cp TemplateInstances.cpp ../../src/objectlayer/


#
