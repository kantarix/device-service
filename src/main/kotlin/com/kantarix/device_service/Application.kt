package com.kantarix.device_service

import com.tuya.connector.spring.annotations.ConnectorScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@ConnectorScan(basePackages = ["com.kantarix.device_service.api.tuya"])
@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
