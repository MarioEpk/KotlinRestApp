package com.morosystems.kotlinrest.dto

data class UserDto(
    var id: Int,
    var name: String?,
    val username: String,
)