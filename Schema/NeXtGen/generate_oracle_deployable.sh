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
SchemaVersion=DBS_1_0_4
#
ddl_file=DBS-NeXtGen-Oracle_DEPLOYABLE.sql
#
# This is crappy way of setting the time in seconds in Oracle, there are better ways, for now lets do thsi.
unix_time="(select (sysdate - to_date('19700101','YYYYMMDD')) * 86400 from dual)"
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
rm -f $stamp_trig
#
#
dos2unix DBS-NeXtGen-Oracle.sql
#
date=`date +%m%d%Y%H%M%S`
if [ -f $ddl_file ]; then
   mv ${ddl_file}  ${ddl_file}.SAVED.${date}
   echo "Saved current schema file to ${ddl_file}.SAVED.${date}"
fi

#int to integer
cat DBS-NeXtGen-Oracle.sql| sed -e "s%int%integer%g" > DBS-NeXtGen-Oracle.sql.integer
#
# TEXT to CLOB
cat DBS-NeXtGen-Oracle.sql.integer|sed -e "s%TEXT%CLOB%g" > DBS-NeXtGen-Oracle.sql.clob.0
# This puts a STUPID LONGCLOB that should only be CLOB
cat DBS-NeXtGen-Oracle.sql.clob.0|sed -e "s%LONGCLOB%CLOB%g" > DBS-NeXtGen-Oracle.sql.clob
#
# BIGINT to int
cat DBS-NeXtGen-Oracle.sql.clob | sed -e "s%BIGINT UNSIGNED%integer%g" > DBS-NeXtGen-Oracle.sql.nobig.0
#
cat DBS-NeXtGen-Oracle.sql.nobig.0 | sed -e "s%BIGINT%integer%g" > DBS-NeXtGen-Oracle.sql.nobig
#
mv DBS-NeXtGen-Oracle.sql.nobig DBS-NeXtGen-Oracle.sql.tmp.0
#
#
echo "-- ====================================================" >> $ddl_file
echo "-- DDL file generated by $0 on $date" > $ddl_file
echo "-- ====================================================" >> $ddl_file
# list of tables in lower case
table_list=`cat DBS-NeXtGen-Oracle.sql.tmp.0 |grep "CREATE TABLE"| awk -FTABLE '{print $2}' | tr '[A-Z]' '[a-z]'`
echo "-- ====================================================" >> $ddl_file
for atable in $table_list; do
   echo "PROMPT creating sequence seq_$atable ;"  >> $seq_ddl
   echo "create sequence seq_$atable ;"  >> $seq_ddl
   #echo   >> $seq_ddl
   echo "-- ====================================================" >> $trig_ddl
   echo "-- AUTO INC TRIGGER FOR $atable.ID using SEQ seq_$atable" >> $trig_ddl
   echo >> $trig_ddl
   echo "PROMPT AUTO INC TRIGGER FOR Trigger for Table: ${atable}" >> $trig_ddl
   echo " CREATE OR REPLACE TRIGGER "${atable}_TRIG" before insert on "$atable"    for each row begin     if inserting then       if :NEW."ID" is null then          select seq_$atable.nextval into :NEW."ID" from dual;       end if;    end if; end;"   >> $trig_ddl
   echo "/" >> $trig_ddl
   echo >> $trig_ddl
   echo "-- ====================================================" >> $stamp_trig
   echo "-- LastModified Time Stamp Trigger" >> $stamp_trig
   echo >> $stamp_trig
   echo "PROMPT LastModified Time Stamp Trigger for Table: ${atable}" >> $stamp_trig
   echo "CREATE OR REPLACE TRIGGER TRTS${atable} BEFORE INSERT OR UPDATE ON ${atable}" >> $stamp_trig
   echo "FOR EACH ROW declare" >> $stamp_trig
   echo "  unixtime integer" >> $stamp_trig
   echo "     :=  (86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'))) - (to_number(substr(tz_offset(sessiontimezone),1,3))) * 3600 ;" >> $stamp_trig
#   echo "  unixtime integer" >> $stamp_trig
#   echo "     := 86400 * (sysdate - to_date('01/01/1970 00:00:00', 'DD/MM/YYYY HH24:MI:SS'));" >> $stamp_trig
   echo "BEGIN" >> $stamp_trig
   echo "  :NEW.LASTMODIFICATIONDATE := unixtime;" >> $stamp_trig
   echo "END;" >> $stamp_trig
   echo "/" >> $stamp_trig
   echo >> $stamp_trig
