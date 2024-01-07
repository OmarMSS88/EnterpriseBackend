# EnterpriseBackend
# Table of Contents
* [Introduction](#introduction)
* [Java Structure](#java-structure)
    * [UML](#UML)
    * [Folder Structures](#folder-structures)
* [CI/CD & Deployment](#deployment)
	* [Dockerfile](#dockerfile)
* [Services and Databases](#services)
* [Extras](#extras)
	* [Frontend](#frontend)
	* [Manifest files](#kubernetes)
	* [Rate limiting](#rate-limiting)
## Introduction <a class="anchor" id="introduction"></a>
<p>
    For this project, we've crafted a sophisticated microservices backend. Our backend comprises four distinct microservices that collectively shape the fundamental structure of a restaurant: visits, waiters, tables, and dishes. Each of these services has its own dedicated database. All conceivable requests are seamlessly handled by a custom-built gateway.
</p>

## Java structure <a class="anchor" id="java-structure"></a>
### UML <a class="anchor" id="UML"></a>
<p>
    Our stucture is based on this UML model, although it is not 100% faithful to the model, it shares the same idea.
</p>
<p>
    <img src="readmeassets/UML.png"/>
</p>

### Folder structures <a class="anchor" id="folder-structures"></a>
<p>
    <img src="readmeassets/tablefolders.png"/>
</p>
<p>
    <img src="readmeassets/dishfolders.png"/>
</p>
<p>
    <img src="readmeassets/visittfolders.png"/>
</p>
<p>
    <img src="readmeassets/waiterfolders.png"/>
</p>
<p>
    You can see that in each service the folders are very similar, they all have: controller, dto, model, repository and service
</p>
<ul>
    <li><strong>controller:</strong> Handles incoming HTTP requests, manages the flow of data, and interacts with the service logic to fulfill client requests.</li>
    <li><strong>dto:</strong> Contains Data Transfer Objects, which define the data structure for communication between different layers of the application, often used to transfer data between the controller and service layers.</li>
    <li><strong>model:</strong> Houses the data models or entities representing the core data structures used by the service. These models typically map to database tables or other data storage structures.</li>
    <li><strong>repository:</strong> Manages the interaction with the database, including querying, saving, and updating data. This folder often contains classes or interfaces that define the data access methods.</li>
    <li><strong>service:</strong> Implements the business logic of the service. It contains the application's main functionality, handling tasks such as data validation, processing, and interaction with the repository to perform CRUD (Create, Read, Update, Delete) operations.</li>
</ul>

## CI/CD & Deployment <a class="anchor" id="deployment"></a>
<p>
    The next step is to create automation using docker and docker-compose files.
</p>

### Dockerfile <a class="anchor" id="dockerfile"></a>
<p>
    Each service has a dockerfile that is used to create Docker images of said services within our Java application.
</p>

## Total services and databases <a class="anchor" id="services"></a>
<p>
   In the following schema each service in detail along with its database can be found. The created endpoints within the gateway are also specified.
</p>
<p>
   <img src="readmeassets/Schema.png"/>
</p>
<p>
   Services:
</p>
<ul>
   <li>api-gateway</li>
   <li>visit-service</li>
   <li>dish-service</li>
   <li>waiter-service</li>
   <li>table-service</li>
</ul>
<p>
   Volumes and databases:
</p>
<ul>
   <li>mongo-table</li>
   <li>mongo-waiter</li>
   <li>mongo-dish</li>
   <li>mysql-visit</li>
   <li>mongodb_table_data</li>
   <li>mongodb_waiter_data</li>
   <li>mongodb_dish_data</li>
   <li>mysql_visit_data</li>
</ul>

## Extras <a class="anchor" id="extras"></a>
<p>
	We have a couple of additions in our project.
</p>

### Frontend <a class="anchor" id="frontend"></a>
<p>
	We have a frontend which uses data from dish-service: https://6599be996270e612a52753e5--steady-belekoy-b94338.netlify.app/.
</p>

### Kubernetes manifest files <a class="anchor" id="kubernetes"></a>
<p>
	We converted our docker-compose file to multiple manifest files, as you can see below.
	<img src="readmeassets/kubernetesManifest1.png"/>
	<img src="readmeassets/kubernetesManifest2.png"/>
</p>

### Rate limiting <a class="anchor" id="rate-limiting"></a>
<p>
	We added rate limiting to our project.
	
</p>
<p>
	<img src="readmeassets/rateLimiting.png"/>
</p>
