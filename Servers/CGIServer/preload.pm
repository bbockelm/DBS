#!/usr/bin/perl

# Setup environment for dbsxml at Apache server startup with mod_perl.
BEGIN {
  use strict; use warnings; $^W=1;
  unshift(@INC, "/home/sekhri/sl3-as-installed/sw/slc3_ia32_gcc323/cms/PHEDEX/PHEDEX_2_3_9/Toolkit/Common");
}


# use Apache2 ();   # <-- only for mod_perl 1.99!
# use ModPerl::MethodLookup ();
# ModPerl::MethodLookup::preload_all_modules ();
use ModPerl::Util ();
use Apache2::RequestRec ();
use Apache2::RequestIO ();
use Apache2::RequestUtil ();
use Apache2::ServerRec ();
use Apache2::ServerUtil ();
use Apache2::Connection ();
use Apache2::Log ();
use APR::Table ();
use ModPerl::Registry ();
use Apache2::Const -compile => ':common';
use APR::Const -compile => ':common';

use CGI ();
CGI->compile(':all');
use Apache::DBI ();
use DBD::Oracle ();
use POSIX ();

1;
