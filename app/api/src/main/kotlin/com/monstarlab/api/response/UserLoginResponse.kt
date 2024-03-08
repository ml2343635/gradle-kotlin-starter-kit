package com.monstarlab.api.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "User login response model")
data class UserLoginResponse(
    @Schema(
        description = "JWT access token",
        example = "sfs314312431",
    )
    val token: String,
)