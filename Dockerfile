FROM openjdk:11
COPY target/wikibot-1.0-SNAPSHOT.jar wikibot.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","wikibot.jar"]