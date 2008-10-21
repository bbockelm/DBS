#/bin/sh
export CLIENTHOME=$PWD
export PYTHONPATH=$CLIENTHOME:$PYTHONPATH
export DBS_CLIENT_CONFIG=$PWD/DBSAPI/dbs.config

# Setup the ALIAS and PATH for dbsCommandLine.py
export DBSCMD_HOME=$CLIENTHOME/DBSAPI
#
export PATH=$PATH:$CLIENTHOME/bin
#
# DBS Commandline tool
#
#
#chmod +x $DBSCMD_HOME/dbsCommandLine.py 
#
alias dbs='python $DBSCMD_HOME/dbsCommandLine.py -c '
#
#
#Primary Datasets
#alias dbslsp='$DBSCMD_HOME/dbsCommandLine.py -c lsp'
#Processed Datasets
#alias dbslsd='$DBSCMD_HOME/dbsCommandLine.py -c lsd'
#Algorithms
#alias dbslsa='$DBSCMD_HOME/dbsCommandLine.py -c lsa'
#Files
#alias dbslsf='$DBSCMD_HOME/dbsCommandLine.py -c lsf'
#Blocks
#alias dbslsb='$DBSCMD_HOME/dbsCommandLine.py -c lsb'
#SE
#alias dbslsse='$DBSCMD_HOME/dbsCommandLine.py -c lsse'

