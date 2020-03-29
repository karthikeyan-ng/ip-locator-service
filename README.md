# The IP address location finder
This is a Java and Spring Boot REST based service which takes input in the form of IPv4 address from the user. 
Application validates the given IP to check it's total allowed size and format as per the specification.
In order to get information about IP addresses, application uses IP Vigilante API: 
https://www.ipvigilante.com/api-developer-docs/.
Use latitude value to decide if IP address comes from a country from the northern hemisphere.

## Some observation based on the problem statement:
- Time to time the given third party service was down during development.
- The following statement was given in the requirement 
"Use latitude value to decide if IP address comes from a country from the northern hemisphere."
Yes, I could find clear indication that the IP Vigilante API response does provide 
latitude value. However, what I don't understand from the requirement is how to use this latitude 
value to identify that the country is part of Northern Hemisphere.
- Kindly clarify is this a GAP in requirement or the IP Vigilante API response mismatch?

## Install & Running
 
### Prerequisites
* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  - Programming language
* [Maven 3.5.0](https://maven.apache.org/download.cgi) - Build tool

### Dependencies Used
* Spring Boot Web - using to develop REST services
* Spring Boot Actuator - using /trace endpoint you can trace last 100 request
* Project Lombok - using simple annotations you can easily generate bean properties (NoArgsConstructor, AllArgsConstructor, Getter, Setter, Builder and etc)
* Apache Commons Validator - for IP address format check.
* Spring Boot Test - to test the application parts.

### Pull from git 
If you would like to download this source code, please use the following commands.
```
$ git clone https://karthikeyan-ng@bitbucket.org/karthikeyan-ng/ip-locator-service.git
$ cd ip-locator-service
$ git branch ==> ensure you are on "* feature/develop"
```

### Build & run 
* If you would like to run only test cases of this application, execute the following command.
```
$ mvn test
```

* Run the web server on default mode (single command to start the service or application)
```
$ mvn spring-boot:run
```

* This service is running on ```server.port=8888``` as per the application requirement.

### How to test application APIs
After executed the application run command, you have option to hit the URL in the following way.
* Using ```curl``` as shown below.
```
curl http://localhost:8888/northcountries?ip=8.8.8.8&ip=8.8.0.0&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0
```
* Using ```any broswer```. Open your favorite browser and hit the following URL.
```
http://localhost:8888/northcountries?ip=8.8.8.8&ip=8.8.0.0&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0
```

### Example Request and Responses
* If user not given any IP address  
Request:
```
curl http://localhost:8888/northcountries?ip
``` 
Response
```json
[
    {
        "status": "BAD_REQUEST",
        "statusCode": 400,
        "message": "Accepted input is minimum 1 and maximum 5 IP addresses at a time",
        "localDateTime": "2020-03-29T19:56:18.543797"
    }
]
```

* If user given IP address is not in IPv4 format  
Request:
```
curl http://localhost:8888/northcountries?ip=8.8.8.8.0
``` 
Response:
```json
[
    {
        "status": "BAD_REQUEST",
        "statusCode": 400,
        "message": "The following [8.8.8.8.0] addresses are not in the exact IP address format.",
        "localDateTime": "2020-03-29T20:06:17.149052"
    }
]
```

* If user given IP address is in allowed size and proper IPv4 format, API would prepare a success response.  
Request:
```
curl http://localhost:8888/northcountries?ip=8.8.8.8
```
Response:
```json
{
  "northcountries": [
    "United States"
  ]
}
```

* If you have received the following response for your request
```json
{
  "northcountries": []
}
```
* Please check the application log in ERROR category. You may get the following error from IP Vigilante API. 
Because, as I explained in the observations block, time to time IP Vigilante API is not reachable.

```html
<html>
<head><title>502 Bad Gateway</title></head>
<body>
<center><h1>502 Bad Gateway</h1></center>
<hr><center>nginx/1.16.0</center>
</body>
</html>
```

## Built With
* [Spring Boot 2.2.5.RELEASE](https://projects.spring.io/spring-boot/) - Backed Framework
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors
* Karthikeyan Nithiyanandam | +91 8056257484 | karthikeyan.ng.jobs@gmail.com
* You can check my [LinkedIn](https://www.linkedin.com/in/karthikeyan-nithiyanandam/) profile for more information.
* You can check my [GitHub](http://github.com/karthikeyan-ng) repositories samples for various Tech Topics.

## License
This project is licensed under the MIT License

