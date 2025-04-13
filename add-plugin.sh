#!/bin/sh

if [ $# -ne 1 ]; then
    echo "usage: add-project <ProjectName>"
    exit 1
fi

start_time=$(date +%s%N)

upper_case_name="$1"
lower_case_name=$(echo "$1" | tr '[:upper:]' '[:lower:]')
dir=$lower_case_name

echo "[ Creating '$upper_case_name' in $dir ]"

if [ -d $dir ]; then
    echo "error: $dir directory already exists"
    exit 1
fi

echo "Creating $dir..."
mkdir $dir

pom_base='<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.jourd1.jgames.lower_case_name</groupId>
    <artifactId>upper_case_name</artifactId>
    <version>0.1</version>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
    </properties>

    <repositories>
        <repository>
            <id>spigot-repo</id>
            <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>org.spigotmc</groupId>
            <artifactId>spigot-api</artifactId>
            <version>1.21.4-R0.1-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>


    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
</project>'

echo "Creating pom.xml..."
pom=$(echo "$pom_base" | sed "s/lower_case_name/$lower_case_name/g" | sed "s/upper_case_name/$upper_case_name/g")
printf "$pom" > "$dir/pom.xml"

group="com.jourd1.jgames.$lower_case_name"
group_dir=$(echo $group |  tr '.' '/')

echo "Creating project directories..."
mkdir -p "$dir/src/main/java/$group_dir"
mkdir -p "$dir/src/main/resources"
mkdir -p "$dir/src/test/java/$group_dir"

plugin_yml_base='name: upper_case_name
main: main_path
description: desc
version: ${project.version}
api-version: 1.21.4'

echo "Creating plugin.yml..."
plugin_yml=$(echo "$plugin_yml_base" | sed "s/upper_case_name/$upper_case_name/g" | sed "s/main_path/$group.$upper_case_name/g")
printf "$plugin_yml" > "$dir/src/main/resources/plugin.yml"

main_class_base='package group_path;

import org.bukkit.plugin.java.JavaPlugin;

public class upper_case_name extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}'

echo "Creating $upper_case_name class..."
main_class=$(echo "$main_class_base" | sed "s/group_path/$group/g" | sed "s/upper_case_name/$upper_case_name/g")
printf "$main_class" > "$dir/src/main/java/$group_dir/$upper_case_name.java"

test_class_base='package group_path;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class upper_case_nameTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}'

echo "Creating ${upper_case_name}Test class..."
test_class=$(echo "$test_class_base" | sed "s/group_path/$group/g" | sed "s/upper_case_name/$upper_case_name/g")
printf "$test_class" > "$dir/src/test/java/$group_dir/${upper_case_name}Test.java"

end_time=$(date +%s%N)
elapsed_time=$(((end_time - start_time) / 1000000))

echo "Done ! (${elapsed_time}ms), 'cd $dir' then 'mvn compile' to compile"