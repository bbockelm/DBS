# ---------------------------------------------------------------------- #
# Script generated with: DeZign for Databases v6.1.2                     #
# Target DBMS:           MySQL 5                                         #
# Project file:          Mysql_Seq.dez                                   #
# Project name:                                                          #
# Author:                                                                #
# Script type:           Database creation script                        #
# Created on:            2010-04-19 14:34                                #
# ---------------------------------------------------------------------- #

USE CMS_DBS3;

# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #

# ---------------------------------------------------------------------- #
# Add table "SEQ_RVS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_RVS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_RVS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_PSHS"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_PSHS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_PSHS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_DRS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_DRS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_DRS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_PGS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_PGS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_PGS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_DTS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_DTS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_DTS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_PDSS"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_PDSS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_PDSS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_PDTS"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_PDTS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_PDTS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_OMCS"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_OMCS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_OMCS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_DCS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_DCS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_DCS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_DPS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_DPS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_DPS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_DSS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_DSS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_DSS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_PSDSS"                                                  #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_PSDSS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_PSDSS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_AQES"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_AQES` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_AQES` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_AES"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_AES` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_AES` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_BPS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_BPS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_BPS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_DTPS"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_DTPS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_DTPS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_PES"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_PES` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_PES` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_FLMS"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_FLMS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_FLMS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_SIS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_SIS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_SIS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_BKS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_BKS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_BKS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_FLS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_FLS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_FLS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_FPS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_FPS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_FPS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_FTS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_FTS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_FTS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_SES"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_SES` (
    `SE_ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_SES` PRIMARY KEY (`SE_ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_BSES"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_BSES` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_BSES` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_FCS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_FCS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_FCS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_AFS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_AFS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_AFS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_BHS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_BHS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_BHS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_DVS"                                                    #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_DVS` (
    `ID` BIGINT NOT NULL,
    CONSTRAINT `PK_SEQ_DVS` PRIMARY KEY (`ID`)
);

# ---------------------------------------------------------------------- #
# Add table "SEQ_BLSTS"                                                   #
# ---------------------------------------------------------------------- #

CREATE TABLE `SEQ_BLSTS` (
    `ID` INTEGER NOT NULL,
    CONSTRAINT `PK_SEQ_BLSTS` PRIMARY KEY (`ID`)
);
