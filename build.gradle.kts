plugins {
    id ("java")
    id ("maven-publish")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation ("mysql:mysql-connector-java:8.0.28")
    testImplementation ("junit:junit:4.13.2")
    // Add any other dependencies here
}

tasks.test {
    useJUnitPlatform()
}