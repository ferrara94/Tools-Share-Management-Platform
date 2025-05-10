#!/bin/bash

echo "Building backend..."
cd tool-sharing
sudo docker build -t tsmp/tsmp:1.0.1 -f ../docker/backend/Dockerfile .

echo "Building frontend..."
cd ../tool-sharing-ui
sudo docker build -t tsmp/tsmp-ui:1.0.0 -f docker/frontend/Dockerfile .

echo "Starting docker compose..."
cd ..
sudo docker compose up