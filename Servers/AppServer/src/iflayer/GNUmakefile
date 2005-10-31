BINDIR=../../bin
CC=g++
CFLAG=-c
CFLAGS=-Wno-deprecated
LDFLAGS=-L$(ODBCHOME)/lib/ -lodbc -lpthread -L$(GLOBUS_LOCATION)/lib/ -lglobus_gss_assist_gcc32dbg -L$(LOG4CXX)/lib/ -llog4cxx
INCLUDE=-I$(GLOBUS_LOCATION)/include/ -I$(GLOBUS_LOCATION)/include/gcc32dbg/ -I$(LOG4CXX)/include/ -I../../include/ -I../common/conf/ -I$(ODBCHOME)/include
SOCKETOBJECTS=../common/socket/Socket.o ../common/socket/ServerSocket.o ../common/socket/SocketException.o
GSSOBJECTS=../common/gss/GSS.o ../common/gss/GSSException.o
LOGOBJECTS=../common/log/Log.o
COMOBJECTS=../comlayer/Message.o ../comlayer/Communicate.o
OLOBJECTS=../objectlayer/ObjectLayerException.o ../objectlayer/Util.o ../objectlayer/SQL.o ../objectlayer/BaseSchemaNConstratints.o ../objectlayer/ObjectLayerTables.o  ../objectlayer/RowInterface.o ../objectlayer/TableTemplate.o ../objectlayer/RowNSchemaBinding.o ../objectlayer/TableFactory.o ../objectlayer/SingleTableInterface.o ../objectlayer/MultiTableInterface.o
BIZOBJECTS=../bizlogiclayer/Manager.o ../bizlogiclayer/ECReadManager.o ../bizlogiclayer/DBSDispatcher.o ../bizlogiclayer/PrimaryDatasetManager.o  ../bizlogiclayer/PersonManager.o ../bizlogiclayer/ProcessedDatasetManager.o ../bizlogiclayer/AnalysisDatasetManager.o ../bizlogiclayer/PhysicsGroupManager.o ../bizlogiclayer/ECWriteManager.o ../bizlogiclayer/FileManager.o ../bizlogiclayer/AppConfigManager.o ../bizlogiclayer/DatasetProvcManagerParent.o ../bizlogiclayer/DatasetProvcManagerChild.o 
DBOBJECTS=../common/db/DBManagement.o ../common/db/ResultSet.o ../common/db/DBException.o
MAIN_S=Server.cpp 
MAIN_C=Client.cpp

#MAIN=cli.cpp
SOURCES=Configuration.cpp DBSThreadManager.cpp
OBJECTS=$(SOURCES:.cpp=.o)


EXECUTABLE=$(MAIN:.cpp=)

EXECUTABLE_S=$(MAIN_S:.cpp=)
EXECUTABLE_C=$(MAIN_C:.cpp=)

all: $(SOURCES) server client

server: $(SOURCES) $(EXECUTABLE_S)
 
client: $(SOURCES) $(EXECUTABLE_C)
	
$(EXECUTABLE_S): $(OBJECTS)
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(SOCKETOBJECTS) $(GSSOBJECTS) $(LOGOBJECTS) $(BIZOBJECTS) $(DBOBJECTS) $(COMOBJECTS) $(OLOBJECTS) $(OBJECTS) -o ${BINDIR}/$@ $(MAIN_S)

$(EXECUTABLE_C): $(OBJECTS)
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(SOCKETOBJECTS) $(GSSOBJECTS) $(LOGOBJECTS) $(BIZOBJECTS) $(DBOBJECTS) $(COMOBJECTS) $(OLOBJECTS) $(OBJECTS) -o ${BINDIR}/$@ $(MAIN_C)

.cpp.o:
	$(CC) $(CFLAG) $(CFLAGS)  $(INCLUDE) $< -o $@
clean:
	rm -rf *.o ${BINDIR}/$(EXECUTABLE_C) ${BINDIR}/$(EXECUTABLE_S)

