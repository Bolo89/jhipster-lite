<template>
  <div id="v-pills-springboot" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-profile-tab">
    <div class="d-flex flex-column gap-3">
      <div>
        <GeneratorButtonVue
          :label="'Spring Boot'"
          :service="'spring-boot'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBoot"
        />
        <GeneratorButtonVue
          :label="'Spring MVC Tomcat'"
          :service="'spring-boot-mvc-with-tomcat'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootMvcTomcat"
        />
        <GeneratorButtonVue
          :label="'Spring MVC Undertow'"
          :service="'spring-boot-mvc-with-undertow'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootMvcUndertow"
        />
        <GeneratorButtonVue
          :label="'Spring Webflux Netty'"
          :service="'spring-boot-webflux-netty'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootWebfluxNetty"
        />
        <GeneratorButtonVue
          :label="'Spring Boot Actuator'"
          :service="'spring-boot-actuator'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootActuator"
        />
        <GeneratorButtonVue :label="'Spring Doc'" :service="'spring-doc'" :selector-prefix="selectorPrefix" @click.prevent="addSpringDoc" />
        <GeneratorButtonVue
          :label="'Java Archunit'"
          :service="'java-archunit'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addJavaArchunit"
        />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'AOP Logging'"
          :service="'aop-logging'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootAopLogging"
        />
        <GeneratorButtonVue
          :label="'Logstash'"
          :service="'logstash'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootLogstash"
        />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'Security JWT'"
          :service="'spring-boot-jwt'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootSecurityJWT"
        />
        <GeneratorButtonVue
          :label="'Security JWT Basic Auth'"
          :service="'spring-boot-jwt-with-basic-authentication'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootSecurityJWTBasicAuth"
        />
        <GeneratorButtonVue
          :label="'SpringDoc OpenApi with Security JWT'"
          :service="'springdoc-openapi-with-security-jwt'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringDocOpenApiSecurityJWT"
        />
        <GeneratorButtonVue
          :label="'Security OAuth2'"
          :service="'spring-boot-oauth2'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootSecurityOAuth2"
        />
        <GeneratorButtonVue
          :label="'OAuth2 Account Context'"
          :service="'spring-boot-oauth2-account'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootSecurityOAuth2Account"
        />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'PostgreSQL'"
          :service="'postgresql'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addPostgreSQL"
        />
        <GeneratorButtonVue :label="'MySQL'" :service="'mysql'" :selector-prefix="selectorPrefix" @click.prevent="addMySQL" />
        <GeneratorButtonVue :label="'MariaDB'" :service="'mariadb'" :selector-prefix="selectorPrefix" @click.prevent="addMariaDB" />
        <GeneratorButtonVue :label="'MongoDB'" :service="'mongodb'" :selector-prefix="selectorPrefix" @click.prevent="addMongoDB" />
        <GeneratorButtonVue :label="'MSSQL'" :service="'mssql'" :selector-prefix="selectorPrefix" @click.prevent="addMSSQL" />
      </div>
      <div>
        <GeneratorButtonVue :label="'Flyway'" :service="'flyway'" :selector-prefix="selectorPrefix" @click.prevent="addFlyway" />
        <GeneratorButtonVue
          :label="'Flyway with users'"
          :service="'flyway-with-users-and-authority-changelogs'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addFlywayUser"
        />
        <GeneratorButtonVue :label="'Liquibase'" :service="'liquibase'" :selector-prefix="selectorPrefix" @click.prevent="addLiquibase" />
        <GeneratorButtonVue
          :label="'Liquibase with users'"
          :service="'liquibase-with-users-and-authority-changelogs'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addLiquibaseUser"
        />
        <GeneratorButtonVue :label="'Mongock'" :service="'mongock'" :selector-prefix="selectorPrefix" @click.prevent="addMongock" />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'SpringCloud Eureka client'"
          :service="'eureka-client'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addEurekaClient"
        />
        <GeneratorButtonVue
          :label="'SpringCloud Consul'"
          :service="'consul'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addConsul"
        />
        <GeneratorButtonVue
          :label="'SpringCloud Config client'"
          :service="'spring-cloud'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringCloudConfigClient"
        />
      </div>
      <div>
        <GeneratorButtonVue :label="'Dev tools'" :service="'dev-tools'" :selector-prefix="selectorPrefix" @click.prevent="addDevTools" />
        <GeneratorButtonVue
          :label="'Docker file'"
          :service="'dockerfile'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addDockerFile"
        />
        <GeneratorButtonVue :label="'Docker Jib'" :service="'jib'" :selector-prefix="selectorPrefix" @click.prevent="addJib" />
        <GeneratorButtonVue
          :label="'Async config'"
          :service="'spring-boot-async'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootAsync"
        />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'Pulsar'"
          :service="'spring-boot-pulsar'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addPulsar"
        />
        <GeneratorButtonVue :label="'Kafka'" :service="'spring-boot-kafka'" :selector-prefix="selectorPrefix" @click.prevent="addKafka" />
        <GeneratorButtonVue
          :label="'Dummy Producer and Consumer for Kafka'"
          :service="'spring-boot-kafka-dummy-producer-consumer'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addKafkaDummyProducerConsumer"
        />
        <GeneratorButtonVue
          :label="'AKHQ for Kafka'"
          :service="'spring-boot-kafka-akhq'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addKafkaAkhq"
        />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'Ehcache with Java config'"
          :service="'ehcache-with-java-config'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addEhcacheWithJavaConfig"
        />
        <GeneratorButtonVue
          :label="'Ehcache with XML config'"
          :service="'ehcache-with-xml-config'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addEhcacheWithXml"
        />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'Cucumber'"
          :service="'spring-boot-cucumber'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addCucumber"
        />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'Dummy feature'"
          :service="'spring-boot-dummy-feature'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootDummyFeature"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./SpringBootGenerator.component.ts"></script>
