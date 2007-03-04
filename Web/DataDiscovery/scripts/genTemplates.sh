#!/bin/sh
rm -rf Templates/*.py*
find Templates -name "*.tmpl" | \
awk '{print "cheetah compile --flat --odir Templates "$1""}' | /bin/sh
find Templates -name "*.bak" -exec rm {} \;
cat > Templates/__init__.py << EOF
#!/usr/bin/env python

"""
Templates used by DataDiscovery
"""
__author__ = "Valentin Kuznetsov <vk@mail.lns.cornell.edu>"
__revision__ = 1

EOF
#ls Templates/*.py | awk '{split($1,a,"."); split(a[1],b,"/"); print "import "b[1]""}' >> __init__.py
ls Templates/*.py | grep -v __init__ | awk '{split($1,a,"."); split(a[1],b,"/"); print "from "b[1]"."b[2]" import "b[2]""}' >> Templates/__init__.py
