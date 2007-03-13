#
#
#  Creat the ROLES
#
echo "PROMPT Creating Role 'CMS_DBS_ADMIN_ROLE'" >> $ddl_file
echo "create role  CMS_DBS_ADMIN_ROLE NOT IDENTIFIED" >> $ddl_file
echo "/" >> $ddl_file

echo "PROMPT Creating Role 'CMS_DBS_WRITER_ROLE'" >> $ddl_file
echo "create role  CMS_DBS_WRITER_ROLE NOT IDENTIFIED" >> $ddl_file
echo "/" >> $ddl_file

echo "PROMPT Creating Role 'CMS_DBS_READER_ROLE'" >> $ddl_file
echo "create role  CMS_DBS_READER_ROLE NOT IDENTIFIED" >> $ddl_file
echo "/" >> $ddl_file

echo "GRANT CMS_DBS_READER_ROLE TO CMS_DBS_WRITER_ROLE" >> $ddl_file
echo "/" >> $ddl_file

echo "GRANT CMS_DBS_WRITER_ROLE TO CMS_DBS_ADMIN_ROLE" >> $ddl_file
echo "/" >> $ddl_file

echo "GRANT CMS_DBS_ADMIN_ROLE TO CMS_DBS_INT_GLOBAL_ADMIN" >> $ddl_file
echo "/" >> $ddl_file

echo "GRANT CMS_DBS_WRITER_ROLE TO CMS_DBS_INT_GLOBAL_WRITER " >> $ddl_file
echo "/" >> $ddl_file

echo "GRANT CMS_DBS_READER_ROLE TO CMS_DBS_INT_GLOBAL_READER " >> $ddl_file
#
#
#  Granting Permissions to tables
#
echo "-- ================Granting Permissions ======================================"  >> $ddl_file
echo "PROMPT Granting Permissions"  >> $ddl_file
echo "-- ================Granting Permissions to CMS_DBS_READER_ROLE ==============="  >> $ddl_file
echo "set serveroutput on size 100000"  >> $ddl_file
echo "BEGIN"  >> $ddl_file
echo "   -- Tables"  >> $ddl_file
echo "   FOR o IN (SELECT table_name name FROM user_tables) LOOP"  >> $ddl_file
echo "      dbms_output.put_line ('Changing permissions on ' || o.name || ' for CMS_DBS_READER_ROLE');"  >> $ddl_file
echo "      execute immediate 'grant select on ' || o.name || ' to CMS_DBS_READER_ROLE';"  >> $ddl_file
echo "   END LOOP;"  >> $ddl_file
echo "END;"  >> $ddl_file
echo "/"  >> $ddl_file
#
echo "-- ================Granting Permissions CMS_DBS_WRITER_ROLE==============="  >> $ddl_file
echo "set serveroutput on size 100000"  >> $ddl_file
echo "BEGIN"  >> $ddl_file
echo "   -- Tables"  >> $ddl_file
echo "   FOR o IN (SELECT table_name name FROM user_tables) LOOP"  >> $ddl_file
echo "      dbms_output.put_line ('Changing permissions on ' || o.name || ' for CMS_DBS_WRITER_ROLE');"  >> $ddl_file
echo "      execute immediate 'grant insert, update on ' || o.name || ' to CMS_DBS_WRITER_ROLE';"  >> $ddl_file
echo "   END LOOP;"  >> $ddl_file
echo "END;"  >> $ddl_file
echo "/"  >> $ddl_file
#
echo "-- ================Granting Permissions CMS_DBS_ADMIN_ROLE ==============="  >> $ddl_file
echo "set serveroutput on size 100000"  >> $ddl_file
echo "BEGIN"  >> $ddl_file
echo "   -- Tables"  >> $ddl_file
echo "   FOR o IN (SELECT table_name name FROM user_tables) LOOP"  >> $ddl_file
echo "      dbms_output.put_line ('Changing permissions on ' || o.name || ' for CMS_DBS_ADMIN_ROLE');"  >> $ddl_file
echo "      execute immediate 'grant delete on ' || o.name || ' to CMS_DBS_ADMIN_ROLE';"  >> $ddl_file
echo "   END LOOP;"  >> $ddl_file
echo "END;"  >> $ddl_file
echo "/"  >> $ddl_file
#
#

