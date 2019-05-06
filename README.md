# How To Run: 

1. Clone the code and run following command: 

>mvn spring-boot:run

2. It will start the service at port 8080 run following API call: 

curl http://localhost:8080/v1/happyprime

Or if you want to test a specific number, put the number in url like this: 

curl http://localhost:8080/v1/happyprime/12345

3. Response should be like this: 

{"number":424568,"isHappy":false,"isHappyPrime":false,"isPrime":false}