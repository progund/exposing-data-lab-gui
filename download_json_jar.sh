#!/bin/bash

DEST_DIR=lib
mkdir -p ${DEST_DIR}

echo "Downloading Json-lib (Apache License) from maven..."
wget 'https://search.maven.org/remotecontent?filepath=org/json/json/20171018/json-20171018.jar' -O ${DEST_DIR}/org.json.jar
