#!/bin/bash

echo "Building backend..."
sudo docker build -t tsmp/tsmp:1.0.1 -f docker/backend/Dockerfile .

echo "Building frontend..."
sudo docker build -t tsmp/tsmp-ui:1.0.0 -f docker/frontend/Dockerfile .

echo "Starting docker compose..."
sudo docker compose up