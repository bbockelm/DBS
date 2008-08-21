/* ---------------------------------------------------------------------- */
/* Script generated with: DeZign for Databases v5.1.1                     */
/* Target DBMS:           Oracle 10g                                      */
/* Project file:          DBSSearch.dez                                   */
/* Project name:                                                          */
/* Author:                                                                */
/* Script type:           Database creation script                        */
/* Created on:            2008-06-25 15:01                                */
/* Model version:         Version 2008-06-25                              */
/* ---------------------------------------------------------------------- */


/* ---------------------------------------------------------------------- */
/* Tables                                                                 */
/* ---------------------------------------------------------------------- */

/* ---------------------------------------------------------------------- */
/* Add table "BoolParamValues"                                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE BoolParamValues (
    paramId NUMBER(19) CONSTRAINT BoolParamValuesNN_paramId NOT NULL,
    value NUMBER(3) CONSTRAINT BoolParamValuesNN_value NOT NULL
);

CREATE UNIQUE INDEX BoolParamValues_paramId ON BoolParamValues (paramId);

COMMENT ON TABLE BoolParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "DoubleParamValues"                                          */
/* ---------------------------------------------------------------------- */

CREATE TABLE DoubleParamValues (
    paramId NUMBER(19) CONSTRAINT DoubleParamValuesNN_paramId NOT NULL,
    value FLOAT(24) CONSTRAINT DoubleParamValuesNN_value NOT NULL
);

CREATE UNIQUE INDEX DoubleParamValues_paramId ON DoubleParamValues (paramId);

COMMENT ON TABLE DoubleParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "EDSourceTemplates"                                          */
/* ---------------------------------------------------------------------- */

CREATE TABLE EDSourceTemplates (
    superId NUMBER(19) CONSTRAINT EDSourceTemplatesNN_superId NOT NULL,
    name VARCHAR2(64) CONSTRAINT EDSourceTemplatesNN_name NOT NULL,
    cvstag VARCHAR2(64) CONSTRAINT EDSourceTemplatesNN_cvstag NOT NULL,
    CONSTRAINT PK_EDSourceTemplates PRIMARY KEY (superId)
);

COMMENT ON TABLE EDSourceTemplates IS 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`)';

/* ---------------------------------------------------------------------- */
/* Add table "ESSourceTemplates"                                          */
/* ---------------------------------------------------------------------- */

CREATE TABLE ESSourceTemplates (
    superId NUMBER(19) CONSTRAINT ESSourceTemplatesNN_superId NOT NULL,
    name VARCHAR2(64) CONSTRAINT ESSourceTemplatesNN_name NOT NULL,
    cvstag VARCHAR2(64) CONSTRAINT ESSourceTemplatesNN_cvstag NOT NULL,
    CONSTRAINT PK_ESSourceTemplates PRIMARY KEY (superId)
);


COMMENT ON TABLE ESSourceTemplates IS 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`)';

/* ---------------------------------------------------------------------- */
/* Add table "EventIDParamValues"                                         */
/* ---------------------------------------------------------------------- */

CREATE TABLE EventIDParamValues (
    paramId NUMBER(19) CONSTRAINT EventIDParamValuesNN_paramId NOT NULL,
    value VARCHAR2(32) CONSTRAINT EventIDParamValuesNN_value NOT NULL
);

CREATE INDEX EventIDParamValues_paramId ON EventIDParamValues (paramId);

COMMENT ON TABLE EventIDParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "InputTagParamValues"                                        */
/* ---------------------------------------------------------------------- */

CREATE TABLE InputTagParamValues (
    paramId NUMBER(19) CONSTRAINT InputTagParamValuesNN_paramId NOT NULL,
    value VARCHAR2(64) CONSTRAINT InputTagParamValuesNN_value NOT NULL
);

CREATE INDEX InputTagParamValues_paramId ON InputTagParamValues (paramId);

COMMENT ON TABLE InputTagParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "Int32ParamValues"                                           */
/* ---------------------------------------------------------------------- */

CREATE TABLE Int32ParamValues (
    paramId NUMBER(19) CONSTRAINT Int32ParamValuesNN_paramId NOT NULL,
    value NUMBER(19) CONSTRAINT Int32ParamValuesNN_value NOT NULL
);

