package com.morosystems.kotlinrest.controller

import com.morosystems.kotlinrest.dto.CreateUserDto
import com.morosystems.kotlinrest.dto.UserDto
import com.morosystems.kotlinrest.entity.UserEntity
import com.morosystems.kotlinrest.service.UserService
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
internal class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun testGetUserById() {
        val uri = "/users/{id}"
        val user = UserDto(1, "testUser1", "testUsername1")
        Mockito.`when`(userService.getById(1)).thenReturn(user)
        mockMvc.perform(
            MockMvcRequestBuilders.get(uri, user.id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun testGetAllUsers() {
        val uri = "/users"
        val user1 = UserDto(1, "user1", "username1")
        val user2 = UserDto(2, "user2", "username2")
        user2.name = "user2"
        Mockito.`when`(userService.getAll()).thenReturn(listOf(user1, user2))
        mockMvc.perform(
            MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize<UserDto>(2)))
    }

    @Test
    fun testAddUser() {
        val uri = "/users"
        val userDto = CreateUserDto(
            name = "user",
            username = "username"
        )
        val userEntity = UserEntity(name = "user")
        Mockito.`when`(userService.create(userDto)).thenReturn(userEntity)
        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                            "    \"name\": \"testUser\",\n" +
                            "    \"username\": \"TestUserName\"\n" +
                            "}")
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun testUpdateUser() {
        val uri = "/users/{id}"
        val userEntity = UserEntity(id = 1, name = "user")
        mockMvc.perform(
            MockMvcRequestBuilders.put(uri, userEntity.id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                            "    \"name\": \"testUser\",\n" +
                            "    \"username\": \"TestUserName\"\n" +
                            "}")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}