#!/bin/sh
#
#  A simple script to generate deployable
#  schema for MySQLdatabase
#  from DBS-NeXtGen-MySQL.sql file generated from
#  {CVSROOT}/DBS/Schema/Description/DBS-NeXtGen-MySQL.druid 
#  using DRUID Tool 
#
#  Written 12/08/2006
#  M. Anzar Afaq (anzar@fnal[.NOSPAM]gov
#
#
SchemaVersion=v00_00_02
#
ddl_file=DBS-NeXtGen-MySQL_DEPLOYABLE.sql
#
#
#
#  execution starts here
#
rm -f DBS-NeXtGen-MySQL.sql.TMP.*
#
date=`date +%m%d%Y%H%M%S`
if [ -f $ddl_file ]; then
   mv ${ddl_file}  ${ddl_file}.SAVED.${date}
   echo "Saved current schema file to ${ddl_file}.SAVED.${date}"
fi
#
#
echo
echo "   Inserting auto_increment for mysql"
cat DBS-NeXtGen-MySQL.sql | sed -e "s%ID                    int%ID                    int not null auto_increment%g" > DBS-NeXtGen-MySQL.sql.TMP.1
cat DBS-NeXtGen-MySQL.sql.TMP.1 | sed -e "s%);%) ENGINE = InnoDB ;%g" > DBS-NeXtGen-MySQL.sql.TMP.2
cat DBS-NeXtGen-MySQL.sql.TMP.2 | sed -e "s%/%;%g" > DBS-NeXtGen-MySQL.sql.TMP.3
cat DBS-NeXtGen-MySQL.sql.TMP.3 >> $ddl_file

echo "-- =========== TRIGGERS FOR CreationDate =============================="  >> $ddl_file
table_list=`cat DBS-NeXtGen-MySQL.sql|grep "CREATE TABLE" | awk '{print $3}'`
for atable in $table_list; do
   echo   >> $ddl_file
   echo "CREATE TRIGGER TR_TS_${atable} BEFORE INSERT ON ${atable}"  >> $ddl_file
   echo "FOR EACH ROW SET NEW.CreationDate = NOW();"  >> $ddl_file
done
echo  >> $ddl_file
echo "-- ======================================================================"  >> $ddl_file
echo "-- Initialize status tables There can be better ways to do it ( laters ) "  >> $ddl_file
echo "-- ======================================================================"  >> $ddl_file
echo  >> $ddl_file
echo "INSERT INTO SchemaVersion(SchemaVersion, CreationDate) values ('$SchemaVersion', NOW());"  >> $ddl_file
echo "INSERT INTO AnalysisDSStatus (Status, CreationDate) VALUES ('NEW', NOW());"  >> $ddl_file
echo "INSERT INTO FileStatus (Status, CreationDate) VALUES ('VALID', NOW()), ('INVALID', NOW()), ('MERGED', NOW()), ('PROMOTED', NOW());"  >> $ddl_file
echo "INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('VALID', NOW()), ('INVALID', NOW()), ('PROMOTED', NOW());"  >> $ddl_file
echo "INSERT INTO FileType(Type, CreationDate) VALUES ('EVD', NOW()) ;"  >> $ddl_file
echo "INSERT INTO AnalysisDSType(Type, CreationDate) VALUES ('TEST', NOW());"  >> $ddl_file
echo "INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('TEST', NOW());"  >> $ddl_file
echo "commit;"  >> $ddl_file

echo "   Deploy DBS-NeXtGen-MySQL_DEPLOYABLE.sql to MySQL"
echo "   mysql -u username -ppassword  ...  < DBS-NeXtGen-MySQL_DEPLOYABLE.sql"
echo

