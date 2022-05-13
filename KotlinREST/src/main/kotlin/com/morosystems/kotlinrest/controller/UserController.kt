package com.morosystems.kotlinrest.controller

import com.morosystems.kotlinrest.dto.CreateUserDto
import com.morosystems.kotlinrest.dto.UpdateUserDto
import com.morosystems.kotlinrest.dto.UserDto
import com.morosystems.kotlinrest.service.UserService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Int): UserDto {
        log.info("Called endpoint GET /users/${id}")

        return userService.getById(id)
    }

    @GetMapping
    fun getAllUsers(): List<UserDto> {
        log.info("Called endpoint GET /users")

        return userService.getAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody @Valid createUserDto: CreateUserDto) {
        log.info("Called endpoint POST /users")

        userService.create(createUserDto)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateUser(@RequestBody @Valid updateUserDto: UpdateUserDto, @PathVariable id: Int) {
        log.info("Called endpoint PUT /users/${id}")

        return userService.update(id, updateUserDto)
    }
}
