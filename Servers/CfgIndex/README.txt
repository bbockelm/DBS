Procedure for generating the index files and addint to the index DB

(examples here assume working in area under location of .py src files.)

1. hash values are extracted from DBS with the procedure described in gethash.py Use the following steps with already configured DBS command line interface:

#get dump of config info for particular set of data
$ dbs lsa --algopattern=/*/CMSSW_1_8_4 >cmssw_1_8_4-algorithms.txt

# make *.cfg.hash files which have the hash values in them
$python ../gethash.py cmssw_1_8_4-algorithms.txt

2. Get the cfg files from the release. This assumes you have the release set up
export CMSCFG=/uscmst1/prod/sw/cms/slc4_ia32_gcc345/cms/cmssw/CMSSW_1_8_4/src/Configuration/Spring08Production/data
cp $CMSCFG/*.cfg .

3. Run the indexer
Syntax: ./cfg2index.py path file1 file2 ...
Example:
../cfg2index.py . `ls *.cfg`

4. load into the database. (only works where "path" is "." and you run in directory where the .hash and .index files are located. This loads for mysql, need to write similar one for oracle.
Syntax: ./cfgindex2db_mysql.py path file1 file2 ...
Example:
../cfgindex.py . `*.cfg`

Note: If the schema is not already there , need to make it using the
sql/create_mysql.sql to set it up.

To make the web service work, use the webapps  
