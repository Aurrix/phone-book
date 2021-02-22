# Phonebook
Simple user based phonebook

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Java SE JDK 11,

GRADLE 6.82

### Installing (Windows)

1. Download and extract repository

2. Open command line at extracted directory

3. Type:
```
gradle build
```
4. Gradle will generate jar at ../product-adviser/build/libs/

### Authentication
Application requires `Authorization` header with base64 encoded content `Basic username:password`.

### OOB User

Application configured with in memory username: user and password: user.
Example of header: Authorization : Basic dXNlcjp1c2Vy

### Jar file

Ready to use jar file is in root directory with name: ```phone-book-0.0.1-SNAPSHOT.jar```

This jar can be started with command line:
```
java -jar phone-book-0.0.1-SNAPSHOT.jar
```
or just launching with double-click.

### Lombok Annotaion Processing

This project uses Lombok. Majority of getters, setters and constructors being created during runtime. In order for IDE to do not show errors during compile time, you would need to enable Annotaion processing. To enable annotation processing in Intellij go to: "Settings > Build > Compiler > Annotation Processors".

### Swagger UI

REST API documentation can be accessed by `http://localhost:9000/swagger-ui.html`

### Testing

To run test cases repeat steps 1,2 then type:
```
gradle test
```
## License

This project is licensed under the [Apache License 2](https://www.apache.org/licenses/LICENSE-2.0)
