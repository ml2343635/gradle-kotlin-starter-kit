package com.monstarlab.service.property

import org.springframework.boot.context.properties.ConfigurationProperties

// TODO: Replace to your own prefix
@ConfigurationProperties(prefix = "starter.jwt")
data class JwtProperties(
    val secretKey: String,
    val expirationTime: Long,
)