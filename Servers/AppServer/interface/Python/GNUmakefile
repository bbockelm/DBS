
IF=dbsclient.i
WRAP=$(IF:.i=_wrap.cxx)
WRAPOBJECT=$(WRAP:.cxx=.o)
SHARED=_$(IF:.i=.so)

dirs := $(DBSHOME)/src/bizlogiclayer $(DBSHOME)/src/clientlayer $(DBSHOME)/src/comlayer \
        $(DBSHOME)/src/objectlayer $(DBSHOME)/src/common/conf $(DBSHOME)/src/common/db $(DBSHOME)/src/common/log
ALLOBJS := $(foreach dir,$(dirs),$(wildcard $(dir)/*.o))


all: swig wrap shared
swig: 
	$(SWIG) -python -c++ -w401,317 $(IF)
wrap: 
	$(CC) $(CFLAG) $(CFLAGS) $(PYINCLUDE) $(INCLUDE) $(WRAP) -o $(WRAPOBJECT)
shared: 
	$(CC) -shared $(WRAPOBJECT) $(PYINCLUDE) $(INCLUDE) $(LDFLAGS) $(ALLOBJS) -o $(SHARED)

clean:
	rm -rf  *.cxx dbsclient.py $(WRAPOBJECT) $(SHARED) $(WRAP)
