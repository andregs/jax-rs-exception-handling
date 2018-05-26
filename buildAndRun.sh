#!/bin/sh
mvn clean package && docker build -t com.andregs/jax-rs-exception-handling .
docker rm -f jax-rs-exception-handling || true && docker run -d -p 8080:8080 -p 4848:4848 --name jax-rs-exception-handling com.andregs/jax-rs-exception-handling 
