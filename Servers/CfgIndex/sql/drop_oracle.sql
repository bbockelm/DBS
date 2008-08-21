/* ---------------------------------------------------------------------- */
/* Script generated with: DeZign for Databases v5.1.1                     */
/* Target DBMS:           Oracle 10g                                      */
/* Project file:          DBSSearch.dez                                   */
/* Project name:                                                          */
/* Author:                                                                */
/* Script type:           Database drop script                            */
/* Created on:            2008-06-25 15:01                                */
/* Model version:         Version 2008-06-25                              */
/* ---------------------------------------------------------------------- */


/* ---------------------------------------------------------------------- */
/* Drop foreign key constraints                                           */
/* ---------------------------------------------------------------------- */

ALTER TABLE BoolParamValues DROP CONSTRAINT BoolParamValues_ibfk_1;

ALTER TABLE DoubleParamValues DROP CONSTRAINT DoubleParamValues_ibfk_1;

ALTER TABLE EDSourceTemplates DROP CONSTRAINT EDSourceTemplates_ibfk_1;

ALTER TABLE ESSourceTemplates DROP CONSTRAINT ESSourceTemplates_ibfk_1;

ALTER TABLE EventIDParamValues DROP CONSTRAINT EventIDParamValues_ibfk_1;

ALTER TABLE InputTagParamValues DROP CONSTRAINT InputTagParamValues_ibfk_1;

ALTER TABLE Int32ParamValues DROP CONSTRAINT Int32ParamValues_ibfk_1;

ALTER TABLE ModuleTemplates DROP CONSTRAINT ModuleTemplates_ibfk_1;

ALTER TABLE ModuleTemplates DROP CONSTRAINT ModuleTemplates_ibfk_2;

ALTER TABLE Modules DROP CONSTRAINT Modules_ibfk_1;

ALTER TABLE Modules DROP CONSTRAINT Modules_ibfk_2;

ALTER TABLE ParameterSets DROP CONSTRAINT ParameterSets_ibfk_1;

ALTER TABLE Parameters DROP CONSTRAINT Parameters_ibfk_1;

ALTER TABLE ServiceTemplates DROP CONSTRAINT ServiceTemplates_ibfk_1;

ALTER TABLE StringParamValues DROP CONSTRAINT StringParamValues_ibfk_1;

ALTER TABLE SuperIdParamSetAssoc DROP CONSTRAINT SuperIdParamSetAssoc_ibfk_1;

ALTER TABLE SuperIdParamSetAssoc DROP CONSTRAINT SuperIdParamSetAssoc_ibfk_2;

ALTER TABLE SuperIdParameterAssoc DROP CONSTRAINT SuperIdParameterAssoc_ibfk_1;

ALTER TABLE SuperIdParameterAssoc DROP CONSTRAINT SuperIdParameterAssoc_ibfk_2;

ALTER TABLE SuperIdReleaseAssoc DROP CONSTRAINT SuperIdReleaseAssoc_ibfk_1;

ALTER TABLE SuperIdReleaseAssoc DROP CONSTRAINT SuperIdReleaseAssoc_ibfk_2;

ALTER TABLE SuperIdVecParamSetAssoc DROP CONSTRAINT SuperIdVecParamSetAssoc_ibfk_1;

ALTER TABLE SuperIdVecParamSetAssoc DROP CONSTRAINT SuperIdVecParamSetAssoc_ibfk_2;

ALTER TABLE UInt32ParamValues DROP CONSTRAINT UInt32ParamValues_ibfk_1;

ALTER TABLE VDoubleParamValues DROP CONSTRAINT VDoubleParamValues_ibfk_1;

ALTER TABLE VEventIDParamValues DROP CONSTRAINT VEventIDParamValues_ibfk_1;

ALTER TABLE VInputTagParamValues DROP CONSTRAINT VInputTagParamValues_ibfk_1;

ALTER TABLE VInt32ParamValues DROP CONSTRAINT VInt32ParamValues_ibfk_1;

ALTER TABLE VStringParamValues DROP CONSTRAINT VStringParamValues_ibfk_1;

ALTER TABLE VUInt32ParamValues DROP CONSTRAINT VUInt32ParamValues_ibfk_1;

ALTER TABLE VecParameterSets DROP CONSTRAINT VecParameterSets_ibfk_1;

/* ---------------------------------------------------------------------- */
/* Drop table "BoolParamValues"                                           */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE BoolParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE BoolParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE BoolParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "DoubleParamValues"                                         */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE DoubleParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE DoubleParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE DoubleParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "EDSourceTemplates"                                         */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE EDSourceTemplates DROP CONSTRAINT NN_superId;

ALTER TABLE EDSourceTemplates DROP CONSTRAINT NN_name;

ALTER TABLE EDSourceTemplates DROP CONSTRAINT NN_cvstag;

ALTER TABLE EDSourceTemplates DROP CONSTRAINT PK_EDSourceTemplates;

