package com.monstarlab.api.annotation

import com.monstarlab.domain.enum.UserPermission

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Permitted(
    vararg val permissions: UserPermission,
)
