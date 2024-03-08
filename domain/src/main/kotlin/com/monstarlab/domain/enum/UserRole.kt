package com.monstarlab.domain.enum

enum class UserRole {
    SUPER_ADMIN,
    UNKNOWN,
    ;

    val permissions: List<UserPermission>
        get() = when (this) {
            SUPER_ADMIN -> listOf(
                UserPermission.LOGIN,
            )

            UNKNOWN -> emptyList()
        }

}