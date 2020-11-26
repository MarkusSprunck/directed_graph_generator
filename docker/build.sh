#!/usr/bin/env bash
docker build --no-cache -t dgg-image:latest ../.  --rm=true
docker tag dgg-image:latest ecs-globpidck01.otc.ihkmun/dgg/bl:1.1.0

