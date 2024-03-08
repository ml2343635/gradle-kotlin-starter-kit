package com.monstarlab.service.component

import com.monstarlab.common.exception.StarterErrorMessage
import com.monstarlab.common.exception.StarterExternalException
import com.monstarlab.domain.database.User
import com.monstarlab.storage.mysql.dao.UserDao
import org.springframework.stereotype.Component

@Component
class UserComponent(
    private val userDao: UserDao,
) {
    fun getByEmail(
        email: String,
    ): User {
        return userDao.getByEmail(email)
            ?: throw StarterExternalException(StarterErrorMessage.UserEmailNotFound, email)
    }
}