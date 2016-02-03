# swagger-akka-http-sample

Clone this git repo and use sbt run to start the Akka Http server.

The sample uses the https://github.com/swagger-akka-http/swagger-akka-http library.

Test using http://petstore.swagger.io/ and replace the swagger.json with http://localhost:12345/api-docs/swagger.json

The Swagger UI can be used to send sample requests. In my testing, the 'Try It' calls fail when issued from the Swagger UI but the curl command that it produces does run when used from the command line. These issues appear by [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing) related. 
