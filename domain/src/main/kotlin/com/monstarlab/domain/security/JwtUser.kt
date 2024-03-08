package com.monstarlab.domain.security

import com.monstarlab.domain.enum.UserPermission
import com.monstarlab.domain.enum.UserRole

data class JwtUser(
    val userId: Long,
    val role: UserRole,
) {
    fun hasPermission(permission: UserPermission): Boolean {
        return role.permissions.contains(permission)
    }
}