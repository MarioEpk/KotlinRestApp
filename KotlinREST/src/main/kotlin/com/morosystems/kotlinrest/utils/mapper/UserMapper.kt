package com.morosystems.kotlinrest.utils.mapper

import com.morosystems.kotlinrest.dto.CreateUserDto
import com.morosystems.kotlinrest.dto.UpdateUserDto
import com.morosystems.kotlinrest.dto.UserDto
import com.morosystems.kotlinrest.entity.UserEntity

fun CreateUserDto.toUserEntity(): UserEntity = UserEntity(
    name = name,
    username = username
)

fun UserEntity.toUserDto(): UserDto = UserDto(
    id = id,
    name = name,
    username = username
)