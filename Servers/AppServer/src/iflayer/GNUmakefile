SOURCES := $(wildcard *.cpp)
OBJECTS=$(SOURCES:.cpp=.o)
BINDIR=../../bin
dirs := ../bizlogiclayer  ../objectlayer ../common/conf ../common/db ../common/log
ALLOBJECTS := $(foreach dir,$(dirs),$(wildcard $(dir)/*.o))

all: $(OBJECTS) exe
        
.cpp.o:
	$(CC) $(CFLAG) $(CFLAGS)  $(INCLUDE) $< -o $@

exe:
	$(CC) $(CFLAGS) $(LDFLAGS) $(INCLUDE) $(ALLOBJECTS) $(OBJECTS) -o ${BINDIR}/server.exe -lpthread -lgsoap++

clean:
	rm -rf *.o ${BINDIR}/server.exe

