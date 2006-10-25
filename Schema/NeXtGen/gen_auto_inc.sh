# DBS-NeXtGen-MySQL.sql is the .sql file generated from 
# ../DBS-NeXtGen-MySQL.druid using DRUID Tool

mv DBS-NeXtGen-MySQL.sql DBS-NeXtGen-MySQL.sql.BAK
echo 
echo "   Inserting auto_increment for mysql"
cat DBS-NeXtGen-MySQL.sql.BAK | sed -e "s%ID                    int%ID                    int not null auto_increment%g" > DBS-NeXtGen-MySQL.sql
echo "   Deploy DBS-NeXtGen-MySQL.sql to MySQL"
echo "   mysql -u username -ppassword  ...  < DBS-NeXtGen-MySQL.sql"
echo

 
