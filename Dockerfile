FROM airhacks/glassfish
COPY ./target/jax-rs-exception-handling.war ${DEPLOYMENT_DIR}
