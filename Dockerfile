FROM maven:latest AS build-project

ADD . ./docker-spring-boot
WORKDIR /docker-spring-boot
RUN mvn clean install


FROM openjdk:17-alpine
EXPOSE 8080

COPY --from=build-project /docker-spring-boot/target/energy-platform-0.0.1-SNAPSHOT.jar ./docker-spring-boot.jar
ENTRYPOINT ["java", "-jar","./docker-spring-boot.jar"]


#FROM maven:latest AS builder
#
#COPY ./src/ /root/src
#COPY ./pom.xml /root/
#WORKDIR /root
#RUN mvn package
#RUN java -Djarmode=layertools -jar /root/target/energy-platform-0.0.1-SNAPSHOT.jar list
#RUN java -Djarmode=layertools -jar /root/target/energy-platform-0.0.1-SNAPSHOT.jar extract
#RUN ls -l /root
#
#FROM openjdk:17-alpine
#
#ENV TZ=UTC
#ENV DB_IP=127.0.0.1
#ENV DB_PORT=3307
#ENV DB_USER=root
#ENV DB_PASSWORD=cara12345
#ENV DB_DBNAME=energy_utility_platform
#
#
#COPY --from=builder /root/dependencies/ ./
#COPY --from=builder /root/snapshot-dependencies/ ./
#
#RUN sleep 10
#COPY --from=builder /root/spring-boot-loader/ ./
#COPY --from=builder /root/application/ ./
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher","-XX:+UseContainerSupport -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 -Xms512m -Xmx512m -XX:+UseG1GC -XX:+UseSerialGC -Xss512k -XX:MaxRAM=72m"]
