# Handling exceptions in a Java EE 8 REST app

It's very easy to handle exceptions in JAX-RS. All you need to do is to provide exception mappers to catch
your exceptions and translate them into Response objects.

This repository is a minimal example on how to do that.

It's helpful because it explains a few tricks that you need to know if you want to override the default exception
mappers that are present in your application server.

This code is very portable, but in the `com.andregs.rest.jersey` package I had to create two Jersey-specific
classes to workaround some issues.

All the instructions are documented as comments in the source code.

Happy reading!

# Build
mvn clean package && docker build -t com.andregs/jax-rs-exception-handling .

# RUN
docker rm -f jax-rs-exception-handling || true && docker run -d -p 8080:8080 -p 4848:4848 --name jax-rs-exception-handling com.andregs/jax-rs-exception-handling 

Read the server logs with `docker logs jax-rs-exception-handling`

Or you can simply build the war and deploy it in a Payara 5 or Glassfish app server.

* This project was created with Adam Bien's archetype: https://github.com/AdamBien/javaee8-essentials-archetype
