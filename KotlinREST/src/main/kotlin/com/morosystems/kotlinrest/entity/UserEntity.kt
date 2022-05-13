package com.morosystems.kotlinrest.entity

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "users", schema = "public", catalog = "RESTApp")
data class UserEntity(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    var id: Int = 0,

    @Basic
    @Column(name = "name", nullable = true)
    var name: String? = null,

    @Size(min = 2, message = "username field should contain at least 2 characters.")
    @Basic
    @Column(name = "username", nullable = false, unique = true)
    var username: String = "",

    @Basic
    @Column(name = "password", nullable = true)
    var password: String? = null
)


