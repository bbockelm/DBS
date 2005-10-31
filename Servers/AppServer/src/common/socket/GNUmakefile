CC=g++
CFLAG=-c
CFLAGS=-Wno-deprecated
INCLUDE=-I$(DBSHOME)/include/
SOURCES=ServerSocket.cpp Socket.cpp SocketException.cpp
OBJECTS=$(SOURCES:.cpp=.o)
all: $(OBJECTS)

.cpp.o:
	$(CC) $(CFLAG) $(CFLAGS) $(INCLUDE) $< -o $@

clean:
	rm -rf *.o 

