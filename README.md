# ![CloudsdaleApp](https://secure.gravatar.com/avatar/006b4dec507eaac9967970a1cd967167?s=48) Cloudsdale for Java Clients
*****

[![Build Status](https://travis-ci.org/cloudsdaleapp/Cloudsdale-Java.png)](https://travis-ci.org/cloudsdaleapp/Cloudsdale-Java)

## Introduction
Cloudsdale for Java is built using [Square](http://square.github.io)'s [Retrofit](http://square.github.io/retrofit) and [OkHttp](http://square.github.io/okhttp), as well as [Berwyn](http://berwyn.github.io)'s [faye.java](http://github.com/berwyn/faye.java) client

## Requirements
 - JavaSE 1.6+

    or

 - Android 2.3+ (API >= 7)

## Getting Started

First, add the library as a dependency. You can either clone the repo and build yourself, or use Apache Maven. The library can be used via Maven Local or Maven Central (NYI)

```xml
<dependency>
    <groupId>org.cloudsdale</groupId>
    <artifactId>cloudsdale-java</artifactId>
    <version>${LATEST VERSION}</version>
</dependency>
```

After adding the library to your classpath, it's as easy as

```java
Cloudsdale cloudsdale = new Cloudsdale.Builder().with("My awesome API token");
```

From here, the api client will have access to all the RESTful endpoints documented in [our developer documentation](http://dev.cloudsdale.org)
