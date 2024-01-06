# EnterpriseBackend
# Table of Contents
* [Introduction](#introduction)
* [Java Structure](#java-structure)
    * [UML](#UML)
    * [Folder Structures](#folder-structures)
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

## CI/CD & Depolyment
<p>
    The next step is to create automation using docker and docker-compose files.
</p>

### Dockerfile
<p>
    Each service has a dockerfile that is used to create Docker images of said services within our Java application.
</p>

## Total services and databases
<p>
   In the following schema each service in detail along with its database can be found. The created endpoints within the gateway are also specified.
</p>
<p>
   <img src=""/>
</p>
