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
SchemaVersion=DBS_1_0_4
#
ddl_file=DBS-NeXtGen-MySQL_DEPLOYABLE.sql
#
#
#
#  execution starts here
#
rm -f DBS-NeXtGen-MySQL.sql.TMP.*
#
dos2unix DBS-NeXtGen-MySQL.sql
#
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
#cat DBS-NeXtGen-MySQL.sql | sed -e "s%ID                    int%ID                    int not null auto_increment%g" > DBS-NeXtGen-MySQL.sql.TMP.1
cat DBS-NeXtGen-MySQL.sql | sed -e "s%ID                    BIGINT UNSIGNED%ID                    BIGINT UNSIGNED not null auto_increment%g" > DBS-NeXtGen-MySQL.sql.TMP.0
cat DBS-NeXtGen-MySQL.sql.TMP.0 | sed -e "s%ID                      BIGINT UNSIGNED%ID                    BIGINT UNSIGNED not null auto_increment%g" > DBS-NeXtGen-MySQL.sql.TMP.1
cat DBS-NeXtGen-MySQL.sql.TMP.1 | sed -e "s%);%) ENGINE = InnoDB ;%g" > DBS-NeXtGen-MySQL.sql.TMP.2
cat DBS-NeXtGen-MySQL.sql.TMP.2 | sed -e "s%/%;%g" > DBS-NeXtGen-MySQL.sql.TMP.3
cat DBS-NeXtGen-MySQL.sql.TMP.3 >> $ddl_file
# Triggers for Creation Data commented out.
#echo "-- =========== TRIGGERS FOR CreationDate =============================="  >> $ddl_file
#table_list=`cat DBS-NeXtGen-MySQL.sql|grep "CREATE TABLE" | awk '{print $3}'`
#for atable in $table_list; do
#   echo   >> $ddl_file
#   echo "CREATE TRIGGER TR_TS_${atable} BEFORE INSERT ON ${atable}"  >> $ddl_file
#   echo "FOR EACH ROW SET NEW.CreationDate = UNIX_TIMESTAMP();"  >> $ddl_file
#done
#
# INSERT Triggers for LastModificationDate, We need to put unix_timestamp there
echo "-- =========== INSERT TRIGGERS FOR LastModificationDate ============================"  >> $ddl_file
table_list=`cat DBS-NeXtGen-MySQL.sql|grep "CREATE TABLE" | awk '{print $3}'`
for atable in $table_list; do
   echo   >> $ddl_file
   echo "CREATE TRIGGER TR_${atable} BEFORE INSERT ON ${atable}"  >> $ddl_file
   echo "FOR EACH ROW SET NEW.LastModificationDate = UNIX_TIMESTAMP();"  >> $ddl_file
done
#
echo  >> $ddl_file
# UPDATE Triggers for LastModificationDate, We need to put unix_timestamp there
echo "-- =========== UPDATE TRIGGERS FOR LastModificationDate ============================"  >> $ddl_file
table_list=`cat DBS-NeXtGen-MySQL.sql|grep "CREATE TABLE" | awk '{print $3}'`
for atable in $table_list; do
   echo   >> $ddl_file
   echo "CREATE TRIGGER UTR_${atable} BEFORE UPDATE ON ${atable}"  >> $ddl_file
   echo "FOR EACH ROW SET NEW.LastModificationDate = UNIX_TIMESTAMP();"  >> $ddl_file
done
#
echo  >> $ddl_file
#
echo "-- ======================================================================"  >> $ddl_file
echo "-- Initialize status tables There can be better ways to do it ( laters ) "  >> $ddl_file
echo "-- ======================================================================"  >> $ddl_file
echo  >> $ddl_file
echo "INSERT INTO SchemaVersion(SchemaVersion, CreationDate) values ('$SchemaVersion', UNIX_TIMESTAMP());"  >> $ddl_file
echo "INSERT INTO AnalysisDSStatus (Status, CreationDate) VALUES ('NEW', UNIX_TIMESTAMP());"  >> $ddl_file
echo "INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('VALID', UNIX_TIMESTAMP()), ('INVALID', UNIX_TIMESTAMP()), ('IMPORTED', UNIX_TIMESTAMP()), ('EXPORTED', UNIX_TIMESTAMP());"  >> $ddl_file
echo "INSERT INTO FileStatus (Status, CreationDate) VALUES ('VALID', UNIX_TIMESTAMP()), ('INVALID', UNIX_TIMESTAMP()), ('MERGED', UNIX_TIMESTAMP()), ('IMPORTED', UNIX_TIMESTAMP()) , ('EXPORTED', UNIX_TIMESTAMP());"  >> $ddl_file
echo "INSERT INTO FileValidStatus (Status, CreationDate) VALUES ('VALID', UNIX_TIMESTAMP()), ('INVALID', UNIX_TIMESTAMP());"  >> $ddl_file
echo "INSERT INTO FileType(Type, CreationDate) VALUES ('EDM', UNIX_TIMESTAMP()) ;"  >> $ddl_file
echo "INSERT INTO FileType(Type, CreationDate) VALUES ('STREAMER', UNIX_TIMESTAMP()) ;"  >> $ddl_file
echo "INSERT INTO AnalysisDSType(Type, CreationDate) VALUES ('TEST', UNIX_TIMESTAMP());"  >> $ddl_file
echo "INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('test', UNIX_TIMESTAMP()), ('data',  UNIX_TIMESTAMP()), ('raw', UNIX_TIMESTAMP()) , ('mc', UNIX_TIMESTAMP()), ('cosmic', UNIX_TIMESTAMP()), ('align', UNIX_TIMESTAMP()), ('calib', UNIX_TIMESTAMP());"  >> $ddl_file
#
#
#
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('SIM', 'Simulated output from GEANT/OSCAR processing of GEN data  PSimHitContainer, EmbdSimVertexContainer, PCaloHitContainer, CrossingFrame');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('DIGI', 'Digitixed output from the various Digitizers that act on the SIM data    EBDigiCollection, HBHEDigiCollection, HFDigiCollection, StripDigiCollection, CSCStripDigiCollection, CSCWireDigiCollection');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('RECO', 'Reconstructed products produced from either real data or DIGI data       TBA');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('AOD', 'Analysis Object Data products TBA');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('RAW', 'Raw detector output from the HLT system   TBA');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('ALCARECO', 'IS ITS A TIER ? TBA');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('USER', 'Things that users make afte AOD. The analysis equivalent of the kitchen sink TBA');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM-DIGI', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('DIGI-RECO', 'Min bias data');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM-DIGI-RECO', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');"  >> $ddl_file
#
#
#
echo "INSERT INTO DataTier (Name, CreationDate) VALUES ('GEN', UNIX_TIMESTAMP()), ('SIM', UNIX_TIMESTAMP()), ('DIGI', UNIX_TIMESTAMP()), ('RECO', UNIX_TIMESTAMP()), ('ALCARECO', UNIX_TIMESTAMP()), ('USER', UNIX_TIMESTAMP()),  ('RAW', UNIX_TIMESTAMP()), ('AOD', UNIX_TIMESTAMP()) ;" >> $ddl_file

#echo "INSERT INTO PhysicsGroup (PhysicsGroupName, PhysicsGroupConvener, CreationDate) VALUES ('None', '', UNIX_TIMESTAMP()), 
#('Individual', '', UNIX_TIMESTAMP()), 
#('Higgs', '', UNIX_TIMESTAMP()), 
#('SUSY', '', UNIX_TIMESTAMP()), 
#('BSM', '', UNIX_TIMESTAMP()), 
#('EWK', '', UNIX_TIMESTAMP()), 
#('Top', '', UNIX_TIMESTAMP()), 
#('QCD', '', UNIX_TIMESTAMP()), 
#('Diffraction', '', UNIX_TIMESTAMP()), 
#('Online Selection', '', UNIX_TIMESTAMP()), 
#('B-physics', '', UNIX_TIMESTAMP()), 
#('Muons', '', UNIX_TIMESTAMP()), 
#('Egamma', '', UNIX_TIMESTAMP()), 
#('JetMet', '', UNIX_TIMESTAMP()), 
#('E-flow', '', UNIX_TIMESTAMP()), 
#('tau', '', UNIX_TIMESTAMP()), 
#('B-tagging', '', UNIX_TIMESTAMP());" >> $ddl_file
#
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Individual', UNIX_TIMESTAMP()), 
('Higgs', UNIX_TIMESTAMP()), 
('SUSYBSM', UNIX_TIMESTAMP()), 
('EWK', UNIX_TIMESTAMP()), 
('Top', UNIX_TIMESTAMP()), 
('QCD', UNIX_TIMESTAMP()), 
('Diffraction', UNIX_TIMESTAMP()), 
('OnSel', UNIX_TIMESTAMP()), 
('Bphys', UNIX_TIMESTAMP()), 
('Muons', UNIX_TIMESTAMP()), 
('Egamma', UNIX_TIMESTAMP()), 
('JetMet', UNIX_TIMESTAMP()), 
('PFlowTau', UNIX_TIMESTAMP()), 
('Btag', UNIX_TIMESTAMP()), 
('RelVal', UNIX_TIMESTAMP()), 
('Trigger', UNIX_TIMESTAMP()), 
('PhysVal', UNIX_TIMESTAMP());" >> $ddl_file
#
#
echo "commit;"  >> $ddl_file

echo "   Deploy DBS-NeXtGen-MySQL_DEPLOYABLE.sql to MySQL"
echo "   mysql -u username -ppassword  ...  < DBS-NeXtGen-MySQL_DEPLOYABLE.sql"
echo

