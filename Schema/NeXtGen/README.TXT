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



#############################  Expert ONLY Options #########################

The DRUID Generated Files are:
    
       DBS-NeXtGen-MySQL.sql and DBS-NeXtGen-Oracle.sql

To re-generate deployable scripts:

For MySQL use 
     ./gen_auto_inc.sh

For Oracle use
     ./deploy_oracle.sh


