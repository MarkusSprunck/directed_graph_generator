#!/usr/bin/env bash
docker container stop dgg
docker container rm dgg
docker build --no-cache -t dgg-image:$1 ../.  --rm=true
docker run -d -p 8080:8080  -it  --name  dgg  dgg-image:$1
docker tag dgg-image:$1 dgg:latest
docker container list

