package com.morosystems.kotlinrest.repository

import com.morosystems.kotlinrest.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Int> {

    fun findByUsername(name: String?): UserEntity
}