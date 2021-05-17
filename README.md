# Unofficial Duckduckgo API

This is an unofficial Duckduckgo API created to grab the first 25 results queried from <duckduckgo.com>. The website <duckduckgo.com> does provide an api, however I didn't like the results. So, now we are here... I rolled my own unofficial Duckduckgo API for easy utilization in a Java based project. Just make sure rocking maven in project, and you're in our little club :wink: 

## Getting Started

These instructions will get you a copy of the project up and running on your local
machine for development and testing purposes. See deployment for notes on how to
deploy the project on a live system.

### Prerequisites

Java 8 is required to run this application, other than that there are no
prerequisites for the project, as the dependencies are included in the `pom.xml` file.

### Installing

To install the library is as simple as cloning the repository and building a jar file
by running

```bash
mvn clean package
```

Then it's as easy as importing the jar into you project as a dependency! Alternatively,
you can install the library via a maven repository. Simply add the following to your
projects `pom.xml` file

```xml
<dependency>
    <groupId>org.bitnick.duckduckgo</groupId>
    <artifactId>duckduckgo</artifactId>
    <version>[CURRENT VERSION]</version>
</dependency>
```

And have fun!

### Dev Notes

Run unit tests locally:

```bash
mvn test
```

## Usage

Import the module and instantiate the `WebSearch()` constructor by calling the static method `WebSearch.instanceOf()`. Finally, call the search method and enjoy!

```java
WebSearch webSearchTest = WebSearch.instanceOf();
List<SearchResult> anonfileResults = webSearchTest.search("anonfile api");
anonfileResults.forEach(x -> System.out.printf("%s\n%s\n%s\n\n",
    x.getTitle(), x.getUrl(), x.getDescription()));
```

Lightweight and simple searching on the Duckduckgo website. Enjoy!

## Built With

* [jsoup](https://github.com/jhy/jsoup) - Java HTML Parser
* [JUnit](https://github.com/junit-team/junit5) - Unit Testing Framework
* [JSON](https://github.com/stleary/JSON-java) - A Java JSON library

## Versioning

Navigate to [tags on this repository](https://github.com/nstrydom2/duckduckgo-api/tags)
to see all available versions.

## Authors

| Name             | Mail Address                | GitHub Profile                                |
|------------------|-----------------------------|-----------------------------------------------|
| Nicholas Strydom | nstrydom@gmail.com          | [nstrydom2](https://github.com/nstrydom2)     |

See also the list of [contributors](https://github.com/nstrydom2/duckduckgo-api/contributors)
who participated in this project.

# License

This project is licensed under the MIT License - see the LICENSE.md license file for more details.

# Acknowledgments
- My Family
- Paps
- Hat tip to anyone whose code was used
- Inspiration
- etc
