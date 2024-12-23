plugins {
    id("buildsrc.convention.kotlin-jvm")
    `java-library`
    `maven-publish`
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


publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
