FROM openjdk:11.0.7-jre-slim
ARG HOME=/usr/apps
ARG ID=mypc
COPY /target/*.jar $HOME/app.jar
ENV APP_OPTS ''
WORKDIR $HOME
CMD java -jar $APP_OPTS app.jar