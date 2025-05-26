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

### 🚀 Running the Project Locally

1. Run the backend with IntelliJ (or others)
2. Run the E2E tests with Playwright in UI mode `playwright test --ui`

---
## Features

###  🎭 End-to-End Testing 
This project includes **Playwright** for end-to-end testing of the Angular application.

- ✅ **Initial Setup with Page Object Model**: Introduced Playwright using the classic `Page Object Model (POM)` to structure and reuse selectors and actions.
- 🎬 **Screenplay Pattern**: Refactored the test architecture using the `Screenplay pattern`, modeling interactions through actors and tasks.
- 📸 **Visual Regression Testing**: Added `screenshot-based` tests to catch unexpected UI changes and enhance visual coverage.

Playwright configuration and test files are located in the `e2e/` directory.

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
- Playwright 



