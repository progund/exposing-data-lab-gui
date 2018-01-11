#!/bin/bash

PACKAGES="se.itu.systemet.domain se.itu.systemet.gui se.itu.systemet.rest"
javadoc  -d docs/ -link "https://docs.oracle.com/javase/8/docs/api/" $PACKAGES
