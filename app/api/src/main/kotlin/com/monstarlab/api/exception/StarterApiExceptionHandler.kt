package com.monstarlab.api.exception

import com.monstarlab.common.exception.StarterErrorMessage
import com.monstarlab.common.exception.StarterExternalException
import com.monstarlab.common.exception.StarterInternalException
import com.monstarlab.common.exception.StarterNoRollbackExternalException
import org.apache.logging.log4j.Level
import org.slf4j.Logger
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class StarterApiExceptionHandler(
    private val log: Logger,
) : ResponseEntityExceptionHandler() {
    @ExceptionHandler
    fun handleUnknownException(exception: Throwable, request: WebRequest): ResponseEntity<Any> {
        val exceptionResponse = when (exception.javaClass) {
            StarterExternalException::class.java, StarterNoRollbackExternalException::class.java -> {
                (exception as StarterExternalException).let {
                    ErrorMessageResponse(
                        cause = it,
                        statusCode = it.logMessage.httpStatus,
                    )
                }
            }

            StarterInternalException::class.java -> {
                when ((exception as StarterInternalException).logMessage.level) {
                    Level.INFO -> log.info(exception.message, exception.cause)
                    Level.WARN -> log.warn(exception.message, exception.cause)
                    else -> log.error(exception.message, exception.cause)
                }
                ErrorMessageResponse(
                    StarterExternalException(StarterErrorMessage.UnknownError),
                    HttpStatus.INTERNAL_SERVER_ERROR
                )
            }

            else -> {
                handleSystemException(exception)
            }
        }

        return ResponseEntity(
            exceptionResponse,
            HttpStatus.valueOf(exceptionResponse.code)
        )
    }

    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        statusCode: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val exceptionResponse = if (statusCode.is5xxServerError) {
            handleSystemException(ex)
        } else {
            ErrorMessageResponse(
                ex,
                9998,
                statusCode
            )
        }
        return ResponseEntity(exceptionResponse, statusCode)
    }

    private fun handleSystemException(ex: Throwable): ErrorMessageResponse {
        log.error("Unknown exception", ex)
        return ErrorMessageResponse(
            StarterExternalException(
                StarterErrorMessage.UnknownError
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    class ErrorMessageResponse(cause: Exception, errorCode: Int, statusCode: HttpStatusCode) {
        constructor(cause: StarterExternalException, statusCode: HttpStatusCode) : this(
            cause,
            cause.logMessage.statusCode,
            statusCode
        )

        val errorCode = errorCode
        val errorMsg = cause.message
        val timestamp = System.currentTimeMillis()
        val code = statusCode.value()
    }
}