set feedback off sqlprompt ''
prompt Loading DBS schema to &_user@&_connect_identifier

prompt
prompt Loading schema revision, CMS info and object history
@@OracleRev.sql
@@OracleCMS.sql
@@OracleHistory.sql

prompt
prompt Loading application description
@@OracleApps.sql

prompt
prompt Loading core data description
@@OracleCoreData.sql

prompt
prompt Load block and file description
@@OracleFiles.sql

prompt
prompt DBS schema loaded
prompt
