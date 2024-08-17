# Agile Scrum Application with Microservices and CI/CD on Azure Cloud
# Description
This project involves the development and deployment of an agile scrum application using microservices architecture and a CI/CD pipeline on Azure Cloud. The application facilitates the management of sprints, tasks, and team members in an agile environment, with separate interfaces for managers and employees.

# Key Features
Manager Interface: Manage sprints, tasks, and team members, with real-time statistics and analytics.
Employee Interface: View and update assigned tasks, manage profiles, and communicate with managers.
CI/CD Pipeline: Automated integration and deployment process using Azure Kubernetes Service (AKS), Jenkins, and Argo CD.
Cloud Infrastructure: Deployed on Azure Cloud with a secure and scalable setup.
Technologies Used
Frontend: React.js
Backend: Spring Boot, Java MVC
Database: MongoDB
Cloud: Azure (AKS, ACR, VMs)
CI/CD Tools: Jenkins, Maven, SonarQube, Argo CD

# Getting Started
# 1-Running the Application
To run the application locally, follow these steps:

Pull the Docker Images:
Pull the required Docker images from Docker Hub.

```docker pull akasbiyasser/scrum-app:back-scrum```

```docker pull akasbiyasser/scrum-app:front-scrum```

```docker pull akasbiyasser/scrum-app:mongo-scrum```

# 2-Create a docker-compose.yml file:
Use the following docker-compose.yml configuration to set up the application:

```
version: '3.8'

services:
  backend:
    image: akasbiyasser/scrum-app:back-scrum
    ports:
      - "8080:8080"
    environment:
      SPRING_DATA_MONGODB_URI: mongodb://hamza:hamza@mongo:27017/scrumdb?authSource=admin
    depends_on:
      - mongo
    networks:
      - scrum

  frontend:
    image: akasbiyasser/scrum-app:front-scrum
    ports:
      - "3000:80"
    networks:
      - scrum

  mongo:
    image: akasbiyasser/scrum-app:mongo-scrum
    ports:
      - "27017:27017"
    volumes:
      - mongo-data:/data/db
      - /mnt/c/scrum:/dump/db/export  # Chemin Windows monté dans le conteneur
    environment:
      MONGO_INITDB_ROOT_USERNAME: hamza
      MONGO_INITDB_ROOT_PASSWORD: hamza
      MONGO_INITDB_DATABASE: scrumdb
    networks:
      - scrum

volumes:
  mongo-data:

networks:
  scrum:
    driver: bridge
```


3-Start the Application:
Run the following command to build and start the containers:

```docker-compose up --build```
This command will launch the backend, frontend, and MongoDB services, making the application accessible locally.
