#!/bin/sh

pwd=$(PWD)
uname=$(uname -s)
CC=g++
Args=
case $uname in
  Darwin) 
    CC=clang++ 
    Args="-stdlib=libc++ -Weverything"
    ;;
  *) ;;
esac

rm -rf $pwd/bin/output
mkdir -p $pwd/bin
$CC -std=c++17 -Wno-c++98-compat -pthread -I$pwd/Include $Args $1 -o $pwd/bin/output
if [ -f $pwd/bin/output ]; then
  echo "Running program... \n"
  $pwd/bin/output
fi
