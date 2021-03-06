FROM airhacks/glassfish
COPY ./target/atx-web.war ${DEPLOYMENT_DIR}
