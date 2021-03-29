plugins {
    kotlin("jvm") version "1.4.31"
    idea
    signing
    `maven-publish`
}

group = "io.datalbry.testcontainers"
version = "1.0.0"

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    withJavadocJar()
    withSourcesJar()
}

configure<PublishingExtension> {
    publications {
        repositories {
            maven {
                name = "MavenCentral"
                url = if (project.rootProject.version.toString().endsWith("SNAPSHOT")) {
                    uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                } else {
                    uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                }
                credentials {
                    username = project.findProperty("maven.central.username") as String
                    password = project.findProperty("maven.central.password") as String
                }
            }
        }
        create<MavenPublication>("jar") {
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("Alxndria Emulator Testcontainer")
                description.set("In-memory emulator for the alxndria platform")
                url.set("https://github.com/datalbry/testcontainer-alxndria")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("datalbry")
                        name.set("DataLbry")
                        email.set("devops@datalbry.io")
                    }
                }
                scm {
                    connection.set("https://github.com/datalbry/testcontainer-alxndria.git")
                    developerConnection.set("scm:git:ssh:git@github.com:datalbry/testcontainer-alxndria.git")
                    url.set("https://github.com/datalbry/testcontainer-alxndria")
                }
            }
        }
    }
}

configure<SigningExtension> {
    sign(configurations.archives.get())
}


signing {
    sign(publishing.publications["jar"])
}

dependencies {
    val testcontainersVersion = "1.15.2"
    implementation(kotlin("stdlib"))
    implementation("org.testcontainers:testcontainers:$testcontainersVersion")

    testImplementation("org.testcontainers:junit-jupiter:$testcontainersVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
