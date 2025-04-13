#!/bin/sh

./install.sh

version=$(find bin -type f -name "JCore-*.jar" | cut -d '-' -f2 | cut -d '.' -f1,2)

zip -j jgames-$version.zip bin/*