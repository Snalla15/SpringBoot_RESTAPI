# SpringBoot_RESTAPI
This is a sample of REST API using Spring Boot and Cassandra Database

#get
http://localhost:8080/getDocumentDetails
headers:
Content-Type:application/json
Authorization:*****
ClientId:****
Submitter:****

#POST
Headers:
Content-Type:application/json
Authorization:*****
ClientId:****
Submitter:****
Body:
{
    "provIdentifier": 5102847784,
    "subIdentifier": 9908390382,
    "subName": "sandeep3",
    "subDob": "12151994",
    "docType": "traffic",
    "ocan": 9908351021
}
