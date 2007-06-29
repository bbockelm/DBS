----------------------------------------------------------------------
-- Simple script to convert FLOAT to NUMBER columns CREATIONDATE, LASTMODIFICATIONDATE 
-- Probably one time fix 
---------------------------------------------------------------------
set serveroutput on size 100000
 show serveroutput
BEGIN
   -- Tables
   FOR o IN (SELECT table_name name FROM user_tables) LOOP
      -- dbms_output.put_line ('Changing Data Types for table ' || o.name || ' for CREATIONDATE and LASTMODIFICATIONDATE ');
      dbms_output.put_line ('Value Before Change');
      execute immediate 'select ID, CREATIONDATE, LASTMODIFICATIONDATE from ' || o.name;
      -- execute immediate 'ALTER TABLE ' || o.name || ' ADD TMP_LMDATE INTEGER';
      -- execute immediate 'ALTER TABLE ' || o.name || ' ADD TMP_CDATE INTEGER';
      -- execute immediate 'update ' || o.name || ' set TMP_LMDATE=LASTMODIFICATIONDATE';
      -- execute immediate 'update ' || o.name || ' set TMP_CDATE=CREATIONDATE';
      -- execute immediate 'ALTER TABLE ' || o.name || ' DROP COLUMN LASTMODIFICATIONDATE';
      -- execute immediate 'ALTER TABLE ' || o.name || ' DROP COLUMN CREATIONDATE';
      -- execute immediate 'ALTER TABLE ' || o.name || ' RENAME COLUMN TMP_LMDATE TO LASTMODIFICATIONDATE';
      -- execute immediate 'ALTER TABLE ' || o.name || ' RENAME COLUMN TMP_CDATE TO CREATIONDATE';
   END LOOP;
END;
/

