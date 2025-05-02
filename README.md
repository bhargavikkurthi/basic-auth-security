# spring-boot-basic-auth-security
Application to demonstrate Spring boot security using Basic Auth (Username and Password) as authentication type. 

This application covers the below functionalities:

1. <b>User Registration (API authentication is not required)</b>
   - New use registration without API authentication
2. <b>User Management (API authentication is required)</b>
   - Get all users
   - Delete a user
3. <b>Account Management (API authentication is required)</b>
    - Get all accounts
    - Get an account
    - Create new account
    - Update existing account
    - Delete existing account
4. <b>Transaction Management (API authentication is required)</b>
    - Deposit an amount to an account
    - Withdraw an amount from an account

### Project Technologies

- Java version: 21, vendor: Oracle Corporation
- Spring boot 3.4.5
- MYSql Database
- Spring boot JPA
- Spring security 6.1.5 (Implemented Basic Auth in this project)
- Lombok
- OpenApi 3 Specifications

### Database

MYSQL has been used in this project.

### OpenAPI Specification

- Path

http://localhost:8081/my-application/swagger-ui/index.html


### Disable OpenAPI Swagger for Production Environment

We can disable OpenAPI swagger for any environment based upon profiles. We can supply a VM argument 
'-Dspring.profiles.active=<environment name>' to the application configurations.
Using spring profile annotation @Profile("prod"), we can control the display of swagger.

- VM Argument

![img.png](screenshots/22_vm_argument_spring_active_profile.png)

If the value of spring profile is 'prod', then swagger won't be available. Please refer the below screenshot:

![img.png](screenshots/23_swagger_not_available.png)
