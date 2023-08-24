# Nukkit MultiVersion

This is a fork of https://github.com/MemoriesOfTime/NukkitPetteriM1Edition
with more features like:
- PowerNukkitX scoreboard api
- better form api
- more stable

Please note that this project is still work in progress!

## Usage

1. Download the latest jar from [releases](https://github.com/Nukkit-MV/Nukkit-MV/releases)
2. Start the server with `java -jar NukkitMV-1.20.10-SNAPSHOT.jar`

## Dependency

You should use this dependency for creating plugins

### Gradle
````gradle
repositories {
    maven { url = "https://repo.opencollab.dev/maven-releases" }
    maven { url = "https://luconia.net/repositories" }
}

dependencies {
    implementation("cn.nukkit:NukkitMV:1.20.10-1-SNAPSHOT")
}
````

### Maven
````xml
<repositories>
    <repository>
        <id>nukkitmv</id>
        <url>https://luconia.net/repositories</url>
    </repository>
    <repository>
        <id>opencollab-repository-maven-releases</id>
        <name>Opencollab Repository</name>
        <url>https://repo.opencollab.dev/maven-releases</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>cn.nukkit</groupId>
        <artifactId>NukkitMV</artifactId>
        <version>1.20.10-1-SNAPSHOT</version>
    </dependency>
</dependencies>
````
