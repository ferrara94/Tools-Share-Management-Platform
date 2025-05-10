#!/bin/bash

ROOT_DIR=$(pwd)

echo "Building backend..."
sudo docker build -t tsmp/tsmp:1.0.1 -f $ROOT_DIR/docker/backend/Dockerfile $ROOT_DIR/tool-sharing

echo "Building frontend..."
cd ../tool-sharing-ui

sudo docker build -t tsmp/tsmp-ui:1.0.0 -f $ROOT_DIR/docker/frontend/Dockerfile $ROOT_DIR/tool-sharing-ui

echo "Starting docker compose..."
sudo docker compose up