# 🔧 Tools-Share-Management-Platform
## Overview
ToolShare is a full-stack application that enables users to manage their tools and engage with a community of
borrowers and lenders.
The platform allows users to list their tools, update and archive listings, borrow available tools, return them 
after use, and approve returns from others.

**Do you need a tool just once?** 
💡 **Borrow it!**

----
## 📝 Instructions

> ⚠️ Note:
> The current stable versions of the application are available in the [**Releases section**](https://github.com/ferrara94/Tools-Share-Management-Platform/tags) (e.g., v1.0.0).

#### 🐳 Docker Compose Solution 
This version runs all components (Spring, Angular, PostgreSql) inside **Docker containers**. You can run the entire application using **Docker Compose**.
To run the Docker-based version, simply use the latest tagged [release](https://github.com/ferrara94/Tools-Share-Management-Platform/releases/tag/v1.0.2) and follow the containerized setup instructions.

#### 💻 Development Mode Solution
In this version, you run the Spring and Angular components separately in "**dev**" mode. The **Angular** app can be run using ng serve, and the Spring app can be executed through an IDE like IntelliJ.
To run in this mode, use this [release](https://github.com/ferrara94/Tools-Share-Management-Platform/releases) and follow the "dev mode" instructions.

#### 🚀 Quick Start with Docker Hub Images
If you want to run the application **without building anything locally**, you can use the prebuilt Docker images hosted on **Docker Hub**.

📦 Images:
- [fferrara8/tsmp-ui](https://hub.docker.com/r/fferrara8/tsmp-ui)
- [fferrara8/tsmp-api](https://hub.docker.com/r/fferrara8/tsmp-api)

🔄 Replace the relevant [section](https://github.com/ferrara94/Tools-Share-Management-Platform/blob/main/docker-compose.yml#L18-L36) in the docker-compose.yml with:

```
    tsmp-api:
        container_name: tsmp-api
        image: fferrara8/tsmp-api:1.0.3
```
```
    tsmp-ui:
        container_name: tsmp-ui
        image: fferrara8/tsmp-ui:1.0.0
```
---

## 🔀 Integration Branches

To experiment with specific features without affecting the stability of the `main` branch, separate branches have been created. Each one includes a distinct integration, allowing isolated development and testing of that functionality:


- **feature/keycloak-integration** 🔐 – Adds `OAuth2` and `Keycloak-based` authentication and authorization.
- **tests/playwright-integration** 🧪 – Adds end-to-end testing using `Playwright`.

---
## Features
- 👤 User Registration & Authentication
- 🧰 Tool Management
- 🔗 Tool Sharing
- 🔄 Tool Borrowing
- 📥 Tool Returning
- ✅ Tool Return Approval

---
## Technologies Used

### Backend
- Spring Boot
- Spring Security 
- JWT Token Authentication
- Spring Data JPA
- Spring Validation
- Docker

### Frontend
- Angular
- Component-Based Architecture
- Authentication Guard
- Bootstrap



