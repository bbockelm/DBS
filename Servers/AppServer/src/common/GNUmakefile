#export CC=g++
#export CFLAG=-c
#export CFLAGS=-Wno-deprecated -Wno-implicit
#export INCLUDE=-I$(ODBCHOME)/include/ -I$(LOG4CXX)/include/ -I$(DBSHOME)/include/
all:
	for d in conf db log; do (cd $$d; $(MAKE) all ;) done
clean:
	for d in conf db log; do (cd $$d; $(MAKE) clean ;) done



