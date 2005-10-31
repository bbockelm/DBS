#CC=g++
#CFLAG=-c
#CFLAGS=-Wno-deprecated -Wno-implicit
#INCLUDE=-I$(ODBCHOME)/include/ -I$(LOG4CXX)/include/ -I$(DBSHOME)/include/ 
SOURCES := $(wildcard *.cpp)
OBJECTS=$(SOURCES:.cpp=.o)

all: $(OBJECTS)
	
.cpp.o:
	$(CC) $(CFLAG) $(CFLAGS)  $(INCLUDE) $< -o $@

clean:
	rm -rf *.o
