# DBS-NeXtGen-MySQL.sql is the .sql file generated from 
# ../DBS-NeXtGen-MySQL.druid using DRUID Tool

echo 
echo "   Inserting auto_increment for mysql"
cat DBS-NeXtGen-MySQL.sql | sed -e "s%ID                    int%ID                    int not null auto_increment%g" > DBS-NeXtGen-MySQL_DEPLOYABLE.sql
echo "   Deploy DBS-NeXtGen-MySQL_DEPLOYABLE.sql to MySQL"
echo "   mysql -u username -ppassword  ...  < DBS-NeXtGen-MySQL_DEPLOYABLE.sql"
echo

 
