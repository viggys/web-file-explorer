FROM openjdk:11.0.7-jre-slim
ARG APP_DIR=/usr/apps
ARG HOME=/usr/mypc
ARG ID=mypc
ENV APP_OPTS ''
RUN addgroup $ID \
    && adduser --disabled-password \
    --ingroup $ID --home $HOME \
    --gecos '' \
    $ID
USER $ID:$ID
WORKDIR $APP_DIR
COPY --chown=$ID target/*.jar $APP_DIR/app.jar
CMD java -jar $APP_OPTS app.jar