CREATE UNIQUE INDEX Int32ParamValues_paramId ON Int32ParamValues (paramId);

COMMENT ON TABLE Int32ParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "ModuleTemplates"                                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE ModuleTemplates (
    superId NUMBER(19) CONSTRAINT ModuleTemplatesNN_superId NOT NULL,
    typeId NUMBER(19) CONSTRAINT ModuleTemplatesNN_typeId NOT NULL,
    name VARCHAR2(64) CONSTRAINT ModuleTemplatesNN_name NOT NULL,
    cvstag VARCHAR2(64) CONSTRAINT ModuleTemplatesNN_cvstag NOT NULL,
    CONSTRAINT PK_ModuleTemplates PRIMARY KEY (superId)
);

CREATE INDEX ModuleTemplates_typeId ON ModuleTemplates (typeId);

COMMENT ON TABLE ModuleTemplates IS 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`typeId`) RE';

/* ---------------------------------------------------------------------- */
/* Add table "ModuleTypes"                                                */
/* ---------------------------------------------------------------------- */

CREATE TABLE ModuleTypes (
    typeId NUMBER(19) CONSTRAINT ModuleTypesNN_typeId NOT NULL,
    type VARCHAR2(32) CONSTRAINT ModuleTypesNN_type NOT NULL,
    CONSTRAINT PK_ModuleTypes PRIMARY KEY (typeId)
);

CREATE UNIQUE INDEX ModuleTypes_type ON ModuleTypes (type);

COMMENT ON TABLE ModuleTypes IS 'InnoDB free: 8192 kB';

/* ---------------------------------------------------------------------- */
/* Add table "Modules"                                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE Modules (
    superId NUMBER(19) CONSTRAINT ModulesNN_superId NOT NULL,
    templateId NUMBER(19) CONSTRAINT ModulesNN_templateId NOT NULL,
    name VARCHAR2(64) CONSTRAINT ModulesNN_name NOT NULL,
    CONSTRAINT PK_Modules PRIMARY KEY (superId)
);

CREATE INDEX Modules_templateId ON Modules (templateId);

COMMENT ON TABLE Modules IS 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`templateId`';

/* ---------------------------------------------------------------------- */
/* Add table "ParameterSets"                                              */
/* ---------------------------------------------------------------------- */

CREATE TABLE ParameterSets (
    superId NUMBER(19) CONSTRAINT ParameterSetsNN_superId NOT NULL,
    name VARCHAR2(64) CONSTRAINT ParameterSetsNN_name NOT NULL,
    tracked NUMBER(3) CONSTRAINT ParameterSetsNN_tracked NOT NULL,
    CONSTRAINT PK_ParameterSets PRIMARY KEY (superId)
);

COMMENT ON TABLE ParameterSets IS 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`)';

/* ---------------------------------------------------------------------- */
/* Add table "ParameterTypes"                                             */
/* ---------------------------------------------------------------------- */

CREATE TABLE ParameterTypes (
    paramTypeId NUMBER(19) CONSTRAINT ParameterTypesNN_paramTypeId NOT NULL,
    paramType VARCHAR2(32) CONSTRAINT ParameterTypesNN_paramType NOT NULL,
    CONSTRAINT PK_ParameterTypes PRIMARY KEY (paramTypeId)
);

CREATE UNIQUE INDEX ParameterTypes_paramType ON ParameterTypes (paramType);

COMMENT ON TABLE ParameterTypes IS 'InnoDB free: 8192 kB';

/* ---------------------------------------------------------------------- */
/* Add table "Parameters"                                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE Parameters (
    paramId NUMBER(19) CONSTRAINT ParametersNN_paramId NOT NULL,
    paramTypeId NUMBER(19) CONSTRAINT ParametersNN_paramTypeId NOT NULL,
    name VARCHAR2(64) CONSTRAINT ParametersNN_name NOT NULL,
    tracked NUMBER(3) CONSTRAINT ParametersNN_tracked NOT NULL,
    CONSTRAINT PK_Parameters PRIMARY KEY (paramId)
);
CREATE INDEX Parameters_paramTypeId ON Parameters (paramTypeId);

COMMENT ON TABLE Parameters IS 'InnoDB free: 8192 kB; (`paramTypeId`) REFER `dbs/ParameterTypes`(`paramTypeId`)';

/* ---------------------------------------------------------------------- */
/* Add table "ServiceTemplates"                                           */
/* ---------------------------------------------------------------------- */

CREATE TABLE ServiceTemplates (
    superId NUMBER(19) CONSTRAINT ServiceTemplatesNN_superId NOT NULL,
    name VARCHAR2(64) CONSTRAINT ServiceTemplatesNN_name NOT NULL,
    cvstag VARCHAR2(64) CONSTRAINT ServiceTemplatesNN_cvstag NOT NULL,
    CONSTRAINT PK_ServiceTemplates PRIMARY KEY (superId)
);

COMMENT ON TABLE ServiceTemplates IS 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`)';

