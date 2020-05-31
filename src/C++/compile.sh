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

rm -rf $pwd/output
mkdir -p $pwd/bin
echo `$CC -std=c++17 $Args $1 -o $pwd/bin/output`
