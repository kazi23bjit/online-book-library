# Table of Contents
1. Project's Title 
2. Project's Description
3. Installation
 
<br>

## 1. Online Book Library
I have created a simple online book library REST application. This project uses Rest Controller to 
create, read, update, delete books. On top of that, I have used spring security to register, authenticate and
authorize users.

<br>

## 2. Project Description

This project is a REST application. Below I have described the entities, controller, and security of the project.

### Entities:
#### Book:
  - Title,
  - Author
  - Genre 
  - Price
#### User:
  - First name
  - Last name
  - E-mail
  - Password
  - Address
  - Roles : There is many to many relationship with Role entity. 
#### Role:
  - Role name

### Controller:
#### Book controller:
 - books/all : It is used to fetch all the books stored in the database. <b>AUTHORITY REQUIRED</b>: ADMIN, CUSTOMER. 
 		Below is a screenshot of a CUSTOMER request to get all the books.
 		![Capture1](https://github.com/kazi23bjit/online-book-library/assets/130432314/9940266b-ee70-4eac-9077-2a4674ca9a68)
	
 - books/create : <b>ADMIN</b> can create a book. Below is a screenshot of a scenario where a CUSTOMER get "403 Forbidded"
 		while creating a book.
		![Capture6](https://github.com/kazi23bjit/online-book-library/assets/130432314/48332d7a-5399-4950-a9c9-03733bb1a4f4)
		
 - books/id/{id} : Will get a book based on the id provided in Path vairable
 - books/author-title/{author name}/{id} : will get a book based on the provided path variables.
 - books/author/{author name} : will get a list of book based on the the author name.
 - books/update/{id} : Update a book based on the book id.
 - books/delete/{id} : delete a book based on book id.
#### User controller:
  - user/register : Register a user.
  - user/login : Authenticate a user against database, create a token, and permit the user to the Books api controllers
                  based on the roles.
		![Capture5](https://github.com/kazi23bjit/online-book-library/assets/130432314/0cd67929-c889-465b-a85a-b31fe50dddaf)
  

 #### Security of application:
- My application creates a user with multiple roles. It fetches the authority and mathces the authority against user's
  authorities. And provides the user with required path if the user has access.
- I used JWT to create a token, and set the token in the users authorization header.
  I have used this to encode some of users information during login. My JWTFilter
  checks the token before the request hits end point, extracts the user from the token
  and checks if the user is in our Database, so the filter happens before.
- I am overriding a UserDetailsService "loadUserByUserName" method. In here, I am
  passing the Authorities to a UserDetails Object. Then, I have checked if the passed 
  JWT token is valid, if it is valid, then it is used to create a UserNamePasswordAuthenticationToken,
  which is set in the Security context of the application. Meaning the user with this token can 
  access the specified parts of the application, based on his roles.
  
- I have faced circular dependency during setting authorities. I have changed the code location, and it solved my issues.
- NOTE: I have used password validation. But, it was not efficient. I have plans to create Custome Authentication Provider
        to provide password validation.
<br>

## 3. Installation
1. Clone the repo 
   <br>
`git clone https://github.com/kazi23bjit/online-book-library.git`
1. Install Dependencies:
    <br> `implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	        implementation 'org.springframework.boot:spring-boot-starter-security'
	        implementation 'org.springframework.boot:spring-boot-starter-web'
	        implementation group: 'io.jsonwebtoken', name: 'jjwt-api', version: '0.11.5'
	        runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-impl', version: '0.11.5'
	        runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.11.5'
	        runtimeOnly 'com.mysql:mysql-connector-j'
	        compileOnly 'org.projectlombok:lombok'
	        annotationProcessor 'org.projectlombok:lombok'
	        testImplementation 'org.springframework.boot:spring-boot-starter-test'`

