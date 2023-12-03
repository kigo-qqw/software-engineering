plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass.set("ru.nstu.se.lab1.main.Main")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml")
}
