package com.monstarlab.common.property

import org.springframework.boot.context.properties.ConfigurationProperties

// TODO: Replace to your own prefix
@ConfigurationProperties(prefix = "starter.crypto")
data class CryptoProperties(
    val secretKey: String,
)