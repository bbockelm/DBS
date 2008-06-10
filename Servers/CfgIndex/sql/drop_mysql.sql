# ---------------------------------------------------------------------- #
# Script generated with: DeZign for Databases v5.1.1                     #
# Target DBMS:           MySQL 5                                         #
# Project file:          DBSSearch.dez                                   #
# Project name:                                                          #
# Author:                                                                #
# Script type:           Database drop script                            #
# Created on:            2008-05-29 17:12                                #
# Model version:         Version 2008-05-29 1                            #
# ---------------------------------------------------------------------- #


# ---------------------------------------------------------------------- #
# Drop foreign key constraints                                           #
# ---------------------------------------------------------------------- #

ALTER TABLE `BoolParamValues` DROP FOREIGN KEY `BoolParamValues_ibfk_1`;

ALTER TABLE `DoubleParamValues` DROP FOREIGN KEY `DoubleParamValues_ibfk_1`;

ALTER TABLE `EDSourceTemplates` DROP FOREIGN KEY `EDSourceTemplates_ibfk_1`;

ALTER TABLE `ESSourceTemplates` DROP FOREIGN KEY `ESSourceTemplates_ibfk_1`;

ALTER TABLE `EventIDParamValues` DROP FOREIGN KEY `EventIDParamValues_ibfk_1`;

ALTER TABLE `InputTagParamValues` DROP FOREIGN KEY `InputTagParamValues_ibfk_1`;

ALTER TABLE `Int32ParamValues` DROP FOREIGN KEY `Int32ParamValues_ibfk_1`;

ALTER TABLE `ModuleTemplates` DROP FOREIGN KEY `ModuleTemplates_ibfk_1`;

ALTER TABLE `ModuleTemplates` DROP FOREIGN KEY `ModuleTemplates_ibfk_2`;

ALTER TABLE `Modules` DROP FOREIGN KEY `Modules_ibfk_1`;

ALTER TABLE `Modules` DROP FOREIGN KEY `Modules_ibfk_2`;

ALTER TABLE `ParameterSets` DROP FOREIGN KEY `ParameterSets_ibfk_1`;

ALTER TABLE `Parameters` DROP FOREIGN KEY `Parameters_ibfk_1`;

ALTER TABLE `ServiceTemplates` DROP FOREIGN KEY `ServiceTemplates_ibfk_1`;

ALTER TABLE `StringParamValues` DROP FOREIGN KEY `StringParamValues_ibfk_1`;

ALTER TABLE `SuperIdParamSetAssoc` DROP FOREIGN KEY `SuperIdParamSetAssoc_ibfk_1`;

ALTER TABLE `SuperIdParamSetAssoc` DROP FOREIGN KEY `SuperIdParamSetAssoc_ibfk_2`;

ALTER TABLE `SuperIdParameterAssoc` DROP FOREIGN KEY `SuperIdParameterAssoc_ibfk_1`;

ALTER TABLE `SuperIdParameterAssoc` DROP FOREIGN KEY `SuperIdParameterAssoc_ibfk_2`;

ALTER TABLE `SuperIdReleaseAssoc` DROP FOREIGN KEY `SuperIdReleaseAssoc_ibfk_1`;

ALTER TABLE `SuperIdReleaseAssoc` DROP FOREIGN KEY `SuperIdReleaseAssoc_ibfk_2`;

ALTER TABLE `SuperIdVecParamSetAssoc` DROP FOREIGN KEY `SuperIdVecParamSetAssoc_ibfk_1`;

ALTER TABLE `SuperIdVecParamSetAssoc` DROP FOREIGN KEY `SuperIdVecParamSetAssoc_ibfk_2`;

ALTER TABLE `UInt32ParamValues` DROP FOREIGN KEY `UInt32ParamValues_ibfk_1`;

ALTER TABLE `VDoubleParamValues` DROP FOREIGN KEY `VDoubleParamValues_ibfk_1`;

ALTER TABLE `VEventIDParamValues` DROP FOREIGN KEY `VEventIDParamValues_ibfk_1`;

ALTER TABLE `VInputTagParamValues` DROP FOREIGN KEY `VInputTagParamValues_ibfk_1`;

