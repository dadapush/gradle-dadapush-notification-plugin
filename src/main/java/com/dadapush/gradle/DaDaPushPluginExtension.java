package com.dadapush.gradle;

@SuppressWarnings("unused")
public class DaDaPushPluginExtension {

  private String basePath = "https://www.dadapush.com";
  private String channelToken;
  private String title = "(not set title)";
  private String content = "(not set content)";
  private Boolean failOnError = false;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getFailOnError() {
    return failOnError;
  }

  public void setFailOnError(Boolean failOnError) {
    this.failOnError = failOnError;
  }

  public String getBasePath() {
    return basePath;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getChannelToken() {
    return channelToken;
  }

  public void setChannelToken(String channelToken) {
    this.channelToken = channelToken;
  }

}
