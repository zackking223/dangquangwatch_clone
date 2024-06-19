To test it out:

1. Start the Spring Boot application with the local profile. `mvn spring-boot:run`
2. Run `npm run build && npm run watch` from the command line.
3. As a final change to the pom.xml, we can add a profile that calls our production NPM scripts. At release time, be sure to enable this Maven profile. `mvn spring-boot:run -P release`