ALTER TABLE `VInt32ParamValues` DROP FOREIGN KEY `VInt32ParamValues_ibfk_1`;

ALTER TABLE `VStringParamValues` DROP FOREIGN KEY `VStringParamValues_ibfk_1`;

ALTER TABLE `VUInt32ParamValues` DROP FOREIGN KEY `VUInt32ParamValues_ibfk_1`;

ALTER TABLE `VecParameterSets` DROP FOREIGN KEY `VecParameterSets_ibfk_1`;

# ---------------------------------------------------------------------- #
# Drop table "BoolParamValues"                                           #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `BoolParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "DoubleParamValues"                                         #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `DoubleParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "EDSourceTemplates"                                         #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `EDSourceTemplates` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `EDSourceTemplates`;

# ---------------------------------------------------------------------- #
# Drop table "ESSourceTemplates"                                         #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `ESSourceTemplates` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `ESSourceTemplates`;

# ---------------------------------------------------------------------- #
# Drop table "EventIDParamValues"                                        #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `EventIDParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "InputTagParamValues"                                       #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `InputTagParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "Int32ParamValues"                                          #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `Int32ParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "ModuleTemplates"                                           #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `ModuleTemplates` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `ModuleTemplates`;

# ---------------------------------------------------------------------- #
# Drop table "ModuleTypes"                                               #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `ModuleTypes` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `ModuleTypes`;

# ---------------------------------------------------------------------- #
# Drop table "Modules"                                                   #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `Modules` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `Modules`;

# ---------------------------------------------------------------------- #
# Drop table "ParameterSets"                                             #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `ParameterSets` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `ParameterSets`;

# ---------------------------------------------------------------------- #
# Drop table "ParameterTypes"                                            #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `ParameterTypes` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `ParameterTypes`;

# ---------------------------------------------------------------------- #
# Drop table "Parameters"                                                #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `Parameters` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `Parameters`;

# ---------------------------------------------------------------------- #
# Drop table "ServiceTemplates"                                          #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `ServiceTemplates` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `ServiceTemplates`;

# ---------------------------------------------------------------------- #
# Drop table "SoftwareReleases"                                          #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `SoftwareReleases` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `SoftwareReleases`;

# ---------------------------------------------------------------------- #
# Drop table "StringParamValues"                                         #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `StringParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "SuperIdParamSetAssoc"                                      #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `SuperIdParamSetAssoc`;

# ---------------------------------------------------------------------- #
# Drop table "SuperIdParameterAssoc"                                     #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `SuperIdParameterAssoc`;

# ---------------------------------------------------------------------- #
# Drop table "SuperIdReleaseAssoc"                                       #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `SuperIdReleaseAssoc`;

# ---------------------------------------------------------------------- #
# Drop table "SuperIdVecParamSetAssoc"                                   #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `SuperIdVecParamSetAssoc`;

# ---------------------------------------------------------------------- #
# Drop table "SuperIds"                                                  #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `SuperIds` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `SuperIds`;

# ---------------------------------------------------------------------- #
# Drop table "UInt32ParamValues"                                         #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `UInt32ParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "VDoubleParamValues"                                        #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `VDoubleParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "VEventIDParamValues"                                       #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `VEventIDParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "VInputTagParamValues"                                      #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `VInputTagParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "VInt32ParamValues"                                         #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `VInt32ParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "VStringParamValues"                                        #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `VStringParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "VUInt32ParamValues"                                        #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `VUInt32ParamValues`;

# ---------------------------------------------------------------------- #
# Drop table "VecParameterSets"                                          #
# ---------------------------------------------------------------------- #

# Drop constraints #

ALTER TABLE `VecParameterSets` DROP PRIMARY KEY;

# Drop table #

DROP TABLE `VecParameterSets`;

# ---------------------------------------------------------------------- #
# Drop table "cfgfile"                                                   #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `cfgfile`;

# ---------------------------------------------------------------------- #
# Drop table "par_double"                                                #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `par_double`;

# ---------------------------------------------------------------------- #
# Drop table "par_string"                                                #
# ---------------------------------------------------------------------- #

# Drop constraints #

# Drop table #

DROP TABLE `par_string`;
