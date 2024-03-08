package com.monstarlab.api.security

import com.monstarlab.common.exception.StarterErrorMessage
import com.monstarlab.domain.security.JwtUser
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import com.monstarlab.api.annotation.Permitted
import com.monstarlab.common.exception.StarterExternalException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.AsyncHandlerInterceptor

@Component
class StarterApiPermissionInterceptor: AsyncHandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val jwtUser = SecurityContextHolder.getContext().authentication.principal
        if (jwtUser == null || jwtUser !is JwtUser) {
            return true
        }
        if (handler !is HandlerMethod) {
            return true
        }
        val authorizationAnnotation = handler.getMethodAnnotation(Permitted::class.java)
            ?: return true
        if ((jwtUser.role.permissions intersect authorizationAnnotation.permissions.toSet()).isEmpty()) {
            throw StarterExternalException(
                StarterErrorMessage.UserNotPermitted,
                jwtUser.userId,
                jwtUser.role,
                authorizationAnnotation.permissions,
            )
        }
        return true
    }
}