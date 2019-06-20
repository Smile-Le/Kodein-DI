import org.jetbrains.kotlin.gradle.tasks.*

plugins {
    id("org.kodein.library.jvm")
    id("org.openjfx.javafxplugin") version "0.0.7"
}

kodeinLib {
    dependencies {
        api(project(":kodein-di-core") target "jvm")
    }
}

javafx {
    version = "12.0.1"
    modules("javafx.controls")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation("no.tornado:tornadofx:1.7.19")
    testImplementation(project(":kodein-di-generic-jvm"))
    testImplementation("org.testfx:testfx-core:4.0.4-alpha")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}