plugins {
	id ("java")
	id ("org.springframework.boot") version "3.3.6"
	id ("io.spring.dependency-management") version "1.1.4"
}

ext["springCloudVersion"] = "2023.0.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

allprojects {
	group = "org.example"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}

}

subprojects {
	apply {
		plugin("java")
		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
		plugin("java-library")
	}

	java {
		toolchain {
			languageVersion = JavaLanguageVersion.of(21)
		}
	}
	dependencies {
		compileOnly("org.projectlombok:lombok:1.18.30")
		annotationProcessor("org.projectlombok:lombok:1.18.30")
		implementation("org.springframework.boot:spring-boot-starter-web")
		testImplementation(platform("org.junit:junit-bom:5.9.1"))
		testImplementation("org.junit.jupiter:junit-jupiter")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}

	dependencyManagement {
		imports {
			mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
		}
	}

	tasks.test {
		useJUnitPlatform()
	}
}