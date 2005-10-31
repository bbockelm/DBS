CC=g++
CFLAG=-c
CFLAGS=-Wno-deprecated
INCLUDE=-I$(GLOBUS_LOCATION)/include/ -I$(GLOBUS_LOCATION)/include/gcc32dbg/ -I$(DBSHOME)/include/
SOURCES=GSS.cpp GSSException.cpp
OBJECTS=$(SOURCES:.cpp=.o)

all: $(OBJECTS)
	
.cpp.o:
	$(CC) $(CFLAG) $(CFLAGS) $(INCLUDE) $< -o $@

clean:
	rm -rf *.o

