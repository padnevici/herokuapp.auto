# herokuapp.auto Automation Test Framework Solution
This is a basic test framework developed as a solution to test assignment. 
* Cucumber - Automated test approach was based BDD. And selected tool for this is Cucumber. This framework allow to write scenarios in plain English, which make easier understanding them by non technical people and not only. All the heavy coding stuff is hided from Business analysts. 
* Maven-Cucumber-Reporting - This maven plugin was selected to generate a beautiful and very informative report of test results.
* Java - As programming language I selected Java, as it allow writing very powerful working code in tandem with other java libraries (Logback, Spring, Cucumber, Maven...). 
* Logback - For any loging stuff it is best library. It is simple and with powerful possibilities which makes loging stuff very easy.
* Spring - Since Cucumber has its own Cucumber Context I used this framework in order to pass data between steps (where it was needed).
* Selenium Web Driver - For UI tests execution it is best library. it is open source, has very powerful methods, Implements Page Object pattern and it is cross browser.
* Matchers (Hamcrest) - This library was selected to do assertion stuff. Using its very reach functionality I can do very smartly assertion.
* JavaFaker - This library was selected to be able to generate intuitive random data necessary for test execution.
* Maven - as software project management and comprehension tool. It has powerful functionalities, reach documentation and many examples on the Internet. 


Listed technology stack allow me to create a powerful test framework for UI Automation.


# Prerequisites:
Before able to run test with this automation test framework you would need to setup some tools
* JDK 8 from here https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
* Maven from here https://maven.apache.org
* Configure JAVA_HOME and add java.exe to system path. An example is here https://stackoverflow.com/questions/2619584/how-to-set-java-home-on-windows-7
* Configure MVN_HOME and add mvn.exe to system path. An example is here https://www.mkyong.com/maven/how-to-install-maven-in-windows/
* Latest versions of Chrome and Firefox browsers to be installed too.

**NOTE:** 
Test framework will work only on Windows or MacOs systems. Other systems are not supported yet, but can be added easily.

# Test Coverage:
Information about created test scenarios can be found here:
```
src/test/resources/Features
```
Scenarios are written in plain English using Gerking format. 
**Note:** Current test coverage is not full and it can contain more negative scenarios with StartDate and Email verification. But due to lack of time I stick with current tests only.

# How To Run tests
* Open cmd in windows or terminal in macOs
* Go to the root folder of the project "herokuapp.auto"
* Execute following command to execute Smoke tests:
```
mvn clean verify -Dcucumber.options="--tags @Smoke"
```
* Or execute following command to execute Regression tests (all scenarios):
```
mvn clean verify -Dcucumber.options="--tags @Regression"
```

# Test results
After test execution results will be under generated folder. 
* Cucumber report will be under:
```
target/cucumber-report-html/cucumber-html-reports/
```
* And execution log will be under :
```
target/logs/
```
* In case of any fail a screenshot will be captured automatically. The screenshot can be found in report linked to After step hook.

# Configuration
Some basic configuration is available too. The config files can be found here:
```
src/main/resources/
```
* test-env-config.properties
> This config file contains data like username and password, application url or interested browser.
* logback.xml
> This configuration file can be used in order to change log level of logged data during test execution. **Current log level is INFO**

