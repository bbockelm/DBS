-- =================
--
--  Create the ROLES
--
-- =================
PROMPT Creating Role 'CMS_DBS_@SchemaNameAbbr@_ADMIN_ROLE'
create role  CMS_DBS_@SchemaNameAbbr@_ADMIN_ROLE NOT IDENTIFIED
/

PROMPT Creating Role 'CMS_DBS_@SchemaNameAbbr@_WRITER_ROLE'
create role  CMS_DBS_@SchemaNameAbbr@_WRITER_ROLE NOT IDENTIFIED
/

PROMPT Creating Role 'CMS_DBS_@SchemaNameAbbr@_READER_ROLE'
create role  CMS_DBS_@SchemaNameAbbr@_READER_ROLE NOT IDENTIFIED
/

PROMPT Creating Role 'CMS_DBS_@SchemaNameAbbr@_DLS_ROLE'
create role  CMS_DBS_@SchemaNameAbbr@_DLS_ROLE NOT IDENTIFIED
/


GRANT CMS_DBS_@SchemaNameAbbr@_READER_ROLE TO CMS_DBS_@SchemaNameAbbr@_WRITER_ROLE
/

GRANT CMS_DBS_@SchemaNameAbbr@_DLS_ROLE TO CMS_DBS_@SchemaNameAbbr@_WRITER_ROLE
/

GRANT CMS_DBS_@SchemaNameAbbr@_WRITER_ROLE TO CMS_DBS_@SchemaNameAbbr@_ADMIN_ROLE
/

GRANT CMS_DBS_@SchemaNameAbbr@_ADMIN_ROLE TO @Schema_Owner@_ADMIN
/

GRANT CMS_DBS_@SchemaNameAbbr@_WRITER_ROLE TO @Schema_Owner@_WRITER
/

GRANT CMS_DBS_@SchemaNameAbbr@_READER_ROLE TO @Schema_Owner@_READER
/
-- ==================================
-- 
--  Granting Permissions to tables
--
-- ==================================
PROMPT Granting Permissions

-- ================Granting Permissions to CMS_DBS_@SchemaNameAbbr@_READER_ROLE =============
set serveroutput on size 100000
BEGIN
 FOR o IN (SELECT table_name name FROM user_tables) LOOP
      dbms_output.put_line ('Changing permissions on ' || o.name || ' for CMS_DBS_@SchemaNameAbbr@_READER_ROLE');
      execute immediate 'grant select on ' || o.name || ' to CMS_DBS_@SchemaNameAbbr@_READER_ROLE';
   END LOOP;
END;
/

-- ================Granting Permissions CMS_DBS_@SchemaNameAbbr@_WRITER_ROLE===============
set serveroutput on size 100000
BEGIN
   FOR o IN (SELECT table_name name FROM user_tables) LOOP
      dbms_output.put_line ('Changing permissions on ' || o.name || ' for CMS_DBS_@SchemaNameAbbr@_WRITER_ROLE');
      execute immediate 'grant insert, update on ' || o.name || ' to CMS_DBS_@SchemaNameAbbr@_WRITER_ROLE';
   END LOOP;
END;
/
#
-- ================Granting Permissions CMS_DBS_@SchemaNameAbbr@_ADMIN_ROLE ==============="  >> $ddl_file
set serveroutput on size 100000
BEGIN
   FOR o IN (SELECT table_name name FROM user_tables) LOOP
      dbms_output.put_line ('Changing permissions on ' || o.name || ' for CMS_DBS_@SchemaNameAbbr@_ADMIN_ROLE');
      execute immediate 'grant delete on ' || o.name || ' to CMS_DBS_@SchemaNameAbbr@_ADMIN_ROLE';
   END LOOP;
END;
/

-- This is for DLS

grant delete on Block to CMS_DBS_@SchemaNameAbbr@_DLS_ROLE
/
grant delete on SEBlock to CMS_DBS_@SchemaNameAbbr@_DLS_ROLE
/
grant delete on STORAGEELEMENT to CMS_DBS_@SchemaNameAbbr@_DLS_ROLE
/