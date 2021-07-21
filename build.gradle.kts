import nu.studer.gradle.jooq.JooqGenerate

plugins {
    application
    id("nu.studer.jooq") version "5.2"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(16))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Versions
    val sqliteVersion = "3.34.0"
    val myBatisVersion = "3.5.6"
    val jdbiVersion = "3.19.0"
    val jooqVersion = "3.14.8"
    val log4j2Version = "2.14.1"
    val lombokVersion = "1.18.20"
    val junitVersion = "5.7.1"
    val assertjVersion = "3.19.0"
    val springVersion = "5.3.7"

    // SQL
    implementation("org.xerial:sqlite-jdbc:$sqliteVersion")
    implementation("org.mybatis:mybatis:$myBatisVersion")
    implementation("org.jdbi:jdbi3-core:$jdbiVersion")
    implementation("org.jooq:jooq:$jooqVersion")
    implementation("org.jooq:jooq-meta:$jooqVersion")
    implementation("org.jooq:jooq-codegen:$jooqVersion")
    jooqGenerator("org.xerial:sqlite-jdbc:$sqliteVersion")
    implementation("org.springframework:spring-jdbc:$springVersion")

    // Logging
    implementation("org.apache.logging.log4j:log4j-api:$log4j2Version")
    implementation("org.apache.logging.log4j:log4j-core:$log4j2Version")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4j2Version")

    // Lombok
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    // Test
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.sqlite.JDBC"
                    url = "jdbc:sqlite:person.sqlite"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.sqlite.SQLiteDatabase"
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "be.inniger.scratch.sql.dao.jooq"
                        directory = "src/generated/java"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}

sourceSets {
    getByName("main").java.srcDirs("src/generated/java")
}

tasks.getByName<JooqGenerate>("generateJooq").enabled = false