done
#
echo "-- ====================================================" >> $ddl_file
# Add the rest of DDL
# Along with changing the TIMESTAMP Format to ORACLE (PL/SQL) format
echo "PROMPT Creating Tables" >> $ddl_file


cat DBS-NeXtGen-Oracle.sql.tmp.0| grep --invert-match "CREATE INDEX"  >> $ddl_file

## TIMESTAMP was deprecated pre DBS_1_0_0 
#cat DBS-NeXtGen-Oracle.sql.tmp.0 | tee |grep --invert-match "CREATE INDEX" |sed -e "s%TIMESTAMP DEFAULT 0%float%g" | sed -e "s%TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP%float%g" >> $ddl_file
#cat DBS-NeXtGen-Oracle.sql.tmp.0 | tee |grep --invert-match "CREATE INDEX" |sed -e "s%TIMESTAMP DEFAULT 0%TIMESTAMP DEFAULT SYSTIMESTAMP%g" | sed -e "s%TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP%TIMESTAMP DEFAULT SYSTIMESTAMP%g" >> $ddl_file

#
#
# add the index statements
#
echo "-- === generated index statements ==============" >> $ddl_file 
echo "PROMPT Creating indexes" >> $ddl_file
index_entry_list=`cat DBS-NeXtGen-Oracle.sql.tmp.0 | grep "CREATE INDEX"|awk -FON '{print $2}'`
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
echo "-- ============== SEQUENCES ===========================" >> $ddl_file
echo "PROMPT Creating sequences" >> $ddl_file
cat $seq_ddl >> $ddl_file
echo "-- ============= AUTO INC Table.ID TRIGGERS ===========" >> $ddl_file
echo "PROMPT Creating auto inc triggers" >> $ddl_file
echo >> $ddl_file
cat $trig_ddl >>  $ddl_file
#
echo "-- ========= TIME STAMP TRIGGERS =======================" >> $ddl_file
echo >> $ddl_file
echo "PROMPT Creating timestamp triggers" >> $ddl_file
cat $stamp_trig >>  $ddl_file
#
#
echo "-- Set the Schema Version -- " >> $ddl_file
echo "INSERT INTO SchemaVersion(SCHEMAVERSION, CREATIONDATE) values ('${SchemaVersion}', ${unix_time});" >> $ddl_file
echo "-- Pre Fill some information into tables ---------" >> $ddl_file
echo "INSERT INTO AnalysisDSStatus (Status, CREATIONDATE) VALUES ('NEW', ${unix_time});" >> $ddl_file
echo "INSERT INTO FileStatus (Status, CREATIONDATE) VALUES ('VALID', ${unix_time});" >> $ddl_file
echo "INSERT INTO FileStatus (Status, CREATIONDATE) VALUES ('INVALID', ${unix_time});" >> $ddl_file
echo "INSERT INTO FileStatus (Status, CREATIONDATE) VALUES ('MERGED', ${unix_time});" >> $ddl_file
echo "INSERT INTO FileStatus (Status, CREATIONDATE) VALUES ('IMPORTED', ${unix_time});" >> $ddl_file
echo "INSERT INTO FileStatus (Status, CREATIONDATE) VALUES ('EXPORTED', ${unix_time});" >> $ddl_file
echo "INSERT INTO ProcDSStatus (Status, CREATIONDATE) VALUES ('VALID', ${unix_time});" >> $ddl_file
echo "INSERT INTO ProcDSStatus (Status, CREATIONDATE) VALUES ('INVALID', ${unix_time});" >> $ddl_file
echo "INSERT INTO ProcDSStatus (Status, CREATIONDATE) VALUES ('EXPORTED', ${unix_time});" >> $ddl_file
echo "INSERT INTO ProcDSStatus (Status, CREATIONDATE) VALUES ('IMPORTED', ${unix_time});" >> $ddl_file
echo "INSERT INTO FileType(Type, CREATIONDATE) VALUES ('EDM', ${unix_time}) ;" >> $ddl_file
echo "INSERT INTO FileType(Type, CREATIONDATE) VALUES ('STREAMER', ${unix_time}) ;" >> $ddl_file
echo "INSERT INTO AnalysisDSType(Type, CREATIONDATE) VALUES ('TEST', ${unix_time});" >> $ddl_file
echo "INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('test', ${unix_time});" >> $ddl_file
echo "INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('mc', ${unix_time});"  >> $ddl_file
echo "INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('cosmic', ${unix_time});"  >> $ddl_file
echo "INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('align', ${unix_time});"  >> $ddl_file
echo "INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('calib', ${unix_time});"  >> $ddl_file
echo "INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('raw', ${unix_time});"  >> $ddl_file
echo "INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('data', ${unix_time});"  >> $ddl_file

