setenv CLIENTHOME $PWD
setenv PYTHONPATH ${CLIENTHOME}:${PYTHONPATH}
setenv DBS_CLIENT_CONFIG $PWD/DBSAPI/dbs.config
setenv DBSCMD_HOME $CLIENTHOME/DBSAPI
setenv PATH $PATH:$CLIENTHOME/bin
#chmod +x $DBSCMD_HOME/dbsCommandLine.py
alias dbs 'python $DBSCMD_HOME/dbsCommandLine.py -c '

