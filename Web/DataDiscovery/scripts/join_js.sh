#!/bin/sh
`dirname $0`/join_js.py Templates/tmpl/dd_combined.txt
`dirname $0`/join_js.py Templates/tmpl/yui_combined.txt
mv -f Templates/tmpl/dd_combined.txt.js js
mv -f Templates/tmpl/yui_combined.txt.js js

