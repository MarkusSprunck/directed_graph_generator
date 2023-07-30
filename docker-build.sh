#!/usr/bin/env bash
docker build --no-cache -t directed-graph-generator:latest .  --rm=true
docker tag directed-graph-generator:latest sprunck/directed-graph-generator:latest
docker push sprunck/directed-graph-generator:latest



