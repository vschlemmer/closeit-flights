Application description:
-------------------------
- the application connects to a <a href="http://stat-computing.org/dataexpo/2009/the-data.html">flight statistics page</a>, retrieves statistics from the given year and computes an average delay of flights arriving to the given airport that have not been cancelled

System prerequisites:
-------------------------
1) Make sure you have java 8 JRE installed, confirm the installation via `java -version`
2) Make sure you have maven installed, confirm the installation via `mvn -v`
3) Make sure you have git installed, confirm the installation via `git --version`

To run the application:
-------------------------
1) `git clone https://github.com/vschlemmer/closeit-flights.git`
2) `cd closeit-flights`
3) `mvn clean install`
4) `mvn exec:java -Dexec.args="1989 LAX"`
5) You will see the statistics file downloading progress and then the result of the computation for a given year and airport code

To run the tests:
-------------------------
1) `mvn test`
2) see the output of the test run