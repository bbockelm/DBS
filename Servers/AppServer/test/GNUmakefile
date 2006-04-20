CC=g++
CFLAG=-c
CFLAGS=-Wno-deprecated -Wno-implicit
LDFLAGS=-L$(ODBCHOME)/lib/ -lodbc -L$(LOG4CXX)/lib/ -llog4cxx  -L$(GSOAPHOME)/lib -lpthread -lgsoap++
INCLUDE=-I$(ODBCHOME)/include/ -I$(LOG4CXX)/include/ -I$(DBSHOME)/include/ -I$(GSOAPHOME)/include

dirs := ../src/bizlogiclayer ../src/clientlayer ../src/comlayer ../src/objectlayer ../src/common/conf ../src/common/db ../src/common/log ../src/iflayer
ALL_OBJECTS := $(foreach dir,$(dirs),$(wildcard $(dir)/*.o))

#SOCKETOBJECTS=../cpp/common/socket/Socket.o ../cpp/common/socket/ServerSocket.o ../cpp/common/socket/SocketException.o
#GSSOBJECTS=../cpp/common/gss/GSS.o ../cpp/common/gss/GSSException.o
DBOBJECTS:= $(wildcard ../src/common/db/*.o)
LOGOBJECTS:=../src/common/log/Log.o

#MAIN_CLIENT=ClientTest.cpp
#$MAIN_CLIENT=GeneratedClientTest.cpp
#MAIN_CLIENT=test.cpp
#MAIN_CLIENT=CrabClient.cpp
MAIN_BIZ=manTest.cpp
MAIN_DB=dbTest.cpp
MAIN_OB=objTest2.cpp
MAIN_MSG=msgTest.cpp
MAIN_CRAB=CrabClient.cpp

EXE_BIZ=$(MAIN_BIZ:.cpp=)
EXE_DB=$(MAIN_DB:.cpp=)
EXE_OB=$(MAIN_OB:.cpp=)
EXE_MSG=$(MAIN_MSG:.cpp=)
EXE_CRAB=$(MAIN_CRAB:.cpp=)
EXE_CLIENT=$(MAIN_CLIENT:.cpp=)

all: biz db ob crab
#all: biz db ob msg client
biz: $(EXE_BIZ)
db: $(EXE_DB)
ob: $(EXE_OB)
msg: $(EXE_MSG)
crab: $(EXE_CRAB)
client: $(EXE_CLIENT)
	
$(EXE_BIZ):
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(SOCKETOBJECTS) $(GSSOBJECTS) $(ALL_OBJECTS) -o $@ $(MAIN_BIZ)
$(EXE_DB):
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(DBOBJECTS) -o $@ $(MAIN_DB)
$(EXE_OB):
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(ALL_OBJECTS) -o $@ $(MAIN_OB) 
$(EXE_MSG):
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(COMOBJECTS) $(ALL_OBJECTS)  -o $@ $(MAIN_MSG)
$(EXE_CRAB):
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(ALL_OBJECTS)  -o $@ $(MAIN_CRAB)

$(EXE_CLIENT):
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(SOCKETOBJECTS) $(DBOBJECTS) $(LOGOBJECTS) $(ALL_OBJECTS) -o $@ $(MAIN_CLIENT)

clean:
	rm -rf  $(EXE_BIZ) $(EXE_DB) $(EXE_OB) $(EXE_CLIENT)