/* ---------------------------------------------------------------------- */
/* Add table "SoftwareReleases"                                           */
/* ---------------------------------------------------------------------- */

CREATE TABLE SoftwareReleases (
    releaseId NUMBER(19) CONSTRAINT SoftwareReleasesNN_releaseId NOT NULL,
    releaseTag VARCHAR2(32) CONSTRAINT SoftwareReleasesNN_releaseTag NOT NULL,
    CONSTRAINT PK_SoftwareReleases PRIMARY KEY (releaseId)
);

CREATE UNIQUE INDEX SoftwareReleases_releaseTag ON SoftwareReleases (releaseTag);

COMMENT ON TABLE SoftwareReleases IS 'InnoDB free: 8192 kB';

/* ---------------------------------------------------------------------- */
/* Add table "StringParamValues"                                          */
/* ---------------------------------------------------------------------- */

CREATE TABLE StringParamValues (
    paramId NUMBER(19) CONSTRAINT StringParamValuesNN_paramId NOT NULL,
    value VARCHAR2(256) CONSTRAINT StringParamValuesNN_value NOT NULL
);

CREATE UNIQUE INDEX StringParamValues_paramId ON StringParamValues (paramId);

COMMENT ON TABLE StringParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "SuperIdParamSetAssoc"                                       */
/* ---------------------------------------------------------------------- */

CREATE TABLE SuperIdParamSetAssoc (
    superId NUMBER(19) CONSTRAINT SuperIdPSAssocNN_superId NOT NULL,
    paramSetId NUMBER(19) CONSTRAINT SuperIdPSAssocNN_paramSetId NOT NULL,
    sequenceNb NUMBER(5) CONSTRAINT SuperIdPSAssocNN_sequenceNb NOT NULL
);

CREATE INDEX SuperIdPSA_superId ON SuperIdParamSetAssoc (superId);

CREATE INDEX SuperIdPSA_paramSetId ON SuperIdParamSetAssoc (paramSetId);

COMMENT ON TABLE SuperIdParamSetAssoc IS 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`paramSetId`';

/* ---------------------------------------------------------------------- */
/* Add table "SuperIdParameterAssoc"                                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE SuperIdParameterAssoc (
    superId NUMBER(19) CONSTRAINT SuperIdPANN_superId NOT NULL,
    paramId NUMBER(19) CONSTRAINT SuperIdPANN_paramId NOT NULL,
    sequenceNb NUMBER(5) CONSTRAINT SuperIdPANN_sequenceNb NOT NULL
);

CREATE INDEX SuperIdPA_superId ON SuperIdParameterAssoc (superId);

CREATE INDEX SuperIdPA_paramId ON SuperIdParameterAssoc (paramId);

COMMENT ON TABLE SuperIdParameterAssoc IS 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`paramId`) R';

/* ---------------------------------------------------------------------- */
/* Add table "SuperIdReleaseAssoc"                                        */
/* ---------------------------------------------------------------------- */

CREATE TABLE SuperIdReleaseAssoc (
    superId NUMBER(19) CONSTRAINT SuperIdRANN_superId NOT NULL,
    releaseId NUMBER(19) CONSTRAINT SuperIdRANN_releaseId NOT NULL
);

CREATE INDEX SuperIdReleaseAssoc_superId ON SuperIdReleaseAssoc (superId);

