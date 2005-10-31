CC	=	g++
INCPATH	=	. # Put the path to Talos.
LINK	=	g++

#TARGETS	=	read_config

#all: $(TARGETS)

#$(TARGETS): % : %.o
#	$(LINK) -o $@ $<

#%.o : %.cpp
#	$(CC) -I$(INCPATH) -c -o $@ $<

#clean:
#	rm -f $(TARGETS) $(TARGETS:%=%.o)


SOURCES := $(wildcard *.cpp)
OBJECTS=$(SOURCES:.cpp=.o)

all: $(OBJECTS)
        
.cpp.o:
	$(CC) $(CFLAG) $(CFLAGS)  $(INCLUDE) $< -o $@

clean:
	rm -rf *.o

