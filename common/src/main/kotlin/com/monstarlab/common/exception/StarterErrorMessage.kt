package com.monstarlab.common.exception

import org.apache.logging.log4j.Level
import org.springframework.http.HttpStatus

interface LogMessage {
    val message: String
}

enum class StarterErrorMessage(
    val statusCode: Int,
    val level: Level,
    override val message: String,
    val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
): LogMessage {
    UnknownError(-9999, Level.ERROR,"Unknown error: {}"),
    FileCreateError(-9998, Level.ERROR, "Error to create file."),
    CommandExecutionError(-9997, Level.ERROR, "Error to execute command: {}"),
    OverRetryMaxCountError(-9996, Level.ERROR, "Retry too times for {}"),

    UserNotPermitted(1001, Level.WARN, "User {} with role {} does not have permission {}", HttpStatus.FORBIDDEN),
    UserNotFound(1002, Level.WARN, "User {} not found", HttpStatus.NOT_FOUND),
    UserEmailNotFound(1003, Level.INFO, "User email {} not found."),
    UserPasswordWrong(1004, Level.INFO, "The password {} is wrong for user with email {}."),
    UserEmailExist(1005, Level.INFO, "User email {} is existed, please use other email."),
    ;
}