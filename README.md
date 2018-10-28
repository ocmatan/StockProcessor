StockProcessor

1. how to build the project:
   - you will need JDK 8 installed on your machine, configure JAVA_HOME in you environment variables
     response example: 
      {"lastQuote":{"symbol":"TYO","latestPrice":15.211},"previousQuote":{"symbol":"TYO","latestPrice":15.211}}- the source can be built using gradle or gradlew, gradlew is a part of the project
   - within the StockProcessor path run: "gradlew build"
   - once gradle is done you can see a jar artifact named "stock-processor-application-0.1.0.jar" in build/lib directory

2. how to run the project:
   - run: java -jar build/lib/stock-processor-application-0.1.0.jar
   - or in the IDE import the project as a gradle project and let gradle build the project in the IDE. 
     once the build is done you can run the main function in StockProcessorApplication.java
     
3. Notification mechanism:
  - The notification mechanism is a synchronous request-response model. 
  The client must initiate a request for notifications and in return the system will return all the notifications since 
  the last time the client made a request.
  - All the notifications are aggregated in an embedded repository so other tools, such as websockets or email can be built ot top of it.
  - there is no persistancy, if the process goes down then we must start over.
  
4. APIs:
 - notification request:
   - curl http://localhost:8080/getNotifications?clientId=madeUpClientId
   - notification response example:
    [{"message":"AAPL : The stock is 0.05% higher relative to the last quote","timestamp":1540688765289},
    {"message":"EWT : The stock is 0.05% higher relative to the last quote","timestamp":1540688765289}]
  - API for available stock data in the system:
    - request: 
      curl http://localhost:8080/getStockData?symbol=TYO
    - response example: 
      {"lastQuote":{"symbol":"TYO","latestPrice":15.211},"previousQuote":{"symbol":"TYO","latestPrice":15.211}}
