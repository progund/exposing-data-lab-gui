#!/bin/bash

PATHSEP=":"
if [[ $OS == "Windows_NT" ]] || [[ $OSTYPE == "cygwin" ]]
then
    PATHSEP=";"
fi
#CP=".${PATHSEP}lib/org.json.jar"
CP="."
javac -cp ${CP} examples/QueryBuilder.java se/itu/systemet/rest/*.java && java -cp ${CP} examples.QueryBuilder
