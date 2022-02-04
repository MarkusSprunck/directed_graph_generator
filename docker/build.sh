#!/usr/bin/env bash
docker build --no-cache -t sprunck/directed-graph-generator:latest ../.  --rm=true
docker tag sprunck/directed-graph-generator:latest sprunck/directed-graph-generator:v3
docker push sprunck/directed-graph-generator:v3
docker push sprunck/directed-graph-generator:latest

