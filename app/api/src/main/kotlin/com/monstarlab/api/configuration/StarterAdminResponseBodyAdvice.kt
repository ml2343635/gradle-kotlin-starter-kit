package com.monstarlab.api.configuration

import com.monstarlab.common.constant.StarterConstant
import com.monstarlab.domain.enum.UserRole
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

// TODO: Replace to your package name
@ControllerAdvice("com.monstarlab.api.controller")
class StarterAdminResponseBodyAdvice: ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        return body?.let { body ->
            try {
                (response as ServletServerHttpResponse).servletResponse
                    .let {
                        if (!it.containsHeader(StarterConstant.ADMIN_ROLE)) {
                            null
                        } else {
                            UserRole.valueOf(it.getHeader(StarterConstant.ADMIN_ROLE))
                        }
                    }
                    .let {
                        // TODO: Update for body by role or permission
                        body
                    }
            } catch (e: IllegalArgumentException) {
                body
            }
        }
    }
}