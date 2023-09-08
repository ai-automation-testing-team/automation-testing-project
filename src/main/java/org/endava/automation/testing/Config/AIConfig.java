package org.endava.automation.testing.Config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:ai-config.properties")
public interface AIConfig extends Config {

    @Key("repo.path")
    String repoPath();

    @Key("token")
    String token();

    @Key("repo.owner")
    String repoOwner();

    @Key("repo.name")
    String repoName();

    @Key("pr.title")
    String prTitle();

    @Key("pr.description")
    String prDescription();
}
