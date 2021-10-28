From openjdk:8
EXPOSE 8090
ADD /target/GestionCondidature.jar GestionCondidature.jar
ENTRYPOINT ["java", "-jar", "/GestionCondidature.jar"]