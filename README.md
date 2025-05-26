# 🔧 Tools-Share-Management-Platform ~ Keycloak Integration
---

## Keycloak 

- 🔐 **Keycloak Integration**: Migrated from a JWT-based system to an `OAuth 2.0` flow using Keycloak as the `Authorization Server`.
- 🧩 **Angular Frontend**: Created a dedicated Keycloak service and updated route guards to handle OAuth2 token-based session logic.
- ⚙️ **Spring Boot Backend**: Configured Spring Security to act as an `OAuth 2.0 Resource Server`, accepting JWTs issued by Keycloak and mapping roles using a custom KeycloakJwtAuthenticationConverter.

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



