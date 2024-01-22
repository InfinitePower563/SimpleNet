# SimpleNet
SimpleNet is a Java API for handling HTTP requests in a simplified fasion.

# Importing SimpleNet
## Default Java
Whilst running the application, use this:

`java -cp C:\Path\To\Jar\SimpleNet.jar appname`
## Maven
Include in your repository declaration:
```xml
<repository>
    <id>SimpleNet</id>
    <url>https://raw.github.com/InfinitePower563/SimpleNet/master/target/mvn-repo/io/github/infinitepower563/SimpleNet</url>
</repository>
```
And include this in your dependency declaration:
```xml
<dependency>
    <groupId>io.github.infinitepower563</groupId>
    <artifactId>SimpleNet</artifactId>
    <version>1.0.0</version>
</dependency>
```
## Gradle
> Haven't tested this yet, use this at your own risk.
Include this in your repository declaration:
```groovy
repositories {
    maven {
        url 'https://raw.github.com/InfinitePower563/SimpleNet/master/target/mvn-repo/io/github/infinitepower563/SimpleNet'
    }
    mavenCentral()
}
```
And this in your dependency declaration:
```groovy
dependencies {
    compile 'io.github.infinitepower563:SimpleNet:1.0.0'
}
```
## Documentation
Documentation can be found [here](https://infinitepower563.github.io/SimpleNet/javadoc).
