package com.monstarlab.common.exception

import org.slf4j.helpers.MessageFormatter

open class StarterExternalException(
    open val logMessage: StarterErrorMessage,
    vararg messageArgs: Any,
    override val cause: Throwable? = null
) : RuntimeException(
    MessageFormatter.arrayFormat(logMessage.message, messageArgs).message,
    cause
)

class StarterNoRollbackExternalException(
    override val logMessage: StarterErrorMessage,
    vararg messageArgs: Any,
    override val cause: Throwable? = null
) : StarterExternalException(
    logMessage = logMessage,
    messageArgs = messageArgs,
    cause = cause
)