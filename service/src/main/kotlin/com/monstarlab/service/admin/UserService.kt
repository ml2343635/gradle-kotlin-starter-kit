package com.monstarlab.service.admin

import com.monstarlab.common.exception.StarterErrorMessage
import com.monstarlab.common.exception.StarterNoRollbackExternalException
import com.monstarlab.service.component.JwtComponent
import com.monstarlab.service.component.UserComponent
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userComponent: UserComponent,
    private val jwtComponent: JwtComponent,
    private val passwordEncoder: PasswordEncoder,
) {
    fun login(
        email: String,
        password: String,
    ): String {
        val user = userComponent.getByEmail(email)
        if (!passwordEncoder.matches(password, user.credential)) {
            throw StarterNoRollbackExternalException(StarterErrorMessage.UserPasswordWrong, password, email)
        }
        return jwtComponent.generateUserToken(user)
    }
}