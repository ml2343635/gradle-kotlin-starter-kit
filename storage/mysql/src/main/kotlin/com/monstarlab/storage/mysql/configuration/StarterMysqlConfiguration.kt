package com.monstarlab.storage.mysql.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
// TODO: Replaced to your package name
@EntityScan("com.monstarlab.domain.database")
// TODO: Replaced to your package name
@EnableJpaRepositories("com.monstarlab.storage.mysql.dao")
class StarterMysqlConfiguration