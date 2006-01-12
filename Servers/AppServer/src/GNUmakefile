SUBDIRS = bizlogiclayer clientlayer comlayer objectlayer common

.PHONY: subdirs $(SUBDIRS)

all: $(SUBDIRS)

$(SUBDIRS): 
	$(MAKE) -C $@

clean:
	for d in bizlogiclayer clientlayer comlayer objectlayer common; do (cd $$d; $(MAKE) clean ;) done




