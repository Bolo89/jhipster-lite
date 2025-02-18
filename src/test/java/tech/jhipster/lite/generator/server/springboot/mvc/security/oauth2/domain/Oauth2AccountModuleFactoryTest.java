package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class Oauth2AccountModuleFactoryTest {

  private static final OAuth2AccountModuleFactory factory = new OAuth2AccountModuleFactory();

  @Test
  void shouldCreateOAuth2AccountModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture
      .propertiesBuilder(FileUtils.tmpDirForTest())
      .basePackage("com.jhipster.test")
      .projectBaseName("myapp")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModule(module)
      .createPrefixedFiles("src/main/java/com/jhipster/test/account/domain", "Account.java", "AccountsRepository.java")
      .createFile("src/main/java/com/jhipster/test/account/application/AccountsApplicationService.java")
      .and()
      .createPrefixedFiles("src/main/java/com/jhipster/test/account/infrastructure/primary", "RestAccount.java", "AccountsResource.java")
      .createPrefixedFiles(
        "src/main/java/com/jhipster/test/account/infrastructure/secondary",
        "OAuth2AccountsRepository.java",
        "OAuth2AuthenticationReader.java",
        "UnknownAuthenticationSchemeException.java"
      )
      .createFile("src/main/java/com/jhipster/test/account/package-info.java")
      .and()
      .createPrefixedFiles(
        "src/main/java/com/jhipster/test/useridentity/domain",
        "Email.java",
        "Firstname.java",
        "Lastname.java",
        "Name.java"
      )
      .createFile("src/main/java/com/jhipster/test/useridentity/package-info.java")
      .and()
      .createPrefixedFiles(
        "src/test/java/com/jhipster/test/useridentity/domain",
        "EmailTest.java",
        "FirstnameTest.java",
        "LastnameTest.java",
        "NameTest.java",
        "UsersIdentitiesFixture.java"
      )
      .createFile("src/test/java/com/jhipster/test/account/domain/AccountsFixture.java")
      .and()
      .createPrefixedFiles(
        "src/test/java/com/jhipster/test/account/infrastructure/primary",
        "RestAccountTest.java",
        "AccountsResourceIntTest.java",
        "AccountsResourceTest.java"
      )
      .createFile("src/test/java/com/jhipster/test/account/infrastructure/secondary/OAuth2AuthenticationReaderTest.java")
      .and()
      .createFile("src/test/java/com/jhipster/test/account/infrastructure/OAuth2TokenFixture.java");
  }
}
