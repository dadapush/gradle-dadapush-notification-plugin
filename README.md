# Gradle DaDaPush Notification Plugin

```groovy
import com.dadapush.client.gradle.DaDaPushTask

plugins {
  id "com.dadapush.gradle" version "1.0.0"
}

apply plugin: 'com.dadapush.gradle'
```

## global parameter
- `basePath`: default value: "https://www.dadapush.com"
- `channelToken`: go to [DaDaPush](https://www.dadapush.com), create new channel.
- `title`: default value: "(not set title)"
- `content`: default value: "(not set content)"
- `failOnError`: default value: false

## task parameter
- `basePath`: no default value, if it is not set, will use global value
- `channelToken`: no default value, if it is not set, will use global value
- `title`: no default value, if it is not set, will use global value
- `content`: no default value, if it is not set, will use global value

```groovy
dadapush {
  channelToken "YOUR_CHANNEL_TOKEN"
  title "${project.name}"
  content "project: ${project.name}\n" +
          "version: ${project.version}\n" +
          "gradleVersion: ${project.gradle.gradleVersion}\n" +
          "buildDir: ${project.buildDir}\n"
  failOnError false
}

```

```groovy



task dadapushSend(type: DaDaPushTask) {
  title "${project.name} assemble success"
}

tasks.assemble.doLast() {
  dadapushSend.execute()
}

task dadapushSendForBuild(type: DaDaPushTask) {
  title "${project.name} build success"
  channelToken "YOUR_CHANNEL_TOKEN"
  content "project: ${project.name}\n" +
          "version: ${project.version}\n" +
          "gradleVersion: ${project.gradle.gradleVersion}\n" +
          "buildDir: ${project.buildDir}\n"
}

tasks.build.doLast() {
  dadapushSendForBuild.execute()
}
```