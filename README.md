# SQL query libraries comparison

Simple project that runs the same app, with different backing libraries to deal with SQL.
Without having to use an ORM nor resorting to manually dealing with `Connection`-s and `ResultSet`-s. 

The database all this runs against is the excellent sqlite, bundled through a library.

## Instructions

Clone the project and run locally `$ ./gradlew clean build`

This project requires any JDK version 16+ to be installed.

If IntelliJ doesn't play nice try to:
1. Turn on annotation processing (Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors)
2. Build the project again from command line (`$ ./gradlew clean build`)
3. Clear caches and restart (File -> Invalidate Caches... -> "clear file system cache" -> Invalidate and Restart)

A small integration test suite covers functionality, find it [here](/src/test/java/be/inniger/scratch/sql/PersonTest.java).

The library specific code you can find [here](/src/main/java/be/inniger/scratch/sql/dao).

Finally all the configuration happens inside [the App class](/src/main/java/be/inniger/scratch/sql/App.java).