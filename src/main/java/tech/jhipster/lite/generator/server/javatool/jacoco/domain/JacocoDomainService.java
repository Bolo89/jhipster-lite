package tech.jhipster.lite.generator.server.javatool.jacoco.domain;

import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class JacocoDomainService implements JacocoService {

  public static final String SOURCE = "server/javatool/jacoco";
  public static final String PATCH = "jacoco-check-coverage.patch";

  private final ProjectRepository projectRepository;

  public JacocoDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void addCheckMinimumCoverage(Project project) {
    projectRepository.gitInit(project);
    projectRepository.add(ProjectFile.forProject(project).withSource(SOURCE, PATCH).withDestinationFolder(".jhipster"));
    projectRepository.gitApplyPatch(project, FileUtils.getPath(project.getFolder(), ".jhipster", PATCH));
  }
}
