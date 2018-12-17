# Log File Analyzer
Its a simple spring boot application that accepts file input over http, process and save the records based the condition mentioned below. It has got an embedded HSQL database manager to query the data in the database.

Flag any long events that take longer than 4ms with a column in the database called "alert" Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder
The application should a new table if necessary and enter the following values: Event id
Event duration
Type and Host if applicable "alert" true is applicable

###At the moment it doesnt support multithreading. The program can handle very large files upto 5GB. 

###Future considerations
1. Provide multithreading support
2. Make the application more robust by adding more tests.


##Example log file:
{"id":"scsmbstgra", "state":"STARTED", "type":"APPLICATION_LOG",
"host":"12345", "timestamp":1491377495212}
{"id":"scsmbstgrb", "state":"STARTED", "timestamp":1491377495213}
{"id":"scsmbstgrc", "state":"FINISHED", "timestamp":1491377495218}
{"id":"scsmbstgra", "state":"FINISHED", "type":"APPLICATION_LOG",
"host":"12345", "timestamp":1491377495217}
{"id":"scsmbstgrc", "state":"STARTED", "timestamp":1491377495210}
{"id":"scsmbstgrb", "state":"FINISHED", "timestamp":1491377495216}
...
In the example above, the event #scsmbstgrb duration is 1401377495216 - 1491377495213 = 3ms The longest event is scsmbstgrc (1491377495218 - 1491377495210 = 8ms)

###The program should:
Take the input file path as input argument
Flag any long events that take longer than 4ms with a column in the database called "alert" Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder
The application should a new table if necessary and enter the following values: Event id
Event duration
Type and Host if applicable "alert" true is applicable

## Requirements
```
Java8
Gradle
Spring boot
```

## Build

### Gradle

```
./gradlew clean build
```

## Testing
To run the unit and integration tests, execute:

```
./gradlew test
```