/* Drop table */

DROP TABLE EDSourceTemplates;

/* ---------------------------------------------------------------------- */
/* Drop table "ESSourceTemplates"                                         */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE ESSourceTemplates DROP CONSTRAINT NN_superId;

ALTER TABLE ESSourceTemplates DROP CONSTRAINT NN_name;

ALTER TABLE ESSourceTemplates DROP CONSTRAINT NN_cvstag;

ALTER TABLE ESSourceTemplates DROP CONSTRAINT PK_ESSourceTemplates;

/* Drop table */

DROP TABLE ESSourceTemplates;

/* ---------------------------------------------------------------------- */
/* Drop table "EventIDParamValues"                                        */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE EventIDParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE EventIDParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE EventIDParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "InputTagParamValues"                                       */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE InputTagParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE InputTagParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE InputTagParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "Int32ParamValues"                                          */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE Int32ParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE Int32ParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE Int32ParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "ModuleTemplates"                                           */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE ModuleTemplates DROP CONSTRAINT NN_superId;

ALTER TABLE ModuleTemplates DROP CONSTRAINT NN_typeId;

ALTER TABLE ModuleTemplates DROP CONSTRAINT NN_name;

ALTER TABLE ModuleTemplates DROP CONSTRAINT NN_cvstag;

ALTER TABLE ModuleTemplates DROP CONSTRAINT PK_ModuleTemplates;

/* Drop table */

DROP TABLE ModuleTemplates;

/* ---------------------------------------------------------------------- */
/* Drop table "ModuleTypes"                                               */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE ModuleTypes DROP CONSTRAINT NN_typeId;

ALTER TABLE ModuleTypes DROP CONSTRAINT NN_type;

ALTER TABLE ModuleTypes DROP CONSTRAINT PK_ModuleTypes;

/* Drop table */

DROP TABLE ModuleTypes;

/* ---------------------------------------------------------------------- */
/* Drop table "Modules"                                                   */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE Modules DROP CONSTRAINT NN_superId;

ALTER TABLE Modules DROP CONSTRAINT NN_templateId;

ALTER TABLE Modules DROP CONSTRAINT NN_name;

ALTER TABLE Modules DROP CONSTRAINT PK_Modules;

/* Drop table */

DROP TABLE Modules;

/* ---------------------------------------------------------------------- */
/* Drop table "ParameterSets"                                             */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE ParameterSets DROP CONSTRAINT NN_superId;

ALTER TABLE ParameterSets DROP CONSTRAINT NN_name;

ALTER TABLE ParameterSets DROP CONSTRAINT NN_tracked;

ALTER TABLE ParameterSets DROP CONSTRAINT PK_ParameterSets;

/* Drop table */

DROP TABLE ParameterSets;

/* ---------------------------------------------------------------------- */
/* Drop table "ParameterTypes"                                            */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE ParameterTypes DROP CONSTRAINT NN_paramTypeId;

ALTER TABLE ParameterTypes DROP CONSTRAINT NN_paramType;

ALTER TABLE ParameterTypes DROP CONSTRAINT PK_ParameterTypes;

/* Drop table */

DROP TABLE ParameterTypes;

/* ---------------------------------------------------------------------- */
/* Drop table "Parameters"                                                */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE Parameters DROP CONSTRAINT NN_paramId;

ALTER TABLE Parameters DROP CONSTRAINT NN_paramTypeId;

ALTER TABLE Parameters DROP CONSTRAINT NN_name;

ALTER TABLE Parameters DROP CONSTRAINT NN_tracked;

ALTER TABLE Parameters DROP CONSTRAINT PK_Parameters;

/* Drop table */

DROP TABLE Parameters;

/* ---------------------------------------------------------------------- */
/* Drop table "ServiceTemplates"                                          */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE ServiceTemplates DROP CONSTRAINT NN_superId;

ALTER TABLE ServiceTemplates DROP CONSTRAINT NN_name;

ALTER TABLE ServiceTemplates DROP CONSTRAINT NN_cvstag;

ALTER TABLE ServiceTemplates DROP CONSTRAINT PK_ServiceTemplates;

/* Drop table */

DROP TABLE ServiceTemplates;

/* ---------------------------------------------------------------------- */
/* Drop table "SoftwareReleases"                                          */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE SoftwareReleases DROP CONSTRAINT NN_releaseId;

ALTER TABLE SoftwareReleases DROP CONSTRAINT NN_releaseTag;

ALTER TABLE SoftwareReleases DROP CONSTRAINT PK_SoftwareReleases;

/* Drop table */

DROP TABLE SoftwareReleases;

/* ---------------------------------------------------------------------- */
/* Drop table "StringParamValues"                                         */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE StringParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE StringParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE StringParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "SuperIdParamSetAssoc"                                      */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE SuperIdParamSetAssoc DROP CONSTRAINT NN_superId;

