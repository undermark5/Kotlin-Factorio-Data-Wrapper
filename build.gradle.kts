/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api(libs.org.luaj.luaj.jse)
    api(libs.org.json.json)
    api(libs.com.google.guava.guava)
    api(libs.com.diffplug.durian.durian)
}

group = "com.demod.factorio"
version = "0.0.1-SNAPSHOT"
description = "FactorioDataWrapper"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}