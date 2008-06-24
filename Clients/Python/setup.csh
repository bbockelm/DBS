setenv CLIENTHOME $PWD
setenv PYTHONPATH ${CLIENTHOME}:${PYTHONPATH}
setenv DBS_CLIENT_CONFIG $PWD/DBSAPI/dbs.config
setenv DBSCMD_HOME $CLIENTHOME/DBSAPI
chmod +x $DBSCMD_HOME/dbsCommandLine.py
alias dbs '$DBSCMD_HOME/dbsCommandLine.py -c '

