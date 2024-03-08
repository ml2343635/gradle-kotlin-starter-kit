package com.monstarlab.api.security

import com.monstarlab.domain.security.JwtUser
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

class StarterApiAuthenticationToken(
    private val jwtUser: JwtUser,
    roles: Set<SimpleGrantedAuthority>
) : AbstractAuthenticationToken(roles) {
    init {
        isAuthenticated = true
    }

    override fun getCredentials() {}

    override fun getPrincipal(): JwtUser {
        return jwtUser
    }
}