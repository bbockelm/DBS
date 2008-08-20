This directory has all the scripts used to send the frontier logging 
information to the collector (monitor) node and make the plots. 

Logger node:

copy-logs-to-monitor.crontab   -  line to add to the crontab on the logger 
                                  machine
copy-logs-to-monitor.sh        -  script that runs the reformatter script and 
                                  scp's output to  collector node
reform-squid-log-offset.py     -  reformatter script, reads the squid 
                                  access_log and reformats to compact form. 
directory
/home/dbfrontier/local/etc     - place where this all lives

Collector node:

SquidTree.C                    - make ROOT tree file
SquidTreePlots.C               - Read ROOT tree and make plots
index.html                     - html index for web page
makeplots-today.sh             - make the plots for today
makeplots-today.crontab        - line to add to crontab on collector machine

directory structure
/data/squid_logs/src  location of src
/data/squid_logs      location of logs
/data/squid_logs/temp working directory


Instructions for use.

Put the logger script on the logger machine and enter the crontab entry.
Put the collector scripts on the collector machine and entere the crontab.

be sure all the locations specified in the scripts exist. 