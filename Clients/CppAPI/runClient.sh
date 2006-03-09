#!/bin/sh
a=`date +%m%s`
./client.exe
b=`date +%m%s`
c=`eval "expr \$b - \$a"`
echo "TIME ELAPSED $c seconds"
