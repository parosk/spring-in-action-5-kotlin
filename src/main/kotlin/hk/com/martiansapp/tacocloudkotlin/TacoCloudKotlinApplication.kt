package hk.com.martiansapp.tacocloudkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
/*
* SpringBootApplication combine 3 annotation :
*
* 1. SpringBootConfiguration : Designates this class as a configuration class
* 2. EnableAutoConfiguration : Spring Boot to automatically configure any components that it thinks you’ll need
* 3. ComponentScan : Enables component scanning. This lets you declare other classes with annotations like
*                    @Component, @Controller, @Service, and others,
* 	                 to have Spring automatically discover them and register them as components in the Spring application context.
*/
@SpringBootApplication
class TacoCloudKotlinApplication

fun main(args: Array<String>) {
	runApplication<TacoCloudKotlinApplication>(*args)
}
