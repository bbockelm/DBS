source setup.sh
java -cp $CLASSPATH -Djava.naming.provider.url=cmssrv48.fnal.gov:1099 -Dcertificate=/home/sekhri/mycert.p12 -Dpassword=vijayneha fnal.gov.client.Start
