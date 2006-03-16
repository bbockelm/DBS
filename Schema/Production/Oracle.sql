set feedback off sqlprompt ''
prompt Loading DBS schema to &_user@&_connect_identifier

@OracleRev.sql
@OracleCMS.sql
@OracleHistory.sql

@OracleApps.sql
@OracleCoreData.sql
@OracleFiles.sql
