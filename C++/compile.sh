#!/bin/sh
# Shift + Opt + N = Compile
# 

pwd=$(PWD)
uname=$(uname -s)
CC=g++
args="-std=c++17 -Wno-c++98-compat -Wno-shadow-field-in-constructor -Wno-c++98-compat-pedantic -Wno-header-hygiene -pthread -Wno-padded -Wno-newline-eof -I$pwd/Include "
case $uname in
  Darwin) 
    CC=clang++ 
    args+="-stdlib=libc++ -Weverything "
    ;;
  *) ;;
esac

rm -rf $pwd/bin/output
mkdir -p $pwd/bin
$CC $args $1 -o $pwd/bin/output
if [ -f $pwd/bin/output ]; then
  echo "Running program... \n"
  $pwd/bin/output
fi
