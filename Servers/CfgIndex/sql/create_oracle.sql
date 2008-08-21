/* ---------------------------------------------------------------------- */
/* Script generated with: DeZign for Databases v5.1.1                     */
/* Target DBMS:           Oracle 10g                                      */
/* Project file:          DBSSearch.dez                                   */
/* Project name:                                                          */
/* Author:                                                                */
/* Script type:           Database creation script                        */
/* Created on:            2008-06-25 15:01                                */
/* Model version:         Version 2008-08-21                              */
/* ---------------------------------------------------------------------- */


/* ---------------------------------------------------------------------- */
/* Tables                                                                 */
/* ---------------------------------------------------------------------- */


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
    pval CLOB CONSTRAINT par_stringNN_pval NOT NULL
);

CREATE INDEX i_par_string_md5 ON par_string (md5);

CREATE INDEX i_par_string_pname ON par_string (pname);

CREATE INDEX i_par_string_pval ON par_string (pval);

/* ---------------------------------------------------------------------- */
/* Foreign key constraints                                                */
/* ---------------------------------------------------------------------- */

