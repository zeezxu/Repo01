Spike Testing on Flight Booking API Using Gatiling 
============================================

A spike testing on flight booking API using the Gatling plugin for Maven. Refer to the plugin documentation
[on the Gatling website](https://gatling.io/docs/current/extensions/maven_plugin/) for usage.

Flight booking API can be found in [here](https://github.com/sameerean/flight-booking/tree/master?tab=readme-ov-file#markdown-header-api-documentation-and-integration-testing). 

This project is written in Java.

Start testing by running [this class](src/test/java/Engine.java).

All test cases can be found [here](src/test/java/flightbookingDB).  


It includes:

* [Maven Wrapper](https://maven.apache.org/wrapper/), so that you can immediately run Maven with `./mvnw` without having
  to install it on your computer
* minimal `pom.xml`
* latest version of `io.gatling:gatling-maven-plugin` applied
* sample Simulation class,
  demonstrating sufficient Gatling functionality
* flight booking API spike testing 
* proper source file layout
