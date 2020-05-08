# Building

## Maven toolchain

This project uses Maven Toolchain plugin.

To compile it, you will need to define a toolchain like following in `~/.m2/toolchains.xml`:
```xml
<?xml version="1.0" encoding="UTF8"?>
<toolchains>
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>8</version>
            <vendor>openjdk</vendor>
        </provides>
        <configuration>
            <jdkHome>/usr/lib/jvm/java-1.8.0-openjdk-amd64</jdkHome>
        </configuration>
    </toolchain>
</toolchains>
```