package tech.jhipster.lite.generator.ci.github.actions.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.ci.github.actions.domain.GitHubActionsService;
import tech.jhipster.lite.generator.project.domain.Project;

@Service
public class GitHubActionsApplicationService {

  private final GitHubActionsService gitHubActionsService;

  public GitHubActionsApplicationService(GitHubActionsService gitHubActionsService) {
    this.gitHubActionsService = gitHubActionsService;
  }

  public void addGitHubActions(Project project) {
    gitHubActionsService.addGitHubActions(project);
  }
}
