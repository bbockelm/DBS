MySQL:

   Deploy DBS-NeXtGen-MySQL_DEPLOYABLE.sql to MySQL
   Setup mysql environment, databasdes 
   mysql -u username -ppassword  ...  < DBS-NeXtGen-MySQL_DEPLOYABLE.sql

ORACLE:

1.   
   Setup Oracle client environment, for example:

         source /Tools/DBS-ORACLE/oracle-10.2.0.1/oraenv.sh
2.
   Remover OLDER tables, ARE You SURE ?????, Use this:

         sqlplus cms_dbs_????/?????????@devdb10 < OracleReset.sql

3.
   Deploy DBS-NeXtGen-Oracle_DEPLOYABLE.sql to Oracle

         sqlplus cms_dbs_user_whatever/passwd@DBINSTANCE < DBS-NeXtGen-Oracle_DEPLOYABLE.sql


4.
  Run the **index**.sql file to create missing indexes.

5. 
  edit build.property file to give proper password and so on.

6.
  run build.sh to create proper roles and private synonyms.


#############################  Expert ONLY Options #########################

The DRUID Generated File is:
    
       DBS-NeXtGen-MySQL.sql and DBS-NeXtGen-Oracle.sql

To re-generate deployable scripts:

For MySQL use 
     ./generate_mysql_deployable.sh

For Oracle use
     ./generate_oracle_deployable.sh


