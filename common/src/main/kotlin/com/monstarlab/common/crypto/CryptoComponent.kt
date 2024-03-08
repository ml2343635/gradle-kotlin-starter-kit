package com.monstarlab.common.crypto

import com.monstarlab.common.property.CryptoProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@Component
@EnableConfigurationProperties(CryptoProperties::class)
class CryptoComponent(
    private val cryptoProperties: CryptoProperties,
) {
    fun encrypt(string: String): String {
        return ChCrypto.aesEncrypt(string, cryptoProperties.secretKey)
    }

    fun decrypt(string: String): String {
        return ChCrypto.aesDecrypt(string, cryptoProperties.secretKey)
    }
}