echo "INSERT INTO Person(Name, DistinguishedName, ContactInfo, CreationDate) Values ('DBSUSER', 'NODN', 'WH', ${unix_time});" >> $ddl_file
echo "INSERT INTO FileValidStatus (Status, CreationDate) VALUES ('VALID', ${unix_time});"  >> $ddl_file
echo "INSERT INTO FileValidStatus (Status, CreationDate) VALUES ('INVALID', ${unix_time});"  >> $ddl_file
#
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('GEN', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct', ${unix_time});"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('SIM', 'Simulated output from GEANT/OSCAR processing of GEN data  PSimHitContainer, EmbdSimVertexContainer, PCaloHitContainer, CrossingFrame', ${unix_time});"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('DIGI', 'Digitixed output from the various Digitizers that act on the SIM data    EBDigiCollection, HBHEDigiCollection, HFDigiCollection, StripDigiCollection, CSCStripDigiCollection, CSCWireDigiCollection', ${unix_time});"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('RECO', 'Reconstructed products produced from either real data or DIGI data', ${unix_time});"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('AOD', 'Analysis Object Data products TBA', ${unix_time});"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('RAW', 'Raw detector output from the HLT system', ${unix_time});"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('ALCARECO', 'IS ITS A TIER ? TBA', ${unix_time});"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('USER', 'Things that users make afte AOD. The analysis equivalent of the kitchen sink TBA', ${unix_time});"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('GEN-SIM', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct', ${unix_time});"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('GEN-SIM-DIGI', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct', ${unix_time});"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('GEN-SIM-DIGI-RECO', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct', ${unix_time});"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description, CREATIONDATE) VALUES ('DIGI-RECO', 'Min bias data', ${unix_time});"  >> $ddl_file
#
#
echo "INSERT INTO DataTier (Name, CreationDate) VALUES ('RAW', ${unix_time});" >> $ddl_file
echo "INSERT INTO DataTier (Name, CreationDate) VALUES ('GEN', ${unix_time});" >> $ddl_file
echo "INSERT INTO DataTier (Name, CreationDate) VALUES ('SIM', ${unix_time});" >> $ddl_file
echo "INSERT INTO DataTier (Name, CreationDate) VALUES ('DIGI', ${unix_time});" >> $ddl_file
echo "INSERT INTO DataTier (Name, CreationDate) VALUES ('RECO', ${unix_time});" >> $ddl_file
echo "INSERT INTO DataTier (Name, CreationDate) VALUES ('ALCARECO', ${unix_time});" >> $ddl_file
echo "INSERT INTO DataTier (Name, CreationDate) VALUES ('USER', ${unix_time});" >> $ddl_file
echo "INSERT INTO DataTier (Name, CreationDate) VALUES ('AOD', ${unix_time});" >> $ddl_file
#
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Individual', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Higgs', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('SUSYBSM', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('EWK', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Top', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('QCD', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Diffraction', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('OnSel', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Bphys', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Muons', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Egamma', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('JetMet', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('PFlowTau', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Btag', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('RelVal', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('PhysVal', ${unix_time});">> $ddl_file
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Tracker', ${unix_time});">> $ddl_file
#
echo "INSERT INTO SubSystem (Name, CreationDate) VALUES ('HCAL', ${unix_time});">> $ddl_file
echo "INSERT INTO SubSystem (Name, CreationDate) VALUES ('HCAL+', ${unix_time});">> $ddl_file
echo "INSERT INTO SubSystem (Name, CreationDate) VALUES ('HCAL-',${unix_time});">> $ddl_file 
echo "INSERT INTO SubSystem (Name, CreationDate) VALUES ('ECAL',${unix_time});">> $ddl_file 
echo "INSERT INTO SubSystem (Name, CreationDate) VALUES ('ECAL+',${unix_time});">> $ddl_file 
echo "INSERT INTO SubSystem (Name, CreationDate) VALUES ('ECAL-',${unix_time});">> $ddl_file 
echo "INSERT INTO SubSystem (Name, CreationDate) VALUES ('NOSUB',${unix_time});">> $ddl_file 
#
echo "INSERT INTO QualityValues (Value, CreationDate) VALUES ('GOOD', ${unix_time});">> $ddl_file
echo "INSERT INTO QualityValues (Value, CreationDate) VALUES ('BAD', ${unix_time});">> $ddl_file
echo "INSERT INTO QualityValues (Value, CreationDate) VALUES ('UNKNOWN', ${unix_time});">> $ddl_file
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

