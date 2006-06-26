#!/bin/bash
#
# Startup script for the Apache Web Server
#
# chkconfig: - 85 15
# description: Apache is a World Wide Web server.  It is used to serve \
#	       HTML files and CGI.
# processname: httpd
# pidfile: /var/run/httpd.pid
# config: /etc/httpd/conf/httpd.conf

# Source function library.
. /etc/rc.d/init.d/functions

if [ -f /etc/sysconfig/httpd ]; then
        . /etc/sysconfig/httpd
fi

### CMS LOCAL
. /home/sekhri/sl3-self-built/DBS/Servers/CGIServer/httpd-env.sh
### CMS LOCAL END

# This will prevent initlog from swallowing up a pass-phrase prompt if
# mod_ssl needs a pass-phrase from the user.
INITLOG_ARGS=""

# Set HTTPD=/usr/sbin/httpd.worker in /etc/sysconfig/httpd to use a server
# with the thread-based "worker" MPM; BE WARNED that some modules may not
# work correctly with a thread-based MPM; notably PHP will refuse to start.

# Path to the apachectl script, server binary, and short-form for messages.
apachectl=/home/sekhri/sl3-self-built/apache/bin/apachectl
httpd=${HTTPD-/home/sekhri/sl3-self-built/apache/bin/httpd}
prog=httpd
RETVAL=0

# check for 1.3 configuration
check13 () {
	CONFFILE=/home/sekhri/sl3-self-built/apache/conf/httpd.conf
	GONE="(ServerType|BindAddress|Port|AddModule|ClearModuleList|"
	GONE="${GONE}AgentLog|RefererLog|RefererIgnore|FancyIndexing|"
	GONE="${GONE}AccessConfig|ResourceConfig)"
	if grep -Eiq "^[[:space:]]*($GONE)" $CONFFILE; then
		echo
		echo 1>&2 " Apache 1.3 configuration directives found"
		echo 1>&2 " please read /usr/share/doc/httpd-2.0.46/migration.html"
		failure "Apache 1.3 config directives test"
		echo
		exit 1
	fi
}

# The semantics of these two functions differ from the way apachectl does
# things -- attempting to start while running is a failure, and shutdown
# when not running is also a failure.  So we just do it the way init scripts
# are expected to behave here.
start() {
        echo -n $"Starting $prog: "
        check13 || exit 1
        daemon $httpd $OPTIONS
        RETVAL=$?
        echo
        [ $RETVAL = 0 ] && touch /home/sekhri/sl3-self-built/var/lock/subsys/httpd
        return $RETVAL
}
stop() {
	echo -n $"Stopping $prog: "
	killproc $httpd
	RETVAL=$?
	echo
	[ $RETVAL = 0 ] && rm -f /home/sekhri/sl3-self-built/var/lock/subsys/httpd /home/sekhri/sl3-self-built/var/run/httpd.pid
}
reload() {
	echo -n $"Reloading $prog: "
	check13 || exit 1
	killproc $httpd -HUP
	RETVAL=$?
	echo
}

# See how we were called.
case "$1" in
  start)
	start
	;;
  stop)
	stop
	;;
  status)
        status $httpd
	RETVAL=$?
	;;
  restart)
	stop
	start
	;;
  condrestart)
	if [ -f /home/sekhri/sl3-self-built/var/run/httpd.pid ] ; then
		stop
		start
	fi
	;;
  reload)
        reload
	;;
  graceful|help|configtest|fullstatus)
	$apachectl $@
	RETVAL=$?
	;;
  *)
	echo $"Usage: $prog {start|stop|restart|condrestart|reload|status|fullstatus|graceful|help|configtest}"
	exit 1
esac

exit $RETVAL
