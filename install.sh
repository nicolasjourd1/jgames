#!/bin/sh

rm bin/*.jar
mkdir -p bin

declare -a dirs=("japi" "jcore" "jbedwars")
declare -a jars=("JAPI-*.jar" "JCore-*.jar" "JBedWars-*.jar")

for i in "${!dirs[@]}"; do
    mvn clean install -f "${dirs[i]}/pom.xml"
    cp ./${dirs[i]}/target/${jars[i]} ./bin/
done

if [ -d "server/plugins" ]; then
    cp ./bin/*.jar server/plugins
fi