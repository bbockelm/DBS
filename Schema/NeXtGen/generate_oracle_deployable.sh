#!/bin/sh
#
#  A simple script to deploy schema in a oracle database
#  Written 10/25/2006
#  M. Anzar Afaq (anzar@fnal[.NOSPAM]gov
#
oracle_home=/Tools/DBS-ORACLE/oracle-10.2.0.1
oracle_user=cms_dbs_????
oracle_passwd=?????????
oracle_db=devdb10
#
SchemaVersion=v00_00_04
#
ddl_file=DBS-NeXtGen-Oracle_DEPLOYABLE.sql
#
#
tokenize() {
index_on_toks=`python -c "instr=\"$1\"
instr = instr.split(',')
for astr in instr:
  print astr+'\n'
"`
}
#
#  execution starts here
#
seq_ddl=seq_ddl.tmp
trig_ddl=trig_ddl.tmp
stamp_trig=trig_ts_ddl.tmp
rm -f $seq_ddl
rm -f $trig_ddl
#
date=`date +%m%d%Y%H%M%S`
if [ -f $ddl_file ]; then
   mv ${ddl_file}  ${ddl_file}.SAVED.${date}
   echo "Saved current schema file to ${ddl_file}.SAVED.${date}"
fi
#
# TEXT to CLOB
cat DBS-NeXtGen-Oracle.sql|sed -e "s%TEXT%CLOB%g" > DBS-NeXtGen-Oracle.sql.clob
mv DBS-NeXtGen-Oracle.sql.clob DBS-NeXtGen-Oracle.sql
# BIGINT to int
cat DBS-NeXtGen-Oracle.sql | sed -e "s%BIGINT UNSIGNED%int%g" > DBS-NeXtGen-Oracle.sql.nobig
mv DBS-NeXtGen-Oracle.sql.nobig DBS-NeXtGen-Oracle.sql
#
#
echo "-- ====================================================" >> $ddl_file
echo "-- DDL file generated by $0 on $date" > $ddl_file
echo "-- ====================================================" >> $ddl_file
# list of tables in lower case
table_list=`cat DBS-NeXtGen-Oracle.sql|grep "CREATE TABLE"| awk -FTABLE '{print $2}' | tr '[A-Z]' '[a-z]'`
echo "-- ====================================================" >> $ddl_file
for atable in $table_list; do
   echo "create sequence seq_$atable ;"  >> $seq_ddl
   #echo   >> $seq_ddl
   echo "-- ====================================================" >> $trig_ddl
   echo "-- AUTO INC TRIGGER FOR $atable.ID using SEQ seq_$atable" >> $trig_ddl
   echo >> $trig_ddl
   echo " CREATE OR REPLACE TRIGGER "${atable}_TRIG" before insert on "$atable"    for each row begin     if inserting then       if :NEW."ID" is null then          select seq_$atable.nextval into :NEW."ID" from dual;       end if;    end if; end;"   >> $trig_ddl
   echo "/" >> $trig_ddl
   echo >> $trig_ddl
   echo "-- ====================================================" >> $stamp_trig
   echo "-- LastModified Time Stamp Trigger" >> $stamp_trig
   echo >> $stamp_trig
   echo "CREATE OR REPLACE TRIGGER TRTS${atable} BEFORE INSERT OR UPDATE ON ${atable}" >> $stamp_trig
   echo "FOR EACH ROW" >> $stamp_trig
   echo "BEGIN" >> $stamp_trig
   echo "  :NEW.LASTMODIFICATIONDATE := SYSTIMESTAMP;" >> $stamp_trig
   echo "END;" >> $stamp_trig
   echo "/" >> $stamp_trig
   echo >> $stamp_trig
done
#
echo "-- ====================================================" >> $ddl_file
# Add the rest of DDL
# Along with changing the TIMESTAMP Format to ORACLE (PL/SQL) format
#cat DBS-NeXtGen-Oracle.sql| tee |grep --invert-match "CREATE INDEX" >> $ddl_file

