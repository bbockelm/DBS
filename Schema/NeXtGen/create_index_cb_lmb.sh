all_tables=`cat DBS-NeXtGen-Oracle_DEPLOYABLE.sql|grep "CREATE TABLE"| awk '{print $3}'`
for atable in $all_tables; do
	echo "CREATE INDEX ix_lmb_$atable on $atable(LastModifiedBy);" >> cb_lmb_indx.sql
	echo "CREATE INDEX ix_cb_$atable on $atable(CreatedBy);" >> cb_lmb_indx.sql
	echo >> cb_lmb_indx.sql
done