CREATE INDEX SuperIdReleaseAssoc_releaseId ON SuperIdReleaseAssoc (releaseId);

COMMENT ON TABLE SuperIdReleaseAssoc IS 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`releaseId`)';

/* ---------------------------------------------------------------------- */
/* Add table "SuperIdVecParamSetAssoc"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE SuperIdVecParamSetAssoc (
    superId NUMBER(19) CONSTRAINT SuperIdVecPSANN_superId NOT NULL,
    vecParamSetId NUMBER(19) CONSTRAINT SuperIdVecPSANN_vecParamSetId NOT NULL,
    sequenceNb NUMBER(5) CONSTRAINT SuperIdVecPSANN_sequenceNb NOT NULL
);

CREATE INDEX SuperIdVecPSA_superId ON SuperIdVecParamSetAssoc (superId);

CREATE INDEX SuperIdVecPSA_vecParamSetId ON SuperIdVecParamSetAssoc (vecParamSetId);

COMMENT ON TABLE SuperIdVecParamSetAssoc IS 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`vecParamSet';

/* ---------------------------------------------------------------------- */
/* Add table "SuperIds"                                                   */
/* ---------------------------------------------------------------------- */

CREATE TABLE SuperIds (
    superId NUMBER(19) CONSTRAINT SuperIdsNN_superId NOT NULL,
    CONSTRAINT PK_SuperIds PRIMARY KEY (superId)
);

COMMENT ON TABLE SuperIds IS 'InnoDB free: 8192 kB';

/* ---------------------------------------------------------------------- */
/* Add table "UInt32ParamValues"                                          */
/* ---------------------------------------------------------------------- */

CREATE TABLE UInt32ParamValues (
    paramId NUMBER(19) CONSTRAINT UInt32ParamValuesNN_paramId NOT NULL,
    value NUMBER(19) CONSTRAINT UInt32ParamValuesNN_value NOT NULL
);

CREATE UNIQUE INDEX UInt32ParamValues_paramId ON UInt32ParamValues (paramId);

COMMENT ON TABLE UInt32ParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "VDoubleParamValues"                                         */
/* ---------------------------------------------------------------------- */

CREATE TABLE VDoubleParamValues (
    paramId NUMBER(19) CONSTRAINT VDoublePVNN_paramId NOT NULL,
    sequenceNb NUMBER(5) CONSTRAINT VDoublePVNN_sequenceNb NOT NULL,
    value FLOAT(24) CONSTRAINT VDoublePVNN_value NOT NULL
);

CREATE UNIQUE INDEX VDoubleParamValues_paramId ON VDoubleParamValues (paramId,sequenceNb);

COMMENT ON TABLE VDoubleParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "VEventIDParamValues"                                        */
/* ---------------------------------------------------------------------- */

CREATE TABLE VEventIDParamValues (
    paramId NUMBER(19) CONSTRAINT VEventIDPVNN_paramId NOT NULL,
    sequenceNb NUMBER(5) CONSTRAINT VEventIDPVNN_sequenceNb NOT NULL,
    value VARCHAR2(32) CONSTRAINT VEventIDPVNN_value NOT NULL
);

CREATE INDEX VEventIDParamValues_paramId ON VEventIDParamValues (paramId);

COMMENT ON TABLE VEventIDParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "VInputTagParamValues"                                       */
/* ---------------------------------------------------------------------- */

CREATE TABLE VInputTagParamValues (
    paramId NUMBER(19) CONSTRAINT VInputTagPVNN_paramId NOT NULL,
    sequenceNb NUMBER(5) CONSTRAINT VInputTagPVNN_sequenceNb NOT NULL,
    value VARCHAR2(64) CONSTRAINT VInputTagPVNN_value NOT NULL
);

CREATE INDEX VInputTagParamValues_paramId ON VInputTagParamValues (paramId);

COMMENT ON TABLE VInputTagParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "VInt32ParamValues"                                          */
/* ---------------------------------------------------------------------- */

CREATE TABLE VInt32ParamValues (
    paramId NUMBER(19) CONSTRAINT VInt32PVNN_paramId NOT NULL,
    sequenceNb NUMBER(5) CONSTRAINT VInt32PVNN_sequenceNb NOT NULL,
    value NUMBER(19) CONSTRAINT VInt32PVNN_value NOT NULL
);

