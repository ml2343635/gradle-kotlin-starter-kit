package com.monstarlab.service.configuration

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.monstarlab.common.cache.CacheWriter
import com.monstarlab.storage.mysql.configuration.StarterMysqlConfiguration
import com.monstarlab.common.configuration.StarterCommonConfiguration
import org.springframework.context.annotation.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@Import(
    StarterMysqlConfiguration::class,
    StarterCommonConfiguration::class,
)
// TODO: Replace to your package name
@ComponentScan("com.monstarlab.service.*")
class StarterServiceConfiguration() {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun cacheWriter(): CacheWriter {
        return CacheWriter("/tmp")
    }

    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModule(SimpleModule())
            .registerModule(KotlinModule())
            .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true)
            .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
    }
}