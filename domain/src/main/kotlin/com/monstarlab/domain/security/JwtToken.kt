package com.monstarlab.domain.security

import org.springframework.security.authentication.AbstractAuthenticationToken

class JwtToken(val token: String): AbstractAuthenticationToken(emptySet()) {
    override fun getCredentials(): String {
        return token
    }

    override fun getPrincipal() {}
}
