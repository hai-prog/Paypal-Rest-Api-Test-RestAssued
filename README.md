
# PayPal REST API Test Project with RestAssured and SHAFT_ENGINE Template

This project is a sample implementation of API tests for PayPal's REST API using RestAssured. It demonstrates how to authenticate, send requests, and validate responses using RestAssured library,It provides test cases for various endpoints and functionalities offered by the PayPal API.


## Project Structure

The project follows a modular structure with separate packages for different components. The project structure is as follows:

### Main Packages

1. `api_request`: This package contains classes responsible for constructing API requests and handling request parameters.

2. `authorization`: This package includes classes related to authentication and authorization mechanisms required for accessing the PayPal API.

3. `http_methods`: This package consists of classes that encapsulate different HTTP methods (GET, POST, PUT, DELETE) to interact with the API.

6. `paypal_server`: This package contains classes that simulate a PayPal server environment for testing purposes.
4. `validations`: This package includes classes responsible for validating API responses and asserting expected results.

5. `identity_api`: This package contains classes specifically designed for testing the Identity API endpoints of the PayPal API.

5. `orders_api`: This package includes classes specifically designed for testing the Orders API endpoints of the PayPal API.




### Test Packages

1. `identity_tests`: This package contains test cases for the Identity API endpoints.

2. `orders_tests`: This package contains test cases for the Orders API endpoints.

## Prerequisites

Before running the tests, make sure you have the following prerequisites set up:

- Java Development Kit (JDK) installed on your machine.
- A suitable integrated development environment (IDE) such as IntelliJ or Eclipse.
- Maven installed to manage project dependencies.
- RestAssured library added to your project's dependencies.
- SHAFT_ENGINE framework configured in your project.

The SHAFT_ENGINE is required for test automation and provides additional capabilities for reporting, logging, and test management.

Make sure you have the necessary software and tools installed and configured properly before proceeding with the project setup.

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository or download the project files.

2. Import the project into your preferred IDE.

3. Install the required dependencies specified in the `pom.xml` file using Maven.

4. Set up the necessary configurations in the project, such as API credentials, access tokens, etc. Refer to the appropriate package classes (e.g., `authorization`) to configure authentication.

5. Run the desired test classes in the `identity_tests` and `orders_tests` packages to execute the test cases.


## Test Execution

To execute the tests, follow these steps:

1. Ensure that the necessary configurations and dependencies are set up correctly.

2. Run the desired test classes in the `identity_tests` and `orders_tests` packages.

3. Observe the test results in the IDE's console or test execution report.

## Reporting

The project is configured to generate test reports in the `target/surefire-reports` directory after test execution. You can view the reports in HTML format to check the test results and any failures.


## Acknowledgements

This project utilizes the [RestAssured](http://rest-assured.io/) library and [SHAFT_ENGINE](https://github.com/MohabMohie/SHAFT_ENGINE) template for test automation. We acknowledge and appreciate the contributions of the RestAssured and SHAFT_ENGINE communities to the open-source software community.


## References

This project utilizes the following references:

- [PayPal Developer Documentation](https://developer.paypal.com/docs/api/overview/): Official documentation for PayPal's REST API.
- [RestAssured Documentation](https://github.com/rest-assured/rest-assured/wiki): Official documentation for RestAssured library.
- [TestNG Documentation](https://testng.org/doc/documentation-main.html): Official documentation for TestNG testing framework.
- [SHAFT_ENGINE Template](https://github.com/MohabMohie/SHAFT_ENGINE): Template for implementing automation frameworks using SHAFT_ENGINE.

