package com.monstarlab.storage.mysql.dao

import com.monstarlab.domain.database.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao: JpaRepository<User, Long> {
    fun getByEmail(email: String): User?
}