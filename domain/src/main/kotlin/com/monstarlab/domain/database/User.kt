package com.monstarlab.domain.database

import com.monstarlab.domain.enum.UserRole
import jakarta.persistence.*

@Entity
@Table(name = "ml_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val createAt: Long,

    @Column(nullable = false)
    val updateAt: Long,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val credential: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: UserRole,
)
