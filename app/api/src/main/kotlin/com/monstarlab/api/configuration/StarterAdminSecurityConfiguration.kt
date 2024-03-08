package com.monstarlab.api.configuration

import com.monstarlab.api.security.StarterApiAuthenticationProvider
import com.monstarlab.common.constant.StarterConstant
import com.monstarlab.api.security.StarterApiAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class StarterAdminSecurityConfiguration(
    private val starterApiAuthenticationProvider: StarterApiAuthenticationProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val environment: Environment,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        with(http) {
            cors().configurationSource(corsConfigurationSource())
            csrf().disable()
            addFilterBefore(
                StarterApiAuthenticationFilter(authenticationManagerBuilder.orBuild),
                UsernamePasswordAuthenticationFilter::class.java,
            )
            sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            exceptionHandling().authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            authorizeHttpRequests()
                .requestMatchers("/actuator/health").permitAll()
                .apply {
                    if (environment.acceptsProfiles(StarterConstant.TEST_PROFILES)) {
                        requestMatchers("/swagger/**").permitAll()
                    } else {
                        requestMatchers("/swagger/**").denyAll()
                    }
                }
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/**").authenticated()
                .anyRequest().denyAll()
        }
        return http.build()
    }

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.authenticationProvider(starterApiAuthenticationProvider)
        return authenticationManagerBuilder.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("OPTIONS", "GET", "PUT", "POST", "PATCH", "DELETE")
        configuration.allowedHeaders = listOf("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}