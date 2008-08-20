Scripts used to send log information form server machines and collect and make plots on monitor machine            

Logger machine scripts

copy_httpd_logs_from_var.sh  - copy httpd access_logs to DBS access httpd/ area
                               Note: this runs under root cron

copy_to_cmsdbs.crontab       - cmsdbs cron entry to copy logs to monitor
copy_to_cmsdbs.sh            - script to do copy. must have rss certificates 
                               setup properly so scp will work to monitor machine.
directory
/home/cmsdbs/src/copylogs

Collector machine scripts:

index.html                   - html page index for plots

DBSTree.C                    - Makes the ROOT tree
DBSTreePlots.C               - Generates the plots form the ROOT tree
                   
          - 
reform-dbs2-log.py           - reformats the log files to compact format. 
                               (used by makeplot*.sh)
makeplots.crontab

makeplots-today.sh 
makeplots-yesterday.sh
makeplots-monthtodate.sh        

directory structure
/data/dbs_logs/              - where logs are sent
/data/dbs_logs/src           -source area, where these scripts live
/data/dbs_logs/temp          - work area
