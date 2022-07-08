# swagger-akka-http-sample

Clone this git repo and use `sbt run` to start the Akka Http server.

Uses [swagger-akka-http](https://github.com/swagger-akka-http/swagger-akka-http) which is built using [swagger.io](http://swagger.io/) libs.   

Test using the Swagger UI at http://localhost:12345/api-docs. You can view the swagger doc itself at http://localhost:12345/api-docs/swagger.json and  http://localhost:12345/api-docs/swagger.yaml.

The [Swagger UI](http://localhost:12345/api-docs) can be used to send sample requests.
This redirects to https://petstore.swagger.io/ but provides a 'url' parameter that causes this sample's swagger.json to be loaded.

[Swagger 1.5/1.6 version](https://github.com/pjfanning/swagger-akka-http-sample/tree/swagger-1.5)

## Current swagger.json

```
{
  "openapi" : "3.0.1",
  "info" : {
    "title" : "",
    "description" : "",
    "termsOfService" : "",
    "version" : "1.0"
  },
  "externalDocs" : {
    "description" : "Core Docs",
    "url" : "http://acme.com/docs"
  },
  "servers" : [ {
    "url" : "http://localhost:12345"
  } ],
  "security" : [ ],
  "paths" : {
    "/add" : {
      "post" : {
        "summary" : "Add integers",
        "description" : "Add integers",
        "operationId" : "add",
        "requestBody" : {
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/AddRequest"
              }
            }
          },
          "required" : true
        },
        "responses" : {
          "200" : {
            "description" : "Add response",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/AddResponse"
                }
              }
            }
          },
          "500" : {
            "description" : "Internal server error"
          }
        }
      }
    },
    "/addOption" : {
      "post" : {
        "summary" : "Add integers",
        "description" : "Add integers",
        "operationId" : "addOption",
        "requestBody" : {
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/AddOptionRequest"
              }
            }
          },
          "required" : true
        },
        "responses" : {
          "200" : {
            "description" : "Add response",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/AddOptionResponse"
                }
              }
            }
          },
          "500" : {
            "description" : "Internal server error"
          }
        }
      }
    },
    "/echoenum" : {
      "post" : {
        "summary" : "Echo Enum",
        "description" : "Echo Enum",
        "operationId" : "echo",
        "requestBody" : {
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/EchoEnum"
              }
            }
          },
          "required" : true
        },
        "responses" : {
          "200" : {
            "description" : "Echo Enum",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/EchoEnum"
                }
              }
            }
          },
          "400" : {
            "description" : "Bad Request"
          }
        }
      }
    },
    "/echoenumeratum" : {
      "post" : {
        "summary" : "Echo Enumeratum",
        "description" : "Echo Enumeratum",
        "operationId" : "echo_1",
        "requestBody" : {
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/EchoEnumeratum"
              }
            }
          },
          "required" : true
        },
        "responses" : {
          "200" : {
            "description" : "Echo Enumeratum",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/EchoEnumeratum"
                }
              }
            }
          },
          "400" : {
            "description" : "Bad Request"
          }
        }
      }
    },
    "/echolist" : {
      "post" : {
        "summary" : "Echo List",
        "description" : "Echo List",
        "operationId" : "echo_2",
        "requestBody" : {
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/EchoList"
              }
            }
          },
          "required" : true
        },
        "responses" : {
          "200" : {
            "description" : "Echo List",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/EchoList"
                }
              }
            }
          },
          "400" : {
            "description" : "Bad Request"
          }
        }
      }
    },
    "/hello" : {
      "get" : {
        "summary" : "Return Hello greeting (anonymous)",
        "description" : "Return Hello greeting for anonymous request",
        "operationId" : "getHello",
        "responses" : {
          "200" : {
            "description" : "Hello response",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Greeting"
                }
              }
            }
          },
          "500" : {
            "description" : "Internal server error"
          }
        }
      }
    },
    "/hello/{name}" : {
      "get" : {
        "summary" : "Return Hello greeting",
        "description" : "Return Hello greeting for named user",
        "operationId" : "getHelloSegment",
        "parameters" : [ {
          "name" : "name",
          "in" : "path",
          "description" : "user name",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Hello response",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/Greeting"
                }
              }
            }
          },
          "500" : {
            "description" : "Internal server error"
          }
        }
      }
    }
  },
  "components" : {
    "schemas" : {
      "AddRequest" : {
        "required" : [ "numbers" ],
        "type" : "object",
        "properties" : {
          "numbers" : {
            "type" : "array",
            "items" : {
              "type" : "integer",
              "format" : "int32"
            }
          }
        }
      },
      "SeqString" : {
        "type" : "array",
        "items" : {
          "type" : "string"
        }
      },
      "Greeting" : {
        "required" : [ "greeting" ],
        "type" : "object",
        "properties" : {
          "greeting" : {
            "type" : "string"
          }
        }
      },
      "EchoList" : {
        "required" : [ "listName", "values" ],
        "type" : "object",
        "properties" : {
          "listName" : {
            "type" : "string"
          },
          "values" : {
            "type" : "array",
            "items" : {
              "type" : "string"
            }
          }
        }
      },
      "AddOptionResponse" : {
        "required" : [ "sum" ],
        "type" : "object",
        "properties" : {
          "sum" : {
            "type" : "integer",
            "format" : "int32"
          }
        }
      },
      "AddOptionRequest" : {
        "required" : [ "number" ],
        "type" : "object",
        "properties" : {
          "number" : {
            "type" : "integer",
            "format" : "int32"
          },
          "number2" : {
            "type" : "integer",
            "format" : "int32"
          }
        }
      },
      "EchoEnumeratum" : {
        "required" : [ "enumValue" ],
        "type" : "object",
        "properties" : {
          "enumValue" : {
            "type" : "string",
            "enum" : [ "TALL", "GRANDE", "VENTI" ]
          }
        }
      },
      "EchoEnum" : {
        "required" : [ "enumValue" ],
        "type" : "object",
        "properties" : {
          "enumValue" : {
            "type" : "string",
            "enum" : [ "TALL", "GRANDE", "VENTI" ]
          }
        }
      },
      "AddResponse" : {
        "required" : [ "sum" ],
        "type" : "object",
        "properties" : {
          "sum" : {
            "type" : "integer",
            "format" : "int32"
          }
        }
      }
    }
  }
}
```