cat DBS-NeXtGen-Oracle.sql| tee |grep --invert-match "CREATE INDEX" |sed -e "s%TIMESTAMP DEFAULT 0%TIMESTAMP DEFAULT SYSTIMESTAMP%g" | sed -e "s%TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP%TIMESTAMP DEFAULT SYSTIMESTAMP%g" >> $ddl_file

#
#
# add the index statements
#
echo "-- === generated index statements ==============" >> $ddl_file 
index_entry_list=`cat DBS-NeXtGen-Oracle.sql|grep "CREATE INDEX"|awk -FON '{print $2}'`
for index_entry in $index_entry_list; do
    table_name=`echo $index_entry | awk -F\( '{print $1}'`
    index_on_str=`echo $index_entry | awk -F\( '{print $2}' | awk -F\) '{print $1}'`
    tokenize $index_on_str
    
    for ix in $index_on_toks; do
       index_name=`echo ix_${table_name}_${ix}| tr '[A-Z]' '[a-z]'`
       echo "create index $index_name on $table_name ($ix);" >> $ddl_file
    done
done
echo "-- ====================================================" >> $ddl_file 
echo >> $ddl_file
#
echo "-- SEQUENCES ==========================================" >> $ddl_file
cat $seq_ddl >> $ddl_file
echo "-- AUTO INC Table.ID TRIGGERS =========================" >> $ddl_file
echo >> $ddl_file
cat $trig_ddl >>  $ddl_file
#
echo "-- TIME STAMP TRIGGERS ================================" >> $ddl_file
echo >> $ddl_file
cat $stamp_trig >>  $ddl_file
#
#
echo "-- Set the Schema Version -- " >> $ddl_file
echo "INSERT INTO SchemaVersion(SCHEMAVERSION, CREATIONDATE) values ('${SchemaVersion}', SYSTIMESTAMP);" >> $ddl_file
echo "-- Pre Fill some information into tables ---------" >> $ddl_file
echo "INSERT INTO AnalysisDSStatus (Status) VALUES ('NEW');" >> $ddl_file
echo "INSERT INTO FileStatus (Status) VALUES ('VALID');" >> $ddl_file
echo "INSERT INTO FileStatus (Status) VALUES ('INVALID');" >> $ddl_file
echo "INSERT INTO FileStatus (Status) VALUES ('MERGED');" >> $ddl_file
echo "INSERT INTO FileStatus (Status) VALUES ('PROMOTED');" >> $ddl_file
echo "INSERT INTO ProcDSStatus (Status) VALUES ('VALID');" >> $ddl_file
echo "INSERT INTO ProcDSStatus (Status) VALUES ('INVALID');" >> $ddl_file
echo "INSERT INTO ProcDSStatus (Status) VALUES ('PROMOTED');" >> $ddl_file
echo "INSERT INTO FileType(Type) VALUES ('EVD') ;" >> $ddl_file
echo "INSERT INTO AnalysisDSType(Type) VALUES ('TEST');" >> $ddl_file
echo "INSERT INTO PrimaryDSType  (Type) VALUES ('VALID');" >> $ddl_file
echo "INSERT INTO Person(Name, DistinguishedName, ContactInfo, CreationDate) Values ('DBSUSER', 'NODN', 'WH', SYSTIMESTAMP);" >> $ddl_file
#
echo "commit;" >> $ddl_file
#
echo
echo
echo "GENERATED $ddl_file"
echo
echo "setup Oracle client environment, for example:"
echo
echo "         source ${oracle_home}/oraenv.sh"
echo
echo "Remover OLDER tables, ARE You SURE ?????, Use this:"
echo
echo "         sqlplus ${oracle_user}/${oracle_passwd}@${oracle_db} < OracleReset.sql"
echo
echo "Verify and then use following commands to deploy:"
echo
echo "         sqlplus ${oracle_user}/${oracle_passwd}@${oracle_db} < $ddl_file"
echo
echo
echo "Make sure you check SchemaVersion at the Bottom of this file"
echo "Current version will be set to ${SchemaVersion} "
echo
echo
echo


