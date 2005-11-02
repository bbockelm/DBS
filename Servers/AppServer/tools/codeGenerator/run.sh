#!/bin/sh
python2 GenCPPFromSQL.py 
cp ObjectLayerTables.cpp  ../../src/objectlayer/
cp ObjectLayerTables.hpp ../../include/
cp RowNSchemaBinding.cpp ../../src/objectlayer/
cp TableFactory.cpp ../../src/objectlayer/
cp NameMaper.hpp ../../include/
cp ClientAPIData.hpp ../../include/
cp ClientAPIData.cpp ../../src/clientlayer/

echo "Do the Other.impl Manullay"
