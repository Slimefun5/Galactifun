plugins {
    java
    id("com.gradleup.shadow") version "9.3.2"
    id("io.github.intisy.github-gradle") version "1.8.3"
}

group = "io.github.addoncommunity.galactifun"
version = "1.0.0"
description = "Galactifun is a Slimefun addon that adds galaxies, star systems, planets, moons, rockets, and spacesuits."

github {
    accessToken = System.getenv("GITHUB_TOKEN") ?: ""
    publish {
        tag = System.getenv("GITHUB_REF_NAME")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.Slimefun5:SlimefunMetrics:master-SNAPSHOT")
    githubCompileOnly("Slimefun5:Slimefun5:gh-v5.2.3.2")
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")

    githubImplementation("Slimefun5:InfinityLib:v1.3.13")
    compileOnly("com.github.Slimefun.dough:dough-api:cb22e71335")
    compileOnly("commons-lang:commons-lang:2.6")
    compileOnly("commons-codec:commons-codec:1.17.1")
    compileOnly("it.unimi.dsi:fastutil:8.5.3")
    
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.15.2")
    testImplementation("org.slf4j:slf4j-simple:2.0.16")
    testImplementation("org.mockbukkit.mockbukkit:mockbukkit-v1.21:4.107.0") {
        exclude(group = "org.jetbrains", module = "annotations")
    }
}

configurations.testImplementation {
    extendsFrom(configurations.compileOnly.get())
}

// options.release overrides TARGET_JVM_VERSION_ATTRIBUTE to 8, blocking Paper 1.17 resolution.
// Force compileClasspath to resolve with JVM 25 compatibility while still compiling to Java 8 bytecode.
configurations.named("compileClasspath") {
    attributes {
        attribute(org.gradle.api.attributes.java.TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 25)
    }
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(8)
    }
    processResources {
        filesMatching("plugin.yml") {
            expand("version" to project.version)
        }
    }
    jar {
        enabled = false
    }
    shadowJar {
        relocate("org.bstats", "galactifun.libs.bstats")
        archiveFileName.set("Galactifun-1.0.0-UNOFFICIAL.jar")
        relocate("io.github.mooy1.infinitylib", "io.github.addoncommunity.galactifun.infinitylib")
        exclude("META-INF/**")
        // Core is provided at runtime (depend: Slimefun); never bundle it.
        exclude("io/github/thebusybiscuit/slimefun5/**")
        exclude("me/mrCookieSlime/**")
    }
    build {
        dependsOn(shadowJar)
    }
    compileTestJava {
        enabled = false
    }
    test {
        enabled = false
    }
}


// Trigger CI

// Trigger CI again

