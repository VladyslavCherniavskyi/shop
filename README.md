<br/>
<p align="center">
  <h3 align="center">Online Shop</h3>

  <p align="center">
    An Online Store with REST API built with Spring Framework
    <br/>
    <br/>
    <h3 align="center">Languages and Tools:</h3>
<p align="center"> 
  <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a>
  <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a>
  <a href="https://spring.io/projects/spring-security" target="_blank" rel="noreferrer"> <img src="https://www.saashub.com/images/app/service_logos/129/rc71jd29uxtm/large.png?1580496061" alt="spring-security" width="40" height="40"/> </a>
  <a href="https://www.liquibase.com/" target="_blank" rel="noreferrer"> <img src="https://www.liquibase.com/wp-content/themes/liquibase/assets/img/logo.svg" alt="liquibase" width="90" height="40"/>  </a>
  <a href="https://www.postgresql.org" target="_blank" rel="noreferrer"> <img src="https://www.postgresql.org/media/img/about/press/elephant.png" alt="postgresql" width="40" height="40"/>  </a>
  <a href="https://jwt.io" target="_blank" rel="noreferrer"> <img src="https://jwt.io/img/pic_logo.svg" alt="jwt" width="40" height="40"/>  </a>
  <a href="https://hibernate.org/" target="_blank" rel="noreferrer"><img src="https://static-00.iconduck.com/assets.00/hibernate-icon-491x512-qd6jy16p.png" alt="hibernate" width="40" height="40"/> </a>
  <a href="https://maven.apache.org/" target="_blank" rel="noreferrer"> <img src="https://user-images.githubusercontent.com/43886029/158700377-62b0da69-81a2-4340-8ce6-dec718533aee.svg" alt="maven" width="40" height="40"/> </a>
  <a href="https://aws.amazon.com" target="_blank" rel="noreferrer"> <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Amazon_Web_Services_Logo.svg/300px-Amazon_Web_Services_Logo.svg.png" alt="aws" width="40" height="40"/> </a>
  <a href="https://mapstruct.org" target="_blank" rel="noreferrer"> <img src="https://mapstruct.org/images/mapstruct.png" alt="mapstruct" width="90" height="40"/> </a>
 </p>
    <br/>
    <br/>

## Table Of Contents

* [About the Project](#about-the-project)
* [Technologies](#technologies)
* [Getting Started](#getting-started)
* [Local environment variables](#Local-environment-variables)
* [Cloud environment variables](#Cloud-environment-variables)
* [Contacts](#contacts)

## About The Project

![login.png](login.png)

Welcome to the Online-Shop project! This project is a web application. It is developed to provide users with a
convenient to manage an online shop. The following features are available:

- Administrative panel
- Product management
- Order management

The main functions of this program include creating and managing user accounts, operations with added products,
purchasing goods, and viewing purchase history. The program adheres to the CRUD (Create, Read, Update, Delete) paradigm,
providing a comprehensive and user-friendly interaction.

## Technologies

<ul>
<li> Java Core: <a href="https://docs.oracle.com/en/java/"> Oracle Java Documentation </a> </li>
<li> Spring Framework:
<ul> <li> Framework: <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/"> Spring Framework Documentation </a> </li>
     <li> MVC: <a href="https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html"> Spring MVC Documentation </a> </li>
     <li> Security: <a href="https://docs.spring.io/spring-security/reference/index.html"> Spring Security Documentation </a> </li> </ul> </li>
<li> Liquibase: <a href="https://docs.liquibase.com/home.html"> Liquibase documentation </a> </li>
<li> PostgreSQL: <a href="https://www.postgresql.org/docs/"> PostgreSQL documentation </a> </li>
<li> JWT: <a href="https://jwt.io/libraries"> JWT libraries </a> </li>
<li> Hibernate: <a href="https://hibernate.org/orm/documentation/5.3/"> Hibernate Documentation </a> </li>
<li> Maven: <a href="https://maven.apache.org/guides/index.html"> Maven Documentation </a> </li>
<li> Mapstruct: <a href="https://mapstruct.org/documentation/installation/"> Mapstruct documentation </a> </li>
<li> AWS: <a href="https://aws.amazon.com/documentation-overview/?nc2=h_ql_doc_do"> AWS documentation </a> </li>
</ul>

## Getting Started

To get started with the project, follow these steps:

<ul>
<h4> Step 1: Build project</h4>

- `mvn clean package`

<h4> Step 2: Start test</h4>

- `mvn test`

<h4> Step 3: Build the Docker Image</h4> 

- `docker build . -t shop_image`

<h4> Step 4: Run the Docker Container</h4> 

- ```
  docker run -p 8080:8080 
  -e LOCAL_DATASOURCE_URL=<your_datasource_url>?currentSchema=shop 

  -e LOCAL_DATASOURCE_USERNAME=<your_db_username> 
  -e LOCAL_DATASOURCE_PASSWORD=<your_db_password>
  -e PRODUCT_PHOTO_DIRECTORY=<your_product_photo_directory>
  -e USER_PHOTO_DIRECTORY=<your_user_photo_directory>
  -e JWT_APP_EXPIRATION_MS=<your_jwt_expiration> 
  -e JWT_APP_SECRET_KEY=<your_jwt_secret_key> 
  shop

## Local environment variables

--spring.profiles.active=local
- LOCAL_DATASOURCE_URL=...?currentSchema=shop;
- LOCAL_DATASOURCE_USERNAME;
- LOCAL_DATASOURCE_PASSWORD;
- PRODUCT_PHOTO_DIRECTORY;
- USER_PHOTO_DIRECTORY;

- JWT_APP_EXPIRATION_MS;
- JWT_APP_SECRET_KEY.

## Cloud environment variables

- DATASOURCE_URL=...?currentSchema=shop;
- DATASOURCE_USERNAME;
- DATASOURCE_PASSWORD;
- ORIGINS;
- ACCESS_KEY;
- SECRET_ACCESS_KEY;
- REGION;
- PRODUCT_PHOTO_BUCKET_NAME;
- USER_PHOTO_BUCKET_NAME

- JWT_APP_EXPIRATION_MS;
- JWT_APP_SECRET_KEY.

## Contacts

* **Vladyslav Cherniavskyi** - *Java Software Engineer*
* **LinkedIn**- [Vladyslav Cherniavskyi](https://www.linkedin.com/feed/)
* **Email**- vladyslavcherniavskyi@ukr.net
