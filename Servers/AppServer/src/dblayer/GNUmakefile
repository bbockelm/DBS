CC=g++
CFLAG=-c
CFLAGS=-Wno-deprecated
LDFLAGS=-L$(ODBCHOME)/lib/ -lodbc
INCLUDE=-I$(ODBCHOME)/include/ -I$(DBSHOME)/include/
DBOBJECTS=../common/db/DBManagement.o ../common/db/ResultSet.o ../common/db/DBException.o
MAIN=dbtest.cpp
EXECUTABLE=$(MAIN:.cpp=)

all: $(EXECUTABLE)
	
$(EXECUTABLE):
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(DBOBJECTS) -o $@ $(MAIN)
clean:
	rm -rf $(EXECUTABLE)

