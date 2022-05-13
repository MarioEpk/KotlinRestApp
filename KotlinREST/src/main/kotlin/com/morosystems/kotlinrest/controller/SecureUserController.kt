package com.morosystems.kotlinrest.controller

import com.morosystems.kotlinrest.service.UserService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/secure/users")
class SecureUserController(private val userService: UserService) {

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteUser(@PathVariable @Valid id: Int) {
        log.info("Called endpoint DELETE secure/users/${id}")
        userService.delete(id)
    }
}