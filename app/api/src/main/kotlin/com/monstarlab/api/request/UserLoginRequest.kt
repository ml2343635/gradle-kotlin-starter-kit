package com.monstarlab.api.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "User login request model")
data class UserLoginRequest(
    @Schema(
        description = "Main email address as login ID of the user",
        example = "example@example.com",
    )
    val email: String,

    @Schema(
        description = "Password of the user",
        example = "asdfghjkl",
    )
    val password: String,
)