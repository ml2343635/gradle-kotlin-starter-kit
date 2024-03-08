package com.monstarlab.api.security

import com.monstarlab.domain.security.JwtToken
import com.monstarlab.service.component.JwtComponent
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class StarterApiAuthenticationProvider(
    private val jwtComponent: JwtComponent,
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val jwtUser = jwtComponent.getJwtUser(authentication as JwtToken)
        return StarterApiAuthenticationToken(
            jwtUser = jwtUser,
            roles = setOf(
                SimpleGrantedAuthority(jwtUser.role.name),
            ),
        )
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return JwtToken::class.java.isAssignableFrom(authentication)
    }
}