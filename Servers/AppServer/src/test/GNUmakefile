CC=g++
CFLAG=-c
CFLAGS=-Wno-deprecated -Wno-implicit
LDFLAGS=-L$(ODBCHOME)/lib/ -lodbc -L$(LOG4CXX)/lib/ -llog4cxx -L$(GLOBUS_LOCATION)/lib/ -lglobus_gss_assist_gcc32dbg
INCLUDE=-I$(ODBCHOME)/include/ -I$(LOG4CXX)/include/ -I$(DBSHOME)/include/ -I$(GLOBUS_LOCATION)/include/gcc32dbg/

SOCKETOBJECTS=../common/socket/Socket.o ../common/socket/ServerSocket.o ../common/socket/SocketException.o
GSSOBJECTS=../common/gss/GSS.o ../common/gss/GSSException.o
DBOBJECTS=../common/db/DBManagement.o ../common/db/ResultSet.o ../common/db/DBException.o
LOGOBJECTS=../common/log/Log.o
COMOBJECTS=../comlayer/Message.o ../comlayer/Communicate.o 
IFOBJECTS=../iflayer/Configuration.o
OLOBJECTS=../objectlayer/ObjectLayerException.o ../objectlayer/Util.o ../objectlayer/SQL.o ../objectlayer/BaseSchemaNConstratints.o ../objectlayer/ObjectLayerTables.o  ../objectlayer/RowInterface.o ../objectlayer/TableTemplate.o ../objectlayer/RowNSchemaBinding.o ../objectlayer/TableFactory.o ../objectlayer/SingleTableInterface.o ../objectlayer/MultiTableInterface.o
BIZOBJECTS=../bizlogiclayer/Manager.o ../bizlogiclayer/ECReadManager.o ../bizlogiclayer/DBSDispatcher.o ../bizlogiclayer/PrimaryDatasetManager.o ../bizlogiclayer/PersonManager.o ../bizlogiclayer/ProcessedDatasetManager.o ../bizlogiclayer/AnalysisDatasetManager.o ../bizlogiclayer/PhysicsGroupManager.o ../bizlogiclayer/ECWriteManager.o ../bizlogiclayer/FileManager.o ../bizlogiclayer/AppConfigManager.o ../bizlogiclayer/DatasetProvcManagerParent.o ../bizlogiclayer/DatasetProvcManagerChild.o ../bizlogiclayer/CRABECReadManager.o
MAIN_BIZ=manTest.cpp
MAIN_DB=dbTest.cpp
MAIN_OB=objTest.cpp
MAIN_MSG=msgTest.cpp
EXE_BIZ=$(MAIN_BIZ:.cpp=)
EXE_DB=$(MAIN_DB:.cpp=)
EXE_OB=$(MAIN_OB:.cpp=)
EXE_MSG=$(MAIN_MSG:.cpp=)

all: biz db ob msg
biz: $(EXE_BIZ)
db: $(EXE_DB)
ob: $(EXE_OB)
msg: $(EXE_MSG)
	
$(EXE_BIZ):
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(SOCKETOBJECTS) $(GSSOBJECTS) $(DBOBJECTS) $(COMOBJECTS) $(LOGOBJECTS) $(IFOBJECTS) $(OLOBJECTS) $(BIZOBJECTS) -o $@ $(MAIN_BIZ)
$(EXE_DB):
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE)  $(DBOBJECTS)  -o $@ $(MAIN_DB)
$(EXE_OB):
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(DBOBJECTS) $(LOGOBJECTS) $(IFOBJECTS) $(OLOBJECTS) -o $@ $(MAIN_OB)
$(EXE_MSG):
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(SOCKETOBJECTS) $(GSSOBJECTS) $(COMOBJECTS) $(DBOBJECTS) $(LOGOBJECTS) $(IFOBJECTS) $(OLOBJECTS) $(BIZOBJECTS) -o $@ $(MAIN_MSG)


clean:
	rm -rf  $(EXE_BIZ) $(EXE_DB) $(EXE_OB) $(EXE_MSG)
