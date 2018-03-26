# job-assignment-exercise-1
Just a job assignment exercise.

-----------
  NOTES
-----------

1) To see the output of jacoco go to target/site/jacoco-ut/index.html

2) mvn clean test (runs unit tests only and creates jacoco test coverage report)

3) mvn clean verify -P integration-test (runs integration tests only)

4) mvn clean verify -P all-tests (runs unit tests and creates jacoco test coverage for them, and then runs integration tests)

5) in order to run mutation for unit tests, execute: org.pitest:pitest-maven:mutationCoverage
    and see the output in the dir: target/pit-reports/YYYYMMDDHHMI/index.html


-----------------
  PLUGINS USED
-----------------
1) Maven Compiler Plugin (is used to compile the sources of your project)
2) Jacoco Plugin (in order to generate test coverage reports for unit tests)
3) Maven Surefire Plugin (in order to run unit tests)
4) Maven Failsafe Plugin (in order to run integration tests)
5) Embedded Tomcat Plugin (in order to run integration tests)
6) Pitest Plugin http://pitest.org/ (in order to run mutation for unit tests)


---------------------
  DEPENDENCIES USED
---------------------
1) Spring Web Mvc
2) Spring Core
3) Spring Beans
4) Spring Context
5) Spring Web
6) Spring Integration
7) Jackson Databind
8) Jackson Core
9) Lombok
10) JUnit
11) Hamcrest
12) Mockito Core
13) Spring Test
14) Pitest
