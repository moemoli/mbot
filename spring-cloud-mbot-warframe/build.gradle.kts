


version = "0.0.1-SNAPSHOT"
description = "spring-cloud-mbot-warframe"


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.cloud:spring-cloud-starter")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("org.projectlombok:lombok")
    implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.14")
    implementation("org.dromara.mpe:mybatis-plus-ext-autotable-core:3.5.14-EXT883")
    implementation("org.dromara.mpe:mybatis-plus-ext-processor:3.5.14-EXT883")
    annotationProcessor("org.dromara.mpe:mybatis-plus-ext-processor:3.5.14-EXT883")
    kapt("org.dromara.mpe:mybatis-plus-ext-processor:3.5.14-EXT883")
    implementation("com.alibaba:druid-spring-boot-starter:1.2.23")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${property("springAlibabaCloudVersion")}")
    }
}