CREATE UNIQUE INDEX VInt32ParamValues_paramId ON VInt32ParamValues (paramId,sequenceNb);

COMMENT ON TABLE VInt32ParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "VStringParamValues"                                         */
/* ---------------------------------------------------------------------- */

CREATE TABLE VStringParamValues (
    paramId NUMBER(19) CONSTRAINT VStringPVNN_paramId NOT NULL,
    sequenceNb NUMBER(5) CONSTRAINT VStringPVNN_sequenceNb NOT NULL,
    value VARCHAR2(256) CONSTRAINT VStringPVNN_value NOT NULL
);

CREATE UNIQUE INDEX VStringParamValues_paramId ON VStringParamValues (paramId,sequenceNb);

COMMENT ON TABLE VStringParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "VUInt32ParamValues"                                         */
/* ---------------------------------------------------------------------- */

CREATE TABLE VUInt32ParamValues (
    paramId NUMBER(19) CONSTRAINT VUInt32PVNN_paramId NOT NULL,
    sequenceNb NUMBER(5) CONSTRAINT VUInt32PVNN_sequenceNb NOT NULL,
    value NUMBER(19) CONSTRAINT VUInt32PVNN_value NOT NULL
);

CREATE UNIQUE INDEX VUInt32ParamValues_paramId ON VUInt32ParamValues (paramId,sequenceNb);

COMMENT ON TABLE VUInt32ParamValues IS 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

/* ---------------------------------------------------------------------- */
/* Add table "VecParameterSets"                                           */
/* ---------------------------------------------------------------------- */

CREATE TABLE VecParameterSets (
    superId NUMBER(19) CONSTRAINT VecPSNN_superId NOT NULL,
    name VARCHAR2(64) CONSTRAINT VecPSNN_name NOT NULL,
    tracked NUMBER(3) CONSTRAINT VecPSNN_tracked NOT NULL,
    CONSTRAINT PK_VecParameterSets PRIMARY KEY (superId)
);

