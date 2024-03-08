package com.monstarlab.api.configuration

import com.monstarlab.api.security.StarterApiPermissionInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
class StarterAdminWebMvcConfigurer(
    private val adminPermissionInterceptor: StarterApiPermissionInterceptor,
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry
            .addInterceptor(adminPermissionInterceptor)
            .addPathPatterns("/v1/**")
    }
}