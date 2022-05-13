package com.morosystems.kotlinrest.controller

import com.morosystems.kotlinrest.entity.UserEntity
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest
internal class SecureUserControllerTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @WithMockUser(username = "Epack", password = "1234")
    fun testDeleteUser() {
        val uri = "/secure/users/{id}"
        val user = UserEntity()
        user.name = "user1"
        user.id = 11
        mockMvc.perform(
            MockMvcRequestBuilders.delete(uri, user.id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}