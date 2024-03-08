package com.monstarlab.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityFilterAutoConfiguration::class])
class StarterAdminApplication

fun main(args: Array<String>) {
    runApplication<StarterAdminApplication>(*args)
}
