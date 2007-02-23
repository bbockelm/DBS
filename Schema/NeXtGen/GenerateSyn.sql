set serveroutput on size 7000000;
set feedback off
spool privateSyn.sql

BEGIN
   -- Loop over Tables
   FOR t IN (SELECT table_name FROM user_tables) LOOP
      dbms_output.put_line ('Drop synonym '|| t.table_name || ';');
      dbms_output.put_line ('/');
   END LOOP;
END;
/ 

DECLARE
  owner varchar2(50);
BEGIN
   --Get owner name
   SELECT username into owner FROM user_users;         
   -- loop over Tables
   FOR t IN (SELECT table_name FROM user_tables) LOOP
      dbms_output.put_line ('Create synonym ' || t.table_name || ' for ' ||owner||'.' || t.table_name || ';');
      dbms_output.put_line ('/');
   END LOOP;
END;
/
set feedback on
spool off
