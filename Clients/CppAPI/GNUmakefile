export CC=g++
export CFLAG=-c
export CFLAGS=-Wno-deprecated -Wno-implicit
export INCLUDE=-I$(DBSHOME)/include/ -I$(GSOAPHOME)/include
export LDFLAGS=-L$(GSOAPHOME)/lib


SOURCES := $(wildcard *.cpp)
OBJECTS=$(SOURCES:.cpp=.o)

all: $(OBJECTS) exe

.cpp.o:
	$(CC) $(CFLAG) $(CFLAGS)  $(INCLUDE) $< -o $@

exe:
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(OBJECTS) -o client.exe -lpthread -lgsoap++

clean:
	rm -rf *.o client.exe

