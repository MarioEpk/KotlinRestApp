package com.morosystems.kotlinrest.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(id: Int) : RuntimeException() {
    override var message: String? = "User with provided id [%s] was not found."
        get() = super.message
}
