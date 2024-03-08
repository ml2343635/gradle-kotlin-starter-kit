package com.monstarlab.api.configuration

import com.monstarlab.service.configuration.StarterServiceConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
// TODO: Replace to your package name
@ComponentScan("com.monstarlab.api.*")
@Import(StarterServiceConfiguration::class)
class StarterApiConfiguration