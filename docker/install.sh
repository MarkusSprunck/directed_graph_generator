#!/usr/bin/env bash
docker container stop dgg
docker container rm dgg
docker build --no-cache -t dgg-image:latest ../.  --rm=true
docker run -d -p 8080:8080  -it  --name  dgg  dgg-image:latest
docker tag dgg-image:latest ecs-globpidck01.otc.ihkmun/dgg/bl:1.1.0

