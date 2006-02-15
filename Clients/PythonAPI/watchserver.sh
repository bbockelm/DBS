#/bin/sh
pid=`cat .dbsProcessIDFile`
watch -n1 ps l $pid
