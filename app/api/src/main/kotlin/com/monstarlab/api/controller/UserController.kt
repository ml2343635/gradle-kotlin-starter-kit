package com.monstarlab.api.controller

import com.monstarlab.api.request.UserLoginRequest
import com.monstarlab.api.response.UserLoginResponse
import com.monstarlab.service.admin.UserService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/public/v1/user/login")
    @Operation(summary = "User login")
    fun login(
        @RequestBody @Valid request: UserLoginRequest,
    ): UserLoginResponse {
        val token = userService.login(
            email = request.email,
            password = request.password,
        )
        return UserLoginResponse(token)
    }
}