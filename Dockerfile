FROM eclipse-temurin:17-jre as build

MAINTAINER edeesan

COPY build/libs/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/cards-0.0.1-SNAPSHOT.jar"]

