#!/bin/sh
echo "This great script written on Nov 11th, 2008 upgrades DBS DB instances to DBS_2_0_4 (Schema version DBS_1_1_3"
if [ -z "$1" ]; then
	echo
	echo "		Oye !!, provide the ROLE ABBR"
	echo "		$0 <roleabbr>"
	echo "		example:		$0 PRODT0"
	echo
	exit 1
fi

sqlfile=$1.sql
echo "Generating ... $sqlfile"
cat upgradeSchema4DBS_2_0_3.sql | sed -e "s%__dbabbr__%${1}%g" > $sqlfile
echo "Generated ... $sqlfile"
echo "Now use sqlplus ...."
exit 0