ALTER TABLE SuperIdParamSetAssoc DROP CONSTRAINT NN_paramSetId;

ALTER TABLE SuperIdParamSetAssoc DROP CONSTRAINT NN_sequenceNb;

/* Drop table */

DROP TABLE SuperIdParamSetAssoc;

/* ---------------------------------------------------------------------- */
/* Drop table "SuperIdParameterAssoc"                                     */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE SuperIdParameterAssoc DROP CONSTRAINT NN_superId;

ALTER TABLE SuperIdParameterAssoc DROP CONSTRAINT NN_paramId;

ALTER TABLE SuperIdParameterAssoc DROP CONSTRAINT NN_sequenceNb;

/* Drop table */

DROP TABLE SuperIdParameterAssoc;

/* ---------------------------------------------------------------------- */
/* Drop table "SuperIdReleaseAssoc"                                       */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE SuperIdReleaseAssoc DROP CONSTRAINT NN_superId;

ALTER TABLE SuperIdReleaseAssoc DROP CONSTRAINT NN_releaseId;

/* Drop table */

DROP TABLE SuperIdReleaseAssoc;

/* ---------------------------------------------------------------------- */
/* Drop table "SuperIdVecParamSetAssoc"                                   */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE SuperIdVecParamSetAssoc DROP CONSTRAINT NN_superId;

ALTER TABLE SuperIdVecParamSetAssoc DROP CONSTRAINT NN_vecParamSetId;

ALTER TABLE SuperIdVecParamSetAssoc DROP CONSTRAINT NN_sequenceNb;

/* Drop table */

DROP TABLE SuperIdVecParamSetAssoc;

/* ---------------------------------------------------------------------- */
/* Drop table "SuperIds"                                                  */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE SuperIds DROP CONSTRAINT NN_superId;

ALTER TABLE SuperIds DROP CONSTRAINT PK_SuperIds;

/* Drop table */

DROP TABLE SuperIds;

/* ---------------------------------------------------------------------- */
/* Drop table "UInt32ParamValues"                                         */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE UInt32ParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE UInt32ParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE UInt32ParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "VDoubleParamValues"                                        */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE VDoubleParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE VDoubleParamValues DROP CONSTRAINT NN_sequenceNb;

ALTER TABLE VDoubleParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE VDoubleParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "VEventIDParamValues"                                       */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE VEventIDParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE VEventIDParamValues DROP CONSTRAINT NN_sequenceNb;

ALTER TABLE VEventIDParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE VEventIDParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "VInputTagParamValues"                                      */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE VInputTagParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE VInputTagParamValues DROP CONSTRAINT NN_sequenceNb;

ALTER TABLE VInputTagParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE VInputTagParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "VInt32ParamValues"                                         */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE VInt32ParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE VInt32ParamValues DROP CONSTRAINT NN_sequenceNb;

ALTER TABLE VInt32ParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE VInt32ParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "VStringParamValues"                                        */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE VStringParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE VStringParamValues DROP CONSTRAINT NN_sequenceNb;

ALTER TABLE VStringParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE VStringParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "VUInt32ParamValues"                                        */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE VUInt32ParamValues DROP CONSTRAINT NN_paramId;

ALTER TABLE VUInt32ParamValues DROP CONSTRAINT NN_sequenceNb;

ALTER TABLE VUInt32ParamValues DROP CONSTRAINT NN_value;

/* Drop table */

DROP TABLE VUInt32ParamValues;

/* ---------------------------------------------------------------------- */
/* Drop table "VecParameterSets"                                          */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE VecParameterSets DROP CONSTRAINT NN_superId;

ALTER TABLE VecParameterSets DROP CONSTRAINT NN_name;

ALTER TABLE VecParameterSets DROP CONSTRAINT NN_tracked;

ALTER TABLE VecParameterSets DROP CONSTRAINT PK_VecParameterSets;

/* Drop table */

DROP TABLE VecParameterSets;

/* ---------------------------------------------------------------------- */
/* Drop table "cfgfile"                                                   */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE cfgfile DROP CONSTRAINT NN_filename;

ALTER TABLE cfgfile DROP CONSTRAINT NN_md5;

/* Drop table */

DROP TABLE cfgfile;

/* ---------------------------------------------------------------------- */
/* Drop table "par_double"                                                */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE par_double DROP CONSTRAINT NN_md5;

ALTER TABLE par_double DROP CONSTRAINT NN_pname;

ALTER TABLE par_double DROP CONSTRAINT NN_pval;

/* Drop table */

DROP TABLE par_double;

/* ---------------------------------------------------------------------- */
/* Drop table "par_string"                                                */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE par_string DROP CONSTRAINT NN_md5;

ALTER TABLE par_string DROP CONSTRAINT NN_pname;

ALTER TABLE par_string DROP CONSTRAINT NN_pval;

/* Drop table */

DROP TABLE par_string;
