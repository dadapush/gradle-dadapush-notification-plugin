package com.dadapush.client.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class DaDaPushPlugin implements Plugin<Project> {

  @Override
  public void apply(Project project) {
    project.getExtensions()
        .create("dadapush", DaDaPushPluginExtension.class);

  }

}
