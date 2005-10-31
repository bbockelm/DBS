#!/bin/sh
python2 GenCPPFromSQL.py 
cp ObjectLayerTables.cpp  ../cpp/objectlayer/
cp ObjectLayerTables.hpp ../include/
cp RowNSchemaBinding.cpp ../cpp/objectlayer/
cp TableFactory.cpp ../cpp/objectlayer/
cp NameMaper.hpp ../include/
cp ClientAPIData.hpp ../include/
cp ClientAPIData.cpp ../cpp/clientlayer/

echo "Do the Other.impl Manullay"
