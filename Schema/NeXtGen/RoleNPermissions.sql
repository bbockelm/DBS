-- =================
--
--  Create the ROLES
--
-- =================
PROMPT Creating Role 'CMS_DBS_SchemaNameAbbr_ADMIN_ROLE'
create role  CMS_DBS_SchemaNameAbbr_ADMIN_ROLE NOT IDENTIFIED
/

PROMPT Creating Role 'CMS_DBS_SchemaNameAbbr_WRITER_ROLE'
create role  CMS_DBS_SchemaNameAbbr_WRITER_ROLE NOT IDENTIFIED
/

PROMPT Creating Role 'CMS_DBS_SchemaNameAbbr_READER_ROLE'
create role  CMS_DBS_SchemaNameAbbr_READER_ROLE NOT IDENTIFIED
/

PROMPT Creating Role 'CMS_DBS_SchemaNameAbbr_DLS_ROLE'
create role  CMS_DBS_SchemaNameAbbr_DLS_ROLE NOT IDENTIFIED
/


GRANT CMS_DBS_SchemaNameAbbr_READER_ROLE TO CMS_DBS_SchemaNameAbbr_WRITER_ROLE
/

GRANT CMS_DBS_SchemaNameAbbr_DLS_ROLE TO CMS_DBS_SchemaNameAbbr_WRITER_ROLE
/

GRANT CMS_DBS_SchemaNameAbbr_WRITER_ROLE TO CMS_DBS_SchemaNameAbbr_ADMIN_ROLE
/

GRANT CMS_DBS_SchemaNameAbbr_ADMIN_ROLE TO CMS_DBS_XXXX_XXXXXX_ADMIN
/

GRANT CMS_DBS_SchemaNameAbbr_WRITER_ROLE TO CMS_DBS_XXXX_XXXXXX_WRITER
/

GRANT CMS_DBS_SchemaNameAbbr_READER_ROLE TO CMS_DBS_XXXX_XXXXXX_READER
/
-- ==================================
-- 
--  Granting Permissions to tables
--
-- ==================================
PROMPT Granting Permissions

-- ================Granting Permissions to CMS_DBS_SchemaNameAbbr_READER_ROLE =============
set serveroutput on size 100000
BEGIN
 FOR o IN (SELECT table_name name FROM user_tables) LOOP
      dbms_output.put_line ('Changing permissions on ' || o.name || ' for CMS_DBS_SchemaNameAbbr_READER_ROLE');
      execute immediate 'grant select on ' || o.name || ' to CMS_DBS_SchemaNameAbbr_READER_ROLE';
   END LOOP;
END;
/

-- ================Granting Permissions CMS_DBS_SchemaNameAbbr_WRITER_ROLE===============
set serveroutput on size 100000
BEGIN
   FOR o IN (SELECT table_name name FROM user_tables) LOOP
      dbms_output.put_line ('Changing permissions on ' || o.name || ' for CMS_DBS_SchemaNameAbbr_WRITER_ROLE');
      execute immediate 'grant insert, update on ' || o.name || ' to CMS_DBS_SchemaNameAbbr_WRITER_ROLE';
   END LOOP;
END;
/
#
-- ================Granting Permissions CMS_DBS_SchemaNameAbbr_ADMIN_ROLE ==============="  >> $ddl_file
set serveroutput on size 100000
BEGIN
   FOR o IN (SELECT table_name name FROM user_tables) LOOP
      dbms_output.put_line ('Changing permissions on ' || o.name || ' for CMS_DBS_SchemaNameAbbr_ADMIN_ROLE');
      execute immediate 'grant delete on ' || o.name || ' to CMS_DBS_SchemaNameAbbr_ADMIN_ROLE';
   END LOOP;
END;
/

-- This is for DLS

grant delete on Block to CMS_DBS_SchemaNameAbbr_DLS_ROLE
/
grant delete on SEBlock to CMS_DBS_SchemaNameAbbr_DLS_ROLE
/
grant delete on STORAGEELEMENT to CMS_DBS_SchemaNameAbbr_DLS_ROLE
/
#
#

