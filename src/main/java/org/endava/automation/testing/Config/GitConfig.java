package org.endava.automation.testing.Config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:gitConfig.properties")
public interface GitConfig extends Config {

    @Key("repo.path")
    String repoPath();

    @Key("token")
    @ConverterClass(GitTokenConverter.class)
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
