#!/bin/sh

declare -a dirs=("jcore" "jbedwars")

for i in "${!dirs[@]}"; do
    mvn clean -f "${dirs[i]}/pom.xml"
done