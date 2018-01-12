#!/bin/bash

PATHSEP=":"
if [[ $OS == "Windows_NT" ]] || [[ $OSTYPE == "cygwin" ]]
then
    PATHSEP=";"
fi
CP=".${PATHSEP}lib/org.json.jar"
#CP="."
java -cp $CP se.itu.systemet.main.ProductSearch
