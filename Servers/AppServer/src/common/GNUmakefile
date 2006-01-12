SUBDIRS =  conf db log

.PHONY: subdirs $(SUBDIRS)

all: $(SUBDIRS)

$(SUBDIRS): 
	$(MAKE) -C $@

clean:
	for d in  conf db log; do (cd $$d; $(MAKE) clean ;) done




