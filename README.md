# swagger-codegen-maven-plugin & Feign Example

This is an example which generates client stubs through swagger
api specification.

    io.swagger.codegen.v3:swagger-codegen-maven-plugin:3.0.8

    io.github.openfeign:xxx:9.7.0
    
    swagger: "2.0"

## Configurations

1. swagger-codegen-plugin configuration


    <plugin>
       <groupId>io.swagger.codegen.v3</groupId>
       <artifactId>swagger-codegen-maven-plugin</artifactId>
       <version>3.0.8</version>
       <executions>
          <execution>
            <id>generate-swagger-spring</id>
            <phase>generate-sources</phase>
            <goals>
              <goal>generate</goal>
            </goals>
            <configuration>
              <language>java</language>
              <library>feign</library>
              <inputSpec>${project.basedir}/src/main/resources/petstore.yaml</inputSpec>
              <generateApiTests>false</generateApiTests>
              <configOptions>
                 <sourceFolder>src/gen/java/main</sourceFolder>
              </configOptions>
            </configuration>
          </execution>
       </executions>
    </plugin>
    `

2. Java Configuration


    @Configuration
    @ComponentScan({"com.swagger.microservice.testservice.*"})
    public class Config {
    
        private String basePath = "https://petstore.swagger.io/v2";
    
        @Bean(name = "petApi")
        public PetApi getPetApi() {
            final PetApi api = Feign
                    .builder()
                    .client(new OkHttpClient())
                    .encoder(new JacksonEncoder())
                    .decoder(new JacksonDecoder())
                    .logLevel(Logger.Level.HEADERS)
                    .options(new Request.Options(1000, 2000))
                    .target(PetApi.class, basePath);
            return api;
        }
    }


#### References:

http://editor.swagger.io/

https://github.com/swagger-api/swagger-codegen

https://github.com/OpenFeign/feign
