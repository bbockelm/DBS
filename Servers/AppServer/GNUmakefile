export CC=g++
export CFLAG=-c
export CFLAGS=-Wno-deprecated -Wno-implicit
export INCLUDE=-I$(ODBCHOME)/include/ -I$(LOG4CXX)/include/ -I$(DBSHOME)/include/ -I$(GSOAPHOME)/include
export LDFLAGS=-L$(ODBCHOME)/lib/ -lodbc -L$(LOG4CXX)/lib/  -llog4cxx -L$(GSOAPHOME)/lib 
export PYINCLUDE= -I$(PYTHONINCLUDE)
export SWIG=$(SWIG_HOME)/bin/swig
#export SWIG=/home/sekhri/swig/bin/swig

#dirs := $(DBSHOME)/src/bizlogiclayer $(DBSHOME)/src/clientlayer $(DBSHOME)/src/comlayer \
	$(DBSHOME)/src/objectlayer $(DBSHOME)/src/common/conf $(DBSHOME)/src/common/db $(DBSHOME)/src/common/log
#export ALLOBJS := $(foreach dir,$(dirs),$(wildcard $(dir)/*.o))

#all: cppsource swiginterface
all: cppsource

cppsource:
	cd src; $(MAKE) all
swiginterface:
	cd interface/Python; $(MAKE) all

clean:
	for d in src interface/Python; do (cd $$d; $(MAKE) clean ;) done

