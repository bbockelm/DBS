import os
f = open('DBS-NeXtGen-MySQL_DEPLOYABLE.sql')
data = {}
tmpStr = ""
trigStr = ""
insertStr = ""
state = 0
for i in f.readlines():
	line = i.strip('\n')
	if line.startswith('ALTER TABLE'):
		tokens = line.split(' ')
		table = tokens[2]
		state = 1
	if state == 1 and (line.find('foreign key') != -1):
		#print table
		state = 2
		fkStr = line[line.find('foreign key'):]
		#fk = fkStr[fkStr.find('(') + 1: fkStr.find(')')]
		#fkTmpStr = "    " + fk + " INTEGER,\n"
		fkTmpStr = "    " + fkStr + "\n"
		data[table] += fkTmpStr
		#print fkStr
		
	if line.startswith('  ) ENGINE = InnoDB ;'):
		#print ");"
		state = 0
		data[table] = tmpStr
		tmpStr = ""
	if line.startswith('CREATE TABLE'):
		state = 3
		tokens = line.split(' ')
		table = tokens[2]
	if state == 3 :
		if (line.find('ID                    BIGINT UNSIGNED not null auto_increment,') != -1) :
			#print "    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,"
			tmpStr += "    ID                    INTEGER PRIMARY KEY AUTOINCREMENT,\n"
		else :
			if (not line.startswith('    primary key(ID)')):
				tmpStr += line + "\n"
				#print line

	if line.startswith('CREATE TRIGGER '):
		table = line[line.find(' ON ') + 4 :]
		if (line.find('BEFORE INSERT') != -1) :
			state = 4
			line = line.replace('BEFORE', 'AFTER')
			trigStr += line + "\n"
		else:
			trigStr += line + "\n"
			state = 5
	
	if state == 4 or state == 5:
		if line.startswith('FOR EACH ROW'):
			trigStr += "FOR EACH ROW\n"
			trigStr += "\tBEGIN\n"
			trigStr += "\t\tUPDATE " + table + " SET LastModificationDate = strftime('%s','now')\n"
			trigStr += "\t\tWHERE rowid = new.rowid;\n"
			trigStr += "\tEND;\n\n"
		
		
	#if line.startswith('INSERT INTO'):
	#	state = 6
		
	#if state == 6:
	#	insertStr += line.replace('UNIX_TIMESTAMP()', "strftime('%s','now')") + "\n"
		
#print data
insertStr = """INSERT INTO SchemaVersion(SchemaVersion, InstanceName, CreationDate) values ('DBS_1_0_7', 'LOCAL', strftime('%s','now'));
INSERT INTO AnalysisDSStatus (Status, CreationDate) VALUES ('NEW', strftime('%s','now'));
INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('VALID', strftime('%s','now'));
INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('INVALID', strftime('%s','now'));
INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('IMPORTED', strftime('%s','now'));
INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('EXPORTED', strftime('%s','now'));
INSERT INTO ProcDSStatus (Status, CreationDate) VALUES ('RO', strftime('%s','now'));

INSERT INTO FileStatus (Status, CreationDate) VALUES ('VALID', strftime('%s','now'));
INSERT INTO FileStatus (Status, CreationDate) VALUES ('INVALID', strftime('%s','now'));
INSERT INTO FileStatus (Status, CreationDate) VALUES ('MERGED', strftime('%s','now'));
INSERT INTO FileStatus (Status, CreationDate) VALUES ('IMPORTED', strftime('%s','now'));
INSERT INTO FileStatus (Status, CreationDate) VALUES ('EXPORTED', strftime('%s','now'));

INSERT INTO FileValidStatus (Status, CreationDate) VALUES ('VALID', strftime('%s','now'));
INSERT INTO FileValidStatus (Status, CreationDate) VALUES ('INVALID', strftime('%s','now'));

INSERT INTO FileType(Type, CreationDate) VALUES ('EDM', strftime('%s','now')) ;
INSERT INTO FileType(Type, CreationDate) VALUES ('STREAMER', strftime('%s','now')) ;
INSERT INTO AnalysisDSType(Type, CreationDate) VALUES ('TEST', strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('test', strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('data',  strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('raw', strftime('%s','now')) ;
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('mc', strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('cosmic', strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('align', strftime('%s','now'));
INSERT INTO PrimaryDSType  (Type, CreationDate) VALUES ('calib', strftime('%s','now'));

INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('SIM', 'Simulated output from GEANT/OSCAR processing of GEN data  PSimHitContainer, EmbdSimVertexContainer, PCaloHitContainer, CrossingFrame');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('DIGI', 'Digitixed output from the various Digitizers that act on the SIM data    EBDigiCollection, HBHEDigiCollection, HFDigiCollection, StripDigiCollection, CSCStripDigiCollection, CSCWireDigiCollection');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('RECO', 'Reconstructed products produced from either real data or DIGI data       TBA');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('AOD', 'Analysis Object Data products TBA');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('AODSIM', 'AODSIM Tier asked by Skim Team');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('RAW', 'Raw detector output from the HLT system   TBA');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('ALCARECO', 'IS ITS A TIER ? TBA');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('USER', 'Things that users make afte AOD. The analysis equivalent of the kitchen sink TBA');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM-DIGI', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('DIGI-RECO', 'Min bias data');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM-DIGI-RECO', 'Generator output, four vectors and vertices in vacuum. For example, pythia events HepMCProduct');
INSERT INTO DataTierOrder(DataTierOrder, Description) VALUES ('GEN-SIM-DIGI-RAW', 'SV Support 102463 for CSA 07');

INSERT INTO DataTier (Name, CreationDate) VALUES ('GEN', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('SIM', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('DIGI', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('RECO', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('ALCARECO', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('USER', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES  ('RAW', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('AOD', strftime('%s','now'));
INSERT INTO DataTier (Name, CreationDate) VALUES ('AODSIM', strftime('%s','now')) ;

INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Individual', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Higgs', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('SUSYBSM', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('EWK', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Top', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('QCD', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Diffraction', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('OnSel', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Bphys', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Muons', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Egamma', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('JetMet', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('PFlowTau', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Btag', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('RelVal', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('Tracker', strftime('%s','now'));
INSERT INTO PhysicsGroup (PhysicsGroupName, CreationDate) VALUES ('PhysVal', strftime('%s','now'));

INSERT INTO QualityValues (Value, CreationDate) VALUES ('GOOD', strftime('%s','now'));
INSERT INTO QualityValues (Value, CreationDate) VALUES ('BAD', strftime('%s','now'));
INSERT INTO QualityValues (Value, CreationDate) VALUES ('UNKNOWN', strftime('%s','now'));"""
for a in data:
	print "--%s" %a
	print data[a] + ");"
	print "\n\n\n"
		
	
print trigStr
print insertStr
