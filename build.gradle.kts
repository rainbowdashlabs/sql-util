plugins {
    java
    `maven-publish`
    `java-library`
    id("de.chojo.publishdata") version "1.0.8"
    id("org.cadixdev.licenser") version "0.6.1"
}

group = "de.chojo"
version = "1.5.0"
val testContainersVersion = "1.17.3"

repositories {
    mavenCentral()
}

license {
    header(rootProject.file("HEADER.txt"))
    include("**/*.java")
}

dependencies {
    api("org.slf4j", "slf4j-api", "1.7.36")

    api("com.zaxxer", "HikariCP", "5.0.1")
    api("org.jetbrains", "annotations", "23.0.0")

    // database driver
    testImplementation("org.xerial:sqlite-jdbc:3.39.2.0")
    testImplementation("org.postgresql", "postgresql", "42.4.1")
    testImplementation("org.mariadb.jdbc", "mariadb-java-client", "3.0.7")
    testImplementation("mysql", "mysql-connector-java", "8.0.30")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    // testcontainers
    testImplementation("org.testcontainers:junit-jupiter:1.17.3")
    testImplementation ("org.testcontainers", "testcontainers", testContainersVersion)
    implementation(platform("org.testcontainers:testcontainers-bom:$testContainersVersion"))

    // container
    testImplementation("org.testcontainers", "mysql", testContainersVersion)
    testImplementation("org.testcontainers", "mariadb", testContainersVersion)
    testImplementation("org.testcontainers", "postgresql", testContainersVersion)
}

publishData {
    useEldoNexusRepos()
    publishComponent("java")
}

publishing {
    publications.create<MavenPublication>("maven") {
        publishData.configurePublication(this)
    }

    repositories {
        maven {
            authentication {
                credentials(PasswordCredentials::class) {
                    username = System.getenv("NEXUS_USERNAME")
                    password = System.getenv("NEXUS_PASSWORD")
                }
            }

            name = "EldoNexus"
            setUrl(publishData.getRepository())
        }
    }
}

java {
    withSourcesJar()
    withJavadocJar()

    sourceCompatibility = JavaVersion.VERSION_15
}


tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}
