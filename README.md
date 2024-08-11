# backendhrm
Spring Boot Back End Development

Validation, Custom Exception, Caching Mechanism, JWT, JIRA, Agile

HRM Application->
1. SignUp
2. SignIn
3. FindById
4. FindByEmpName- Partial Match and it allows ignore case 
5. FindAll
6. SortById
7. SortByName
8. SortBySalary
9. UpdateData
10. Change Address
11. DeleteById
--------------------------------------------------
Technologies->
Back End-
Java 17
Data JPA
Spring Boot 3+

Database- PostgreSQL

Front End- React JS

Tools-
Code Control Management- GitHub

Local Build Management- Maven

Web Server- Tomcat

Secure REST API's- JWT 

API Testing- Postman API, Swagger UI, Thunder Client

Story Tracking- JIRA

Process- Agile

Centralize Build Management- CI/CD Jenkins

Cloud Platform- AWS | GCP

JUnit Testing- Mockito Framework

--------------------------

JPAQL

@Query("SELECT e FROM Employee e WHERE LOWER(e.empName) LIKE LOWER(CONCAT('%', :empName, '%'))")
List<Employee> findByEmpNameIgnoreCase(@Param("empName") String empName);




