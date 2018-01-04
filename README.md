Application description:
-------------------------
- the application connects to a <a href="http://stat-computing.org/dataexpo/2009/the-data.html">flight statistics page</a>, retrieves statistics from the year 1989 and computes an average delay of flights arriving to Los Angeles that have not been cancelled

System prerequisites:
-------------------------
1) Make sure you have java 8 JRE installed, confirm the installation via `java -version`
2) Make sure you have maven installed, confirm the installation via `mvn -v`
3) Make sure you have git installed, confirm the installation via `git --version`

To run the application:
-------------------------
1) `git clone https://github.com/vschlemmer/closeit-flights.git`
2) `mvn clean install`
3) `mvn exec:java`
4) You will see the statistics file downloading progress and then you will see the result of the computation
