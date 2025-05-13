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



