package com.monstarlab.common.exception

import org.slf4j.helpers.MessageFormatter

class StarterInternalException(
    val logMessage: StarterErrorMessage,
    vararg messageArgs: Any?,
    override val cause: Throwable? = null
) : RuntimeException(
    MessageFormatter.arrayFormat(logMessage.message, messageArgs).message,
    cause
)