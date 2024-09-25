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


# Start the Application
Run the following command to build and start the containers:

```docker-compose up --build```

This command will launch the backend, frontend, and MongoDB services, making the application accessible locally.

# Annexe : Interfaces de l'application

## À Propos de ScrumFlow

### About us 1
![About us 1](scrum-frontend/src/icons/aboutus1.PNG)

### About us 2
![About us 2](scrum-frontend/src/icons/aboutus2.PNG)

## Interfaces Manager

### Dashboard Manager
![Dashboard Manager](scrum-frontend/src/icons/dashbordmana.PNG)

### Ajouter des sprints
![Ajouter des sprints](scrum-frontend/src/icons/sprintmana2.PNG)

### Modifier les sprints
![Modifier les sprints](scrum-frontend/src/icons/tachemana5.PNG)

### Gestion des sprints
![Gestion des sprints](scrum-frontend/src/icons/sprintmana1.PNG)

### Créer des taches
![Créer des taches](scrum-frontend/src/icons/tachemana2.PNG)

### Gestion des taches
![Gestion des taches](scrum-frontend/src/icons/tachemana1.PNG)

### Vue Kanban des taches
![Vue Kanban des taches](scrum-frontend/src/icons/tachemana4.PNG)

### Vue Kanban des taches 2
![Vue Kanban des taches 2](scrum-frontend/src/icons/tachemana3.PNG)

### Ajouter un Employé
![Ajouter un Employé](scrum-frontend/src/icons/employemana2.PNG)

### Gestion des Employés
![Gestion des Employés](scrum-frontend/src/icons/employemana1.PNG)

## Interfaces Employés

### Profil Employé
![Profil Employé](scrum-frontend/src/icons/interfaceempprofile.PNG)

### Dashbord Employé
![Dashbord Employé](scrum-frontend/src/icons/menuboardemp.PNG)

### Taches Assignées
![Taches Assignées](scrum-frontend/src/icons/tacheemp.PNG)

### Modifier l'état de la tache
![Modifier l'état de la tache](scrum-frontend/src/icons/tacheemp2.PNG)

### Contact
![Contact](scrum-frontend/src/icons/interfaceempcontact.PNG)

