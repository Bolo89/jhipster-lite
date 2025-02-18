package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.infrastructure.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;
import static tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.application.SpringdocAssert.*;
import static tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.domain.SpringdocConstants.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;
import tech.jhipster.lite.module.infrastructure.secondary.TestJHipsterModules;

@IntegrationTest
@AutoConfigureMockMvc
class SpringdocResourceIT {

  @Autowired
  private MavenApplicationService mavenApplicationService;

  @Autowired
  private SpringBootApplicationService springBootApplicationService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldInit() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(FileUtils.tmpDirForTest());
    projectDTO.getGeneratorJhipster().put(BASE_NAME, "MyProject");

    Project project = ProjectDTO.toProject(projectDTO);

    TestJHipsterModules.applyInit(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/api-documentations/springdoc/init")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    assertDependencies(project);
    assertJavaFiles(project);
    assertProperties(project);

    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, "myProject");
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, DEFAULT_API_TITLE);
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, DEFAULT_API_DESCRIPTION);
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, DEFAULT_LICENSE_NAME);
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, DEFAULT_LICENSE_URL);
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, DEFAULT_EXT_DOC_DESCRIPTION);
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, DEFAULT_EXT_DOC_URL);
  }

  @Test
  void shouldInitWithSecurityJWT() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    projectDTO.folder(FileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);

    TestJHipsterModules.applyInit(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/api-documentations/springdoc/init-with-security-jwt")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    assertDependencies(project);
    assertJavaFilesWithSecurityJWT(project);
    assertProperties(project);
  }
}
