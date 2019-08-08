package com.dadapush.gradle;

import static org.junit.Assert.*;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

public class DaDaPushTaskTest {

  @Test
  public void message() {
    Project project = ProjectBuilder.builder().build();
    Task task = project.getTasks().create("message", DaDaPushTask.class);
    assertTrue(task != null);
  }
}