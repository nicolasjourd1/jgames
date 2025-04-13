# JGames
A collection of minigame plugins for Minecraft

## Using
You'll need a Minecraft 1.21.4 Spigot server for using the plugins. Also, a databse is needed for JAPI. Games can be launched without setting up a database, but nothing is saved.

### From release
Download latest the latest released archive and put its content in the plugin directory of your server.

### From source
A `install.sh` script is provided : it creates jars, copies them to the binary directory and in the server plugins 
directory if it exists.

Also, a `release.sh` script is provided : it creates a release archive (i.e. it runs `install.sh` and zips the binary 
directory).

Alternatively, each plugin can be compiled separately using maven in its directory. Each jar is located in the `target` 
directory, alongside `src`.

## Contributing
The project requires. **JDK 21** and **maven**.
You can start developping with minimal configuration using *VS Code* with the "*Extension Pack for Java*" extension. 

The provided `add-plugin` script creates a new plugin with the provided name :
```
./add-plugin MyPlugin
```