# ---------------------------------------------------------------------- #
# Script generated with: DeZign for Databases v5.1.1                     #
# Target DBMS:           MySQL 5                                         #
# Project file:          DBSSearch.dez                                   #
# Project name:                                                          #
# Author:                                                                #
# Script type:           Database creation script                        #
# Created on:            2008-05-29 17:12                                #
# Model version:         Version 2008-05-29 1                            #
# ---------------------------------------------------------------------- #


# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #

# ---------------------------------------------------------------------- #
# Add table "BoolParamValues"                                            #
# ---------------------------------------------------------------------- #

CREATE TABLE `BoolParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `value` TINYINT(1) NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE UNIQUE INDEX `paramId` ON `BoolParamValues` (`paramId`);

# ---------------------------------------------------------------------- #
# Add table "DoubleParamValues"                                          #
# ---------------------------------------------------------------------- #

CREATE TABLE `DoubleParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `value` DOUBLE NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE UNIQUE INDEX `paramId` ON `DoubleParamValues` (`paramId`);

# ---------------------------------------------------------------------- #
# Add table "EDSourceTemplates"                                          #
# ---------------------------------------------------------------------- #

CREATE TABLE `EDSourceTemplates` (
    `superId` BIGINT(20) UNSIGNED NOT NULL,
    `name` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    `cvstag` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    CONSTRAINT `PK_EDSourceTemplates` PRIMARY KEY (`superId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`)';

CREATE UNIQUE INDEX `superId` ON `EDSourceTemplates` (`superId`);

# ---------------------------------------------------------------------- #
# Add table "ESSourceTemplates"                                          #
# ---------------------------------------------------------------------- #

CREATE TABLE `ESSourceTemplates` (
    `superId` BIGINT(20) UNSIGNED NOT NULL,
    `name` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    `cvstag` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    CONSTRAINT `PK_ESSourceTemplates` PRIMARY KEY (`superId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`)';

CREATE UNIQUE INDEX `superId` ON `ESSourceTemplates` (`superId`);

# ---------------------------------------------------------------------- #
# Add table "EventIDParamValues"                                         #
# ---------------------------------------------------------------------- #

CREATE TABLE `EventIDParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `value` VARCHAR(32) CHARACTER SET utf8 NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE INDEX `paramId` ON `EventIDParamValues` (`paramId`);

# ---------------------------------------------------------------------- #
# Add table "InputTagParamValues"                                        #
# ---------------------------------------------------------------------- #

CREATE TABLE `InputTagParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `value` VARCHAR(64) CHARACTER SET utf8 NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE INDEX `paramId` ON `InputTagParamValues` (`paramId`);

# ---------------------------------------------------------------------- #
# Add table "Int32ParamValues"                                           #
# ---------------------------------------------------------------------- #

CREATE TABLE `Int32ParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `value` BIGINT(20) NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE UNIQUE INDEX `paramId` ON `Int32ParamValues` (`paramId`);

# ---------------------------------------------------------------------- #
# Add table "ModuleTemplates"                                            #
# ---------------------------------------------------------------------- #

CREATE TABLE `ModuleTemplates` (
    `superId` BIGINT(20) UNSIGNED NOT NULL,
    `typeId` BIGINT(20) UNSIGNED NOT NULL,
    `name` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    `cvstag` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    CONSTRAINT `PK_ModuleTemplates` PRIMARY KEY (`superId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`typeId`) RE';

CREATE UNIQUE INDEX `superId` ON `ModuleTemplates` (`superId`);

CREATE INDEX `typeId` ON `ModuleTemplates` (`typeId`);

# ---------------------------------------------------------------------- #
# Add table "ModuleTypes"                                                #
# ---------------------------------------------------------------------- #

CREATE TABLE `ModuleTypes` (
    `typeId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(32) CHARACTER SET utf8 NOT NULL,
    CONSTRAINT `PK_ModuleTypes` PRIMARY KEY (`typeId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB';

CREATE UNIQUE INDEX `typeId` ON `ModuleTypes` (`typeId`);

CREATE UNIQUE INDEX `type` ON `ModuleTypes` (`type`);

# ---------------------------------------------------------------------- #
# Add table "Modules"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `Modules` (
    `superId` BIGINT(20) UNSIGNED NOT NULL,
    `templateId` BIGINT(20) UNSIGNED NOT NULL,
    `name` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    CONSTRAINT `PK_Modules` PRIMARY KEY (`superId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`templateId`';

CREATE UNIQUE INDEX `superId` ON `Modules` (`superId`);

CREATE INDEX `templateId` ON `Modules` (`templateId`);

# ---------------------------------------------------------------------- #
# Add table "ParameterSets"                                              #
# ---------------------------------------------------------------------- #

CREATE TABLE `ParameterSets` (
    `superId` BIGINT(20) UNSIGNED NOT NULL,
    `name` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    `tracked` TINYINT(1) NOT NULL,
    CONSTRAINT `PK_ParameterSets` PRIMARY KEY (`superId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`)';

CREATE UNIQUE INDEX `superId` ON `ParameterSets` (`superId`);

# ---------------------------------------------------------------------- #
# Add table "ParameterTypes"                                             #
# ---------------------------------------------------------------------- #

CREATE TABLE `ParameterTypes` (
    `paramTypeId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `paramType` VARCHAR(32) CHARACTER SET utf8 NOT NULL,
    CONSTRAINT `PK_ParameterTypes` PRIMARY KEY (`paramTypeId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB';

CREATE UNIQUE INDEX `paramTypeId` ON `ParameterTypes` (`paramTypeId`);

CREATE UNIQUE INDEX `paramType` ON `ParameterTypes` (`paramType`);

# ---------------------------------------------------------------------- #
# Add table "Parameters"                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE `Parameters` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `paramTypeId` BIGINT(20) UNSIGNED NOT NULL,
    `name` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    `tracked` TINYINT(1) NOT NULL,
    CONSTRAINT `PK_Parameters` PRIMARY KEY (`paramId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramTypeId`) REFER `dbs/ParameterTypes`(`paramTypeId`)';

CREATE UNIQUE INDEX `paramId` ON `Parameters` (`paramId`);

CREATE INDEX `paramTypeId` ON `Parameters` (`paramTypeId`);

# ---------------------------------------------------------------------- #
# Add table "ServiceTemplates"                                           #
# ---------------------------------------------------------------------- #

CREATE TABLE `ServiceTemplates` (
    `superId` BIGINT(20) UNSIGNED NOT NULL,
    `name` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    `cvstag` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    CONSTRAINT `PK_ServiceTemplates` PRIMARY KEY (`superId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`)';

CREATE UNIQUE INDEX `superId` ON `ServiceTemplates` (`superId`);

# ---------------------------------------------------------------------- #
# Add table "SoftwareReleases"                                           #
# ---------------------------------------------------------------------- #

CREATE TABLE `SoftwareReleases` (
    `releaseId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `releaseTag` VARCHAR(32) CHARACTER SET utf8 NOT NULL,
    CONSTRAINT `PK_SoftwareReleases` PRIMARY KEY (`releaseId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB';

CREATE UNIQUE INDEX `releaseId` ON `SoftwareReleases` (`releaseId`);

CREATE UNIQUE INDEX `releaseTag` ON `SoftwareReleases` (`releaseTag`);

# ---------------------------------------------------------------------- #
# Add table "StringParamValues"                                          #
# ---------------------------------------------------------------------- #

CREATE TABLE `StringParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `value` VARCHAR(256) CHARACTER SET utf8 NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE UNIQUE INDEX `paramId` ON `StringParamValues` (`paramId`);

# ---------------------------------------------------------------------- #
# Add table "SuperIdParamSetAssoc"                                       #
# ---------------------------------------------------------------------- #

CREATE TABLE `SuperIdParamSetAssoc` (
    `superId` BIGINT(20) UNSIGNED NOT NULL,
    `paramSetId` BIGINT(20) UNSIGNED NOT NULL,
    `sequenceNb` SMALLINT(5) UNSIGNED NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`paramSetId`';

CREATE INDEX `superId` ON `SuperIdParamSetAssoc` (`superId`);

CREATE INDEX `paramSetId` ON `SuperIdParamSetAssoc` (`paramSetId`);

# ---------------------------------------------------------------------- #
# Add table "SuperIdParameterAssoc"                                      #
# ---------------------------------------------------------------------- #

CREATE TABLE `SuperIdParameterAssoc` (
    `superId` BIGINT(20) UNSIGNED NOT NULL,
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `sequenceNb` SMALLINT(5) UNSIGNED NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`paramId`) R';

CREATE INDEX `superId` ON `SuperIdParameterAssoc` (`superId`);

CREATE INDEX `paramId` ON `SuperIdParameterAssoc` (`paramId`);

# ---------------------------------------------------------------------- #
# Add table "SuperIdReleaseAssoc"                                        #
# ---------------------------------------------------------------------- #

CREATE TABLE `SuperIdReleaseAssoc` (
    `superId` BIGINT(20) UNSIGNED NOT NULL,
    `releaseId` BIGINT(20) UNSIGNED NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`releaseId`)';

CREATE INDEX `superId` ON `SuperIdReleaseAssoc` (`superId`);

CREATE INDEX `releaseId` ON `SuperIdReleaseAssoc` (`releaseId`);

# ---------------------------------------------------------------------- #
# Add table "SuperIdVecParamSetAssoc"                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SuperIdVecParamSetAssoc` (
    `superId` BIGINT(20) UNSIGNED NOT NULL,
    `vecParamSetId` BIGINT(20) UNSIGNED NOT NULL,
    `sequenceNb` SMALLINT(5) UNSIGNED NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`); (`vecParamSet';

CREATE INDEX `superId` ON `SuperIdVecParamSetAssoc` (`superId`);

CREATE INDEX `vecParamSetId` ON `SuperIdVecParamSetAssoc` (`vecParamSetId`);

# ---------------------------------------------------------------------- #
# Add table "SuperIds"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `SuperIds` (
    `superId` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    CONSTRAINT `PK_SuperIds` PRIMARY KEY (`superId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB';

CREATE UNIQUE INDEX `superId` ON `SuperIds` (`superId`);

# ---------------------------------------------------------------------- #
# Add table "UInt32ParamValues"                                          #
# ---------------------------------------------------------------------- #

CREATE TABLE `UInt32ParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `value` BIGINT(20) UNSIGNED NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE UNIQUE INDEX `paramId` ON `UInt32ParamValues` (`paramId`);

# ---------------------------------------------------------------------- #
# Add table "VDoubleParamValues"                                         #
# ---------------------------------------------------------------------- #

CREATE TABLE `VDoubleParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `sequenceNb` SMALLINT(5) UNSIGNED NOT NULL,
    `value` DOUBLE NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE UNIQUE INDEX `paramId` ON `VDoubleParamValues` (`paramId`,`sequenceNb`);

# ---------------------------------------------------------------------- #
# Add table "VEventIDParamValues"                                        #
# ---------------------------------------------------------------------- #

CREATE TABLE `VEventIDParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `sequenceNb` SMALLINT(5) UNSIGNED NOT NULL,
    `value` VARCHAR(32) CHARACTER SET utf8 NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE INDEX `paramId` ON `VEventIDParamValues` (`paramId`);

# ---------------------------------------------------------------------- #
# Add table "VInputTagParamValues"                                       #
# ---------------------------------------------------------------------- #

CREATE TABLE `VInputTagParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `sequenceNb` SMALLINT(5) UNSIGNED NOT NULL,
    `value` VARCHAR(64) CHARACTER SET utf8 NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE INDEX `paramId` ON `VInputTagParamValues` (`paramId`);

# ---------------------------------------------------------------------- #
# Add table "VInt32ParamValues"                                          #
# ---------------------------------------------------------------------- #

CREATE TABLE `VInt32ParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `sequenceNb` SMALLINT(5) UNSIGNED NOT NULL,
    `value` BIGINT(20) NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE UNIQUE INDEX `paramId` ON `VInt32ParamValues` (`paramId`,`sequenceNb`);

# ---------------------------------------------------------------------- #
# Add table "VStringParamValues"                                         #
# ---------------------------------------------------------------------- #

CREATE TABLE `VStringParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `sequenceNb` SMALLINT(5) UNSIGNED NOT NULL,
    `value` VARCHAR(256) CHARACTER SET utf8 NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE UNIQUE INDEX `paramId` ON `VStringParamValues` (`paramId`,`sequenceNb`);

# ---------------------------------------------------------------------- #
# Add table "VUInt32ParamValues"                                         #
# ---------------------------------------------------------------------- #

CREATE TABLE `VUInt32ParamValues` (
    `paramId` BIGINT(20) UNSIGNED NOT NULL,
    `sequenceNb` SMALLINT(5) UNSIGNED NOT NULL,
    `value` BIGINT(20) UNSIGNED NOT NULL
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`paramId`) REFER `dbs/Parameters`(`paramId`)';

CREATE UNIQUE INDEX `paramId` ON `VUInt32ParamValues` (`paramId`,`sequenceNb`);

# ---------------------------------------------------------------------- #
# Add table "VecParameterSets"                                           #
# ---------------------------------------------------------------------- #

CREATE TABLE `VecParameterSets` (
    `superId` BIGINT(20) UNSIGNED NOT NULL,
    `name` VARCHAR(64) CHARACTER SET utf8 NOT NULL,
    `tracked` TINYINT(1) NOT NULL,
    CONSTRAINT `PK_VecParameterSets` PRIMARY KEY (`superId`)
)
TYPE = INNODB COMMENT = 'InnoDB free: 8192 kB; (`superId`) REFER `dbs/SuperIds`(`superId`)';

CREATE UNIQUE INDEX `superId` ON `VecParameterSets` (`superId`);

# ---------------------------------------------------------------------- #
# Add table "cfgfile"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `cfgfile` (
    `filename` VARCHAR(256) CHARACTER SET utf8 NOT NULL,
    `md5` VARCHAR(32) CHARACTER SET utf8 NOT NULL
)
TYPE = MYISAM;

CREATE UNIQUE INDEX `i_cfgfile_filename` ON `cfgfile` (`filename`);

CREATE UNIQUE INDEX `i_cfgfile_md5` ON `cfgfile` (`md5`);

# ---------------------------------------------------------------------- #
# Add table "par_double"                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE `par_double` (
    `md5` VARCHAR(32) CHARACTER SET utf8 NOT NULL,
    `pname` VARCHAR(256) CHARACTER SET utf8 NOT NULL,
    `pval` DOUBLE NOT NULL
)
TYPE = MYISAM;

CREATE INDEX `i_par_double_pname` ON `par_double` (`pname`);

CREATE INDEX `i_par_double_pval` ON `par_double` (`pval`);

CREATE INDEX `i_par_double_md5` ON `par_double` (`md5`);

# ---------------------------------------------------------------------- #
# Add table "par_string"                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE `par_string` (
    `md5` VARCHAR(32) CHARACTER SET utf8 NOT NULL,
    `pname` VARCHAR(256) CHARACTER SET utf8 NOT NULL,
    `pval` VARCHAR(256) CHARACTER SET utf8 NOT NULL
)
TYPE = MYISAM;

CREATE INDEX `i_par_string_md5` ON `par_string` (`md5`);

CREATE INDEX `i_par_string_pname` ON `par_string` (`pname`);

CREATE INDEX `i_par_string_pval` ON `par_string` (`pval`);

# ---------------------------------------------------------------------- #
# Foreign key constraints                                                #
# ---------------------------------------------------------------------- #

ALTER TABLE `BoolParamValues` ADD CONSTRAINT `BoolParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `DoubleParamValues` ADD CONSTRAINT `DoubleParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `EDSourceTemplates` ADD CONSTRAINT `EDSourceTemplates_ibfk_1` 
    FOREIGN KEY (`superId`) REFERENCES `SuperIds` (`superId`);

ALTER TABLE `ESSourceTemplates` ADD CONSTRAINT `ESSourceTemplates_ibfk_1` 
    FOREIGN KEY (`superId`) REFERENCES `SuperIds` (`superId`);

ALTER TABLE `EventIDParamValues` ADD CONSTRAINT `EventIDParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `InputTagParamValues` ADD CONSTRAINT `InputTagParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `Int32ParamValues` ADD CONSTRAINT `Int32ParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `ModuleTemplates` ADD CONSTRAINT `ModuleTemplates_ibfk_1` 
    FOREIGN KEY (`superId`) REFERENCES `SuperIds` (`superId`);

ALTER TABLE `ModuleTemplates` ADD CONSTRAINT `ModuleTemplates_ibfk_2` 
    FOREIGN KEY (`typeId`) REFERENCES `ModuleTypes` (`typeId`);

ALTER TABLE `Modules` ADD CONSTRAINT `Modules_ibfk_1` 
    FOREIGN KEY (`superId`) REFERENCES `SuperIds` (`superId`);

ALTER TABLE `Modules` ADD CONSTRAINT `Modules_ibfk_2` 
    FOREIGN KEY (`templateId`) REFERENCES `ModuleTemplates` (`superId`);

ALTER TABLE `ParameterSets` ADD CONSTRAINT `ParameterSets_ibfk_1` 
    FOREIGN KEY (`superId`) REFERENCES `SuperIds` (`superId`);

ALTER TABLE `Parameters` ADD CONSTRAINT `Parameters_ibfk_1` 
    FOREIGN KEY (`paramTypeId`) REFERENCES `ParameterTypes` (`paramTypeId`);

ALTER TABLE `ServiceTemplates` ADD CONSTRAINT `ServiceTemplates_ibfk_1` 
    FOREIGN KEY (`superId`) REFERENCES `SuperIds` (`superId`);

ALTER TABLE `StringParamValues` ADD CONSTRAINT `StringParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `SuperIdParamSetAssoc` ADD CONSTRAINT `SuperIdParamSetAssoc_ibfk_1` 
    FOREIGN KEY (`superId`) REFERENCES `SuperIds` (`superId`);

ALTER TABLE `SuperIdParamSetAssoc` ADD CONSTRAINT `SuperIdParamSetAssoc_ibfk_2` 
    FOREIGN KEY (`paramSetId`) REFERENCES `ParameterSets` (`superId`);

ALTER TABLE `SuperIdParameterAssoc` ADD CONSTRAINT `SuperIdParameterAssoc_ibfk_1` 
    FOREIGN KEY (`superId`) REFERENCES `SuperIds` (`superId`);

ALTER TABLE `SuperIdParameterAssoc` ADD CONSTRAINT `SuperIdParameterAssoc_ibfk_2` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `SuperIdReleaseAssoc` ADD CONSTRAINT `SuperIdReleaseAssoc_ibfk_1` 
    FOREIGN KEY (`superId`) REFERENCES `SuperIds` (`superId`);

ALTER TABLE `SuperIdReleaseAssoc` ADD CONSTRAINT `SuperIdReleaseAssoc_ibfk_2` 
    FOREIGN KEY (`releaseId`) REFERENCES `SoftwareReleases` (`releaseId`);

ALTER TABLE `SuperIdVecParamSetAssoc` ADD CONSTRAINT `SuperIdVecParamSetAssoc_ibfk_1` 
    FOREIGN KEY (`superId`) REFERENCES `SuperIds` (`superId`);

ALTER TABLE `SuperIdVecParamSetAssoc` ADD CONSTRAINT `SuperIdVecParamSetAssoc_ibfk_2` 
    FOREIGN KEY (`vecParamSetId`) REFERENCES `VecParameterSets` (`superId`);

ALTER TABLE `UInt32ParamValues` ADD CONSTRAINT `UInt32ParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `VDoubleParamValues` ADD CONSTRAINT `VDoubleParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `VEventIDParamValues` ADD CONSTRAINT `VEventIDParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `VInputTagParamValues` ADD CONSTRAINT `VInputTagParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `VInt32ParamValues` ADD CONSTRAINT `VInt32ParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `VStringParamValues` ADD CONSTRAINT `VStringParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `VUInt32ParamValues` ADD CONSTRAINT `VUInt32ParamValues_ibfk_1` 
    FOREIGN KEY (`paramId`) REFERENCES `Parameters` (`paramId`);

ALTER TABLE `VecParameterSets` ADD CONSTRAINT `VecParameterSets_ibfk_1` 
    FOREIGN KEY (`superId`) REFERENCES `SuperIds` (`superId`);
