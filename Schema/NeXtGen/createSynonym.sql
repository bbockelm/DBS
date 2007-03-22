set feedback off
SET HEADING OFF
SET NEWPAGE NONE
set serveroutput on size 1000000;
--NOLIST;
spool privateSyn.sql
DECLARE
  owner varchar2(50);
BEGIN
   -- Loop over Tables
   FOR t IN (SELECT table_name FROM user_tables) LOOP
      dbms_output.put_line ('Drop synonym '|| t.table_name);
      dbms_output.put_line ('/');
   END LOOP;
   --Get owner name
   SELECT username into owner FROM user_users;         
   -- loop over Tables
   FOR t IN (SELECT table_name FROM user_tables) LOOP
      dbms_output.put_line ('Create synonym ' || t.table_name || ' for ' ||owner||'.' || t.table_name);
      dbms_output.put_line ('/');
   END LOOP;
END;
/
spool off
set feedback on