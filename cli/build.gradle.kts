
plugins {
    java
    application
}

application.mainClass.set("up.TowerDefense.cli.CLILauncher")

dependencies {
    testImplementation("junit:junit:4.+")
    implementation("org.json:json:20171018")
}


