FROM openjdk:11
COPY target/wikibot-1.0-SNAPSHOT.war wikibot.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","wikibot.war"]