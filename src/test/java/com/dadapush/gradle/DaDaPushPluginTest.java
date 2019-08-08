package com.dadapush.gradle;

import static org.junit.Assert.assertTrue;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

public class DaDaPushPluginTest {

  @Test
  public void apply() {
    Project project = ProjectBuilder.builder().build();
    project.getPluginManager().apply("com.dadapush.gradle");
    for (Plugin plugin : project.getPlugins()) {
      System.out.println(plugin.getClass());
    }
    assertTrue(project.getPlugins().hasPlugin(DaDaPushPlugin.class));
  }
}