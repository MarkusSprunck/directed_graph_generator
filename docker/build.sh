#!/usr/bin/env bash
docker build --no-cache -t sprunck/directed-graph-generator:latest ../.  --rm=true
docker tag sprunck/directed-graph-generator:latest sprunck/directed-graph-generator:v3
docker tag sprunck/directed-graph-generator:latest ecs-globpidck01.otc.ihkmun/dgg/bl:1.3.0
docker push sprunck/directed-graph-generator:v3
docker push sprunck/directed-graph-generator:latest