COMMENT ON TABLE VecParameterSets IS 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`)';

/* ---------------------------------------------------------------------- */
/* Add table "cfgfile"                                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE cfgfile (
    filename VARCHAR2(256) CONSTRAINT cfgfileNN_filename NOT NULL,
    md5 VARCHAR2(32) CONSTRAINT cfgfileNN_md5 NOT NULL
);

CREATE UNIQUE INDEX i_cfgfile_filename ON cfgfile (filename);

CREATE UNIQUE INDEX i_cfgfile_md5 ON cfgfile (md5);

/* ---------------------------------------------------------------------- */
/* Add table "par_double"                                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE par_double (
    md5 VARCHAR2(32) CONSTRAINT par_doubleNN_md5 NOT NULL,
    pname VARCHAR2(256) CONSTRAINT par_doubleNN_pname NOT NULL,
    pval FLOAT(24) CONSTRAINT par_doubleNN_pval NOT NULL
);

CREATE INDEX i_par_double_pname ON par_double (pname);

CREATE INDEX i_par_double_pval ON par_double (pval);

CREATE INDEX i_par_double_md5 ON par_double (md5);

/* ---------------------------------------------------------------------- */
/* Add table "par_string"                                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE par_string (
    md5 VARCHAR2(32) CONSTRAINT par_stringNN_md5 NOT NULL,
    pname VARCHAR2(256) CONSTRAINT par_stringNN_pname NOT NULL,
    pval VARCHAR2(256) CONSTRAINT par_stringNN_pval NOT NULL
);

CREATE INDEX i_par_string_md5 ON par_string (md5);

CREATE INDEX i_par_string_pname ON par_string (pname);

CREATE INDEX i_par_string_pval ON par_string (pval);

/* ---------------------------------------------------------------------- */
/* Foreign key constraints                                                */
/* ---------------------------------------------------------------------- */

ALTER TABLE BoolParamValues ADD CONSTRAINT BoolParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE DoubleParamValues ADD CONSTRAINT DoubleParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE EDSourceTemplates ADD CONSTRAINT EDSourceTemplates_ibfk_1 
    FOREIGN KEY (superId) REFERENCES SuperIds (superId);

ALTER TABLE ESSourceTemplates ADD CONSTRAINT ESSourceTemplates_ibfk_1 
    FOREIGN KEY (superId) REFERENCES SuperIds (superId);

ALTER TABLE EventIDParamValues ADD CONSTRAINT EventIDParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE InputTagParamValues ADD CONSTRAINT InputTagParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE Int32ParamValues ADD CONSTRAINT Int32ParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE ModuleTemplates ADD CONSTRAINT ModuleTemplates_ibfk_1 
    FOREIGN KEY (superId) REFERENCES SuperIds (superId);

ALTER TABLE ModuleTemplates ADD CONSTRAINT ModuleTemplates_ibfk_2 
    FOREIGN KEY (typeId) REFERENCES ModuleTypes (typeId);

ALTER TABLE Modules ADD CONSTRAINT Modules_ibfk_1 
    FOREIGN KEY (superId) REFERENCES SuperIds (superId);

ALTER TABLE Modules ADD CONSTRAINT Modules_ibfk_2 
    FOREIGN KEY (templateId) REFERENCES ModuleTemplates (superId);

ALTER TABLE ParameterSets ADD CONSTRAINT ParameterSets_ibfk_1 
    FOREIGN KEY (superId) REFERENCES SuperIds (superId);

ALTER TABLE Parameters ADD CONSTRAINT Parameters_ibfk_1 
    FOREIGN KEY (paramTypeId) REFERENCES ParameterTypes (paramTypeId);

ALTER TABLE ServiceTemplates ADD CONSTRAINT ServiceTemplates_ibfk_1 
    FOREIGN KEY (superId) REFERENCES SuperIds (superId);

ALTER TABLE StringParamValues ADD CONSTRAINT StringParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE SuperIdParamSetAssoc ADD CONSTRAINT SuperIdParamSetAssoc_ibfk_1 
    FOREIGN KEY (superId) REFERENCES SuperIds (superId);

ALTER TABLE SuperIdParamSetAssoc ADD CONSTRAINT SuperIdParamSetAssoc_ibfk_2 
    FOREIGN KEY (paramSetId) REFERENCES ParameterSets (superId);

ALTER TABLE SuperIdParameterAssoc ADD CONSTRAINT SuperIdParameterAssoc_ibfk_1 
    FOREIGN KEY (superId) REFERENCES SuperIds (superId);

ALTER TABLE SuperIdParameterAssoc ADD CONSTRAINT SuperIdParameterAssoc_ibfk_2 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE SuperIdReleaseAssoc ADD CONSTRAINT SuperIdReleaseAssoc_ibfk_1 
    FOREIGN KEY (superId) REFERENCES SuperIds (superId);

ALTER TABLE SuperIdReleaseAssoc ADD CONSTRAINT SuperIdReleaseAssoc_ibfk_2 
    FOREIGN KEY (releaseId) REFERENCES SoftwareReleases (releaseId);

ALTER TABLE SuperIdVecParamSetAssoc ADD CONSTRAINT SuperIdVecParamSetAssoc_ibfk_1 
    FOREIGN KEY (superId) REFERENCES SuperIds (superId);

ALTER TABLE SuperIdVecParamSetAssoc ADD CONSTRAINT SuperIdVecParamSetAssoc_ibfk_2 
    FOREIGN KEY (vecParamSetId) REFERENCES VecParameterSets (superId);

ALTER TABLE UInt32ParamValues ADD CONSTRAINT UInt32ParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE VDoubleParamValues ADD CONSTRAINT VDoubleParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE VEventIDParamValues ADD CONSTRAINT VEventIDParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE VInputTagParamValues ADD CONSTRAINT VInputTagParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE VInt32ParamValues ADD CONSTRAINT VInt32ParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE VStringParamValues ADD CONSTRAINT VStringParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE VUInt32ParamValues ADD CONSTRAINT VUInt32ParamValues_ibfk_1 
    FOREIGN KEY (paramId) REFERENCES Parameters (paramId);

ALTER TABLE VecParameterSets ADD CONSTRAINT VecParameterSets_ibfk_1 
    FOREIGN KEY (superId) REFERENCES SuperIds (superId);
