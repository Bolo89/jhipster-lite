package tech.jhipster.lite.generator.buildtool.gradle.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.buildtool.gradle.domain.GradleService;
import tech.jhipster.lite.generator.project.domain.Project;

@Service
public class GradleApplicationService {

  private final GradleService gradleService;

  public GradleApplicationService(GradleService gradleService) {
    this.gradleService = gradleService;
  }

  public void init(Project project) {
    gradleService.initJava(project);
  }

  public void addBuildGradle(Project project) {
    gradleService.addJavaBuildGradleKts(project);
  }

  public void addGradleWrapper(Project project) {
    gradleService.addGradleWrapper(project);
  }
}
