import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.1"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
	kotlin("plugin.jpa") version "1.4.21"
}
repositories {
	mavenLocal()
	maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
	jcenter()
	mavenCentral()
	maven { url = project.uri("https://jitpack.io") }
}
group = "tech.openEdgn"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.5.1")
	implementation("org.springdoc:springdoc-openapi-ui:1.5.1")
	implementation("com.github.OpenEdgn.Logger4K:core:1.3.0")
	implementation("com.github.OpenEdgn.Logger4K:logger-slf4j:1.3.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
allprojects {
	repositories {
		mavenLocal()
		maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
		jcenter()
		mavenCentral()
		maven { url = project.uri("https://jitpack.io") }
	}
}