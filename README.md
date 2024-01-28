A Complete Crud App Full Working with Backend in Springboot and Frontend with React



https://github.com/ImArnav19/Sunbase_Task/assets/117253613/459699ff-16a2-4cb7-81b5-096d4c309d42


ADDITIONS:



https://github.com/ImArnav19/Sunbase_Task/assets/117253613/8b25da7b-5a43-49e4-8357-165f4804ca3a

Implemented Pagination,Sorting,Searching with improvement in Both Server,CLient Codes


Implemented JWT Authentication for Securing Apis : 


https://github.com/ImArnav19/Sunbase_Task/assets/117253613/35cfcc44-2e3f-4a54-ad64-d6824c8636a2


body -> JSon

{
  "email":"arnav",
  "password":"arnav"
}

Docs with SwaggerApi , need some time can do it.




For Running the application :

*REQUIRED*
npm , Nodejs v16 (if there is no Web_config Hash problems then any would do),  
MAVEN, JDK for Build and Springboot

STEPS

1.CLONE REPOSITORY

git clone https://github.com/ImArnav19/Sunbase_Task.git

FRONTEND:

cd .\employeeweb\

  
install all dependencies
*npm i*

*npm start*  

BACKEND:

*cd .\employee\*

SYNC all the Maven dependencies -- CAN be done by INtellij Idea easily, Just open folder in IntelliJ -> locate pom.xml -> Click Sync in TOP RIght corner

RUN : *mvn spring-boot:run*

MAKE SURE login_id and password is added in the Post Request Call for SYNC, locate it inside -> employeeweb\src\pages\dashboard\Dash.jsx

And You are Good to go

There are many things that can be improved but really enjoyed to do this project and understanding all functionalities, Will Definately Imporove this and make a Better Version
