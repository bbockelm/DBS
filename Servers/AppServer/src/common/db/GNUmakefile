#CC=g++
#CFLAG=-c
#CFLAGS=-Wno-deprecated
#INCLUDE=-I$(ODBCHOME)/include/ -I$(DBSHOME)/include/
#SOURCES=ResultSet.cpp DBManagement.cpp DBException.cpp
SOURCES := $(wildcard *.cpp)
OBJECTS=$(SOURCES:.cpp=.o)

all: $(OBJECTS)
	
.cpp.o:
	$(CC) $(CFLAG) $(CFLAGS) $(INCLUDE) $< -o $@

clean:
	rm -rf *.o 

