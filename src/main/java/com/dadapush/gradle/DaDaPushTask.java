package com.dadapush.gradle;

import com.dadapush.client.ApiClient;
import com.dadapush.client.ApiException;
import com.dadapush.client.Configuration;
import com.dadapush.client.api.DaDaPushMessageApi;
import com.dadapush.client.model.MessagePushRequest;
import com.dadapush.client.model.ResultOfMessagePushResponse;
import org.apache.commons.lang3.StringUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;

@SuppressWarnings("unused")
public class DaDaPushTask extends DefaultTask {

  @Optional
  @Input
  private String basePath = "https://www.dadapush.com";
  @Optional
  @Input
  private String channelToken;
  @Optional
  @Input
  private String title;
  @Optional
  @Input
  private String content;

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @TaskAction
  public void message() {
    DaDaPushPluginExtension extension = getProject().getExtensions()
        .findByType(DaDaPushPluginExtension.class);
    String channelToken =
        StringUtils.isNotEmpty(this.channelToken) ? this.channelToken : extension.getChannelToken();
    String basePath =
        StringUtils.isNotEmpty(this.basePath) ? this.basePath : extension.getBasePath();
    String title = StringUtils.isNotEmpty(this.title) ? this.title : extension.getTitle();
    String content = StringUtils.isNotEmpty(this.content) ? this.content : extension.getContent();
    Boolean failOnError = extension.getFailOnError();

    if (StringUtils.isEmpty(channelToken)) {
      System.err.println("not set channelToken");
      return;
    }
    ApiClient apiClient = Configuration.getDefaultApiClient();
    apiClient.setBasePath(basePath);
    DaDaPushMessageApi apiInstance = new DaDaPushMessageApi(apiClient);
    MessagePushRequest body = new MessagePushRequest();
    body.setTitle(StringUtils.substring(title, 0, 50));
    body.setContent(StringUtils.substring(content, 0, 500));
    body.setNeedPush(true);
    try {
      ResultOfMessagePushResponse result = apiInstance.createMessage(body, channelToken);
      if (result.getCode() == 0) {
        System.out
            .println("send notification success, messageId=" + result.getData().getMessageId());
      } else {
        System.err.println(
            "send notification fail, detail: " + result.getCode() + " " + result.getErrmsg());
      }
    } catch (ApiException e) {
      if (!failOnError) {
        getLogger().error("send notification fail", e);
      } else {
        throw new RuntimeException("send notification fail", e);
      }
    }

  }

}
