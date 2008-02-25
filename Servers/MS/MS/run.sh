cd /home/sekhri/DBS/Servers/MS/MS/
source setup.sh
/home/sekhri/j2ee/SDK/jdk/bin/java -cp $CLASSPATH -Djava.naming.provider.url=cmssrv48.fnal.gov:1099 -Dcertificate=/home/sekhri/mycert.p12 -Dpassword=vijayneha fnal.gov.client.Start
