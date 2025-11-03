package moe.imoli.mbot.cloud.warframe

import org.dromara.autotable.springboot.EnableAutoTable
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableAutoTable
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = ["moe.imoli.mbot.cloud.warframe.data.entity"])
class SpringCloudMbotWarframeApplication

fun main(args: Array<String>) {
    runApplication<SpringCloudMbotWarframeApplication>(*args)
}
