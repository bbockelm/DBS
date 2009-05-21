#!/bin/sh

echo "Create views for ORACLE instances"
for dbs in "CMS_DBS_PROD_GLOBAL"
do
    cat view_templates.sql \
    | sed "s/@build.schema.owner.name@/$dbs/" > oracle_views_$dbs.sql
done

echo "Create views for MySQL"
cat view_templates.sql | grep -v -i prompt \
| perl -0777 -ple 's!(_READER)\n/!$1!sg' \
| grep -v -i grant | sed "s/\//;/g" \
| sed "s/Drop view/drop view if exists/g" > mysql_views.sql
