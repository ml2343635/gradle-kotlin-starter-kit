package com.monstarlab.common.constant

import org.springframework.core.env.Profiles

object StarterConstant {
    const val ADMIN_ROLE = "Role"
    val TEST_PROFILES = Profiles.of("default", "beta", "alpha")
}