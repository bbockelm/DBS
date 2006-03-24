#!/bin/sh
#
if [ "$APPSERVER_PORT" == "" ]; then
   echo "APPSERVER_PORT not set cannot continue..."
   exit 1
fi
hostname=`hostname`
port=$APPSERVER_PORT
#
soapcpp2 -I $GSOAPHOME/include -L ../../interface/Cpp/Interface.hpp
#edit DBS.wsdl and soapProxy.h with perl to change localhost:80 to your desired  hosname:port
cat DBS.wsdl | sed -e "s%localhost:80%$hostname:$port%g" > tmpDBS.wsdl
#cat soapProxy.h | sed -e "s%localhost:80%$hostname:$port%g" > tmpsoapProxy.h
mv tmpDBS.wsdl DBS.wsdl
#mv tmpsoapProxy.h soapProxy.h

cp soap*.cpp ../../src/iflayer
cp soapC.cpp  soapClient.cpp ../../../../Clients/CppAPI/
cp *.h ../../include
#
wsdl2py.py -f DBS.wsdl
python replace.py 
cp DBS.nsmap  ../../include
cp DBS.* ../../../../Clients/PythonAPI/
cp Service* ../../../../Clients/PythonAPI/
#
python genPyClientInterface.py
cp dbsWsApi.py ../../../../Clients/PythonAPI/
cp dbsClientDatastructures.py ../../../../Clients/PythonAPI/
cp SoapApiImpl.cpp ../../src/iflayer/
cp Interface.hpp ../../include/  
#
echo "Code Generation is DONE"

