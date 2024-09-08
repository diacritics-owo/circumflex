---
sidebar_position: 0
slug: /
---

# Circumflex

A Fabric/Quilt library mod

## Features

- [Registry initializer API](/registry-initializer) (inspired by [owo-lib](https://docs.wispforest.io/owo/registration/))
- [Command registration API](/command-registration)
- [More widgets](/widgets)

## Adding the Dependency

Add the following to your project:

```groovy title="build.gradle"
repositories {
  exclusiveContent {
    forRepository {
      maven {
        name = "Modrinth"
        url = "https://api.modrinth.com/maven"
      }
    }

    filter {
      includeGroup "maven.modrinth"
    }
  }
}

dependencies {
  modImplementation "maven.modrinth:circumflex:${project.circumflex_version}"
}
```

```toml title="gradle.properties"
circumflex_version=1.2.0
```

```json title="fabric.mod.json"
{
  "depends": {
    "circumflex": "~1.2.0"
  }
}
```
