FROM node:20 AS build-stage
WORKDIR /app

# Install the specific Angular CLI version (19.2.8)
RUN npm install -g @angular/cli@19.2.8

# Define dependency and versions we use
COPY package*.json ./

# Install dependency
RUN npm install

# Copy all remeining files
COPY . .

# Clean previous builds
RUN rm -rf dist

# Build the application
RUN npm run build --prod

# List the contents of the dist folder for debugging
RUN ls -l /app/dist/tool-sharing-ui

# SERVE ANGULAR APPLICATION - CHOICE OF SERVER
FROM nginx:alpine
# WE NEED THE NGINX CONFIG FILE AND COPY IT IN THE DEFAULT LOCATION
# THE FILE IS LOCATED IN tool-sharing-ui/
COPY nginx.conf /etc/nginx/nginx.conf

# THE dist FOLDER IS CREATED AFTER THE ANGULR BUILD PHASE
# THE NAME IS WHAT WE HAVE SPECIFIED IN THE package.json FILE
COPY --from=build-stage /app/dist/tool-sharing-ui /usr/share/nginx/html

EXPOSE 80