# bt-solution
The service provides endpoints that can retrieve all user info with posts/users/posts.

## Prerequisites

* [Java 11 Runtime](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

### Run
    
1. How to run Springboot bt-api-solution application

   java -jar bt-api-solution-0.0.1-SNAPSHOT.jar

2. Endpoint urls
   Pleae use below url to retrieve all users with their posts
   
   http://localhost:8080/admin/usersInfo
    
  
   Pleae use below url to retrieve all users
   
   http://localhost:8080/admin/users
   
   Pleae use below url to retrieve all posts
   
   http://localhost:8080/admin/posts
   
3. App description
   Design notes:
   As an admin user I want to be able to see all user information with their related posts so that I can track in one place what users are posting to the site.

4. Api used in this application

   •	GET - /users: 
   Lists all users in the system with their personal information. 
   
   The API can be accessed on: https://jsonplaceholder.typicode.com/users
   
   •	GET - /posts: 
   Lists all posts in the system. 
   
   The API can be accessed on: https://jsonplaceholder.typicode.com/posts

