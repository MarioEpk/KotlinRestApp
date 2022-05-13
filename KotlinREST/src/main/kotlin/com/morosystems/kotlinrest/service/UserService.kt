package com.morosystems.kotlinrest.service

import com.morosystems.kotlinrest.dto.CreateUserDto
import com.morosystems.kotlinrest.dto.UpdateUserDto
import com.morosystems.kotlinrest.dto.UserDto
import com.morosystems.kotlinrest.entity.UserEntity
import com.morosystems.kotlinrest.exception.UserNotFoundException
import com.morosystems.kotlinrest.repository.UserRepository
import com.morosystems.kotlinrest.utils.mapper.toUserDto
import com.morosystems.kotlinrest.utils.mapper.toUserEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    fun getById(id: Int): UserDto {
        val userDto = userRepository.findById(id)
            .orElseThrow { UserNotFoundException(id) }
        return userDto.toUserDto()
    }

    fun getAll(): List<UserDto> =
        userRepository.findAll().map { it.toUserDto() }

    fun create(createUserDto: CreateUserDto): UserEntity =
        userRepository.save(createUserDto.toUserEntity())

    fun update(id: Int, updateUserDto: UpdateUserDto) {
        val userEntity = userRepository.findById(id)
            .orElseThrow { UserNotFoundException(id) }
        userEntity.name = updateUserDto.name
        userRepository.save(userEntity)
    }

    fun delete(id: Int) {
        userRepository.findById(id)
            .orElseThrow { UserNotFoundException(id) }
        userRepository.deleteById(id)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = userRepository.findByUsername(username)

        return (
                User.withUsername(userEntity.username)
                    .password(userEntity.password)
                    .authorities("USER").build())
    }
}