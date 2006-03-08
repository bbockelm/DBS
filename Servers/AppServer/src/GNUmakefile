SUBDIRS = bizlogiclayer objectlayer common iflayer

.PHONY: subdirs $(SUBDIRS)

all: $(SUBDIRS)

$(SUBDIRS): 
	$(MAKE) -C $@

clean:
	for d in bizlogiclayer objectlayer common iflayer; do (cd $$d; $(MAKE) clean ;) done




