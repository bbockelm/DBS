#CC=g++
#CFLAG=-c
#CFLAGS=-Wno-deprecated
INCLUDE=-I$(LOG4CXX)/include/ -I$(DBSHOME)/include/
SOURCES=Log.cpp
OBJECTS=$(SOURCES:.cpp=.o)
all: $(OBJECTS)

.cpp.o:
	$(CC) $(CFLAG) $(CFLAGS) $(INCLUDE) $< -o $@

clean:
	rm -rf *.o

