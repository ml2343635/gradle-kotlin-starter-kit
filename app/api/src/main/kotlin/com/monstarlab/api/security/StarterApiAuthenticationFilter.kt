package com.monstarlab.api.security

import com.monstarlab.common.constant.StarterConstant
import com.monstarlab.domain.security.JwtUser
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import com.monstarlab.domain.security.JwtToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class StarterApiAuthenticationFilter(
    private val authenticationManager: AuthenticationManager,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val context = SecurityContextHolder.getContext()
            getToken(request)?.let { token ->
                context.authentication?.isAuthenticated
                    ?.let { authenticated ->
                        if (!authenticated) {
                            verifyToken(token)
                        }
                    }
                    ?: run {
                        verifyToken(token)
                    }
            }
            context.authentication
                ?.let { it.principal as? JwtUser }
                ?.let {
                    response.addHeader(StarterConstant.ADMIN_ROLE, it.role.name)
                }
            filterChain.doFilter(request, response)
        } catch (ex: Exception) {
            logger.error("Failed to verify token: ${getToken(request)}", ex)
            response.status = HttpServletResponse.SC_UNAUTHORIZED
        }
    }

    private fun verifyToken(token: String) {
        val context = SecurityContextHolder.getContext()
        val result = authenticationManager.authenticate(
            JwtToken(token)
        )
        context.authentication = result
    }

    private fun getToken(request: HttpServletRequest): String? {
        val authorization = request.getHeader("Authorization")
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null
        }
        return authorization.replace("Bearer ", "")
    }

}