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
SchemaVersion=v00_00_06
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
cat DBS-NeXtGen-MySQL.sql | sed -e "s%ID                    BIGINT UNSIGNED%ID                    BIGINT UNSIGNED not null auto_increment%g" > DBS-NeXtGen-MySQL.sql.TMP.1
cat DBS-NeXtGen-MySQL.sql.TMP.1 | sed -e "s%);%) ENGINE = InnoDB ;%g" > DBS-NeXtGen-MySQL.sql.TMP.2
cat DBS-NeXtGen-MySQL.sql.TMP.2 | sed -e "s%/%;%g" > DBS-NeXtGen-MySQL.sql.TMP.3
cat DBS-NeXtGen-MySQL.sql.TMP.3 >> $ddl_file
# Triggers for Creation Data commented out.
#echo "-- =========== TRIGGERS FOR CreationDate =============================="  >> $ddl_file
#table_list=`cat DBS-NeXtGen-MySQL.sql|grep "CREATE TABLE" | awk '{print $3}'`
#for atable in $table_list; do
#   echo   >> $ddl_file
#   echo "CREATE TRIGGER TR_TS_${atable} BEFORE INSERT ON ${atable}"  >> $ddl_file
#   echo "FOR EACH ROW SET NEW.CreationDate = NOW();"  >> $ddl_file
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
echo "INSERT INTO SchemaVersion(SchemaVersion, CreationDate) values ('$SchemaVersion', NOW());"  >> $ddl_file
echo "INSERT INTO AnalysisDSStatus (Status, CreationDate) VALUES ('NEW', NOW());"  >> $ddl_file
echo "INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('VALID', NOW()), ('INVALID', NOW()), ('IMPORTED', NOW()), ('EXPORTED', NOW());"  >> $ddl_file
echo "INSERT INTO FileStatus (Status, CreationDate) VALUES ('VALID', NOW()), ('INVALID', NOW()), ('MERGED', NOW()), ('IMPORTED', NOW()) , ('EXPORTED', NOW());"  >> $ddl_file
echo "INSERT INTO FileValidStatus (Status, CreationDate) VALUES ('VALID', NOW()), ('INVALID', NOW());"  >> $ddl_file
echo "INSERT INTO FileType(Type, CreationDate) VALUES ('EDM', NOW()) ;"  >> $ddl_file
echo "INSERT INTO AnalysisDSType(Type, CreationDate) VALUES ('TEST', NOW());"  >> $ddl_file
echo "INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('TEST', NOW()), ('RAW', NOW()) , ('MC', NOW()), ('COSMIC', NOW()), ('ALIGN', NOW()), ('CALIB', NOW());"  >> $ddl_file
#
#
#
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('SIM', 'Simulated output from GEANT/OSCAR processing of GEN data  PSimHitContainer, EmbdSimVertexContainer, PCaloHitContainer, CrossingFrame');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('DIGI', 'Digitixed output from the various Digitizers that act on the SIM data    EBDigiCollection, HBHEDigiCollection, HFDigiCollection, StripDigiCollection, CSCStripDigiCollection, CSCWireDigiCollection');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('RECO', 'Reconstructed products produced from either real data or DIGI data       TBA');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('AOD', 'Analysis Object Data products TBA');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('RAW', 'Raw detector output from the HLT system   TBA');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('FEVT', 'IS ITS A TIER ');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('ALCARECO', 'IS ITS A TIER ? TBA');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('USER', 'Things that users make afte AOD. The analysis equivalent of the kitchen sink TBA');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM-DIGI', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');"  >> $ddl_file
echo "INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM-DIGI-RECO', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');"  >> $ddl_file
#
#
#
echo "INSERT INTO DataTier (Name, CreationDate) VALUES ('GEN', NOW()), ('SIM', NOW()), ('DIGI', NOW()), ('RECO', NOW()), ('FEVT', NOW()), ('ALCARECO', NOW()), ('USER', NOW()),  ('RAW', NOW()), ('AOD', NOW());" >> $ddl_file
#echo "INSERT INTO PhysicsGroup (PhysicsGroupName, PhysicsGroupConvener, CreationDate) VALUES ('None', '', NOW()), 
#('Individual', '', NOW()), 
#('Higgs', '', NOW()), 
#('SUSY', '', NOW()), 
#('BSM', '', NOW()), 
#('EWK', '', NOW()), 
#('Top', '', NOW()), 
#('QCD', '', NOW()), 
#('Diffraction', '', NOW()), 
#('Online Selection', '', NOW()), 
#('B-physics', '', NOW()), 
#('Muons', '', NOW()), 
#('Egamma', '', NOW()), 
#('JetMet', '', NOW()), 
#('E-flow', '', NOW()), 
#('tau', '', NOW()), 
#('B-tagging', '', NOW());" >> $ddl_file
#
echo "INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('None', NOW()), 
('Individual', NOW()), 
('Higgs', NOW()), 
('SUSY', NOW()), 
('BSM', NOW()), 
('EWK', NOW()), 
('Top', NOW()), 
('QCD', NOW()), 
('Diffraction', NOW()), 
('Online Selection', NOW()), 
('B-physics', NOW()), 
('Muons', NOW()), 
('Egamma', NOW()), 
('JetMet', NOW()), 
('E-flow', NOW()), 
('tau', NOW()), 
('B-tagging', NOW());" >> $ddl_file
#
#
echo "commit;"  >> $ddl_file

echo "   Deploy DBS-NeXtGen-MySQL_DEPLOYABLE.sql to MySQL"
echo "   mysql -u username -ppassword  ...  < DBS-NeXtGen-MySQL_DEPLOYABLE.sql"
echo

