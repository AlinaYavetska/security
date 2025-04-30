package edu.yavetska.security;

/*
@author   Admin
@project   security
@class  AccessTests
@version  1.0.0
@since 30.04.2025 - 18.53
*/


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AccessTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithAnonymousUser
    void anonymousAccessToUser_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void userAccessToUser_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void adminAccessToUser_ShouldReturnForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/user"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void adminAccessToAdmin_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void userAccessToAdmin_ShouldReturnForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"SUPERADMIN"})
    void superadminAccessToSuperadmin_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/superadmin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void adminAccessToSuperadmin_ShouldReturnForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/superadmin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void userAccessToUnknown_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/unknown"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"SUPERADMIN"})
    void superadminAccessToUnknown_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/unknown"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"EDITOR"})
    void editorAccessToEditor_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/editor"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"MODERATOR"})
    void moderatorAccessToModerator_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/moderator"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"MODERATOR"})
    void moderatorAccessToCommon_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/common"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"EDITOR"})
    void editorAccessToCommon_ShouldReturnForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/common"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void adminAccessToCommon_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/common"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"SUPERADMIN"})
    void superadminAccessToAdmin_ShouldReturnForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void adminAccessToUnknown_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/unknown"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void userAccessToStranger_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/stranger"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void anonymousAccessToUnknown_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/unknown"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void userAccessToSuperadmin_ShouldReturnForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/superadmin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "USER"})
    void multiRoleAccessToUser_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/hello/user"))
                .andExpect(status().isOk());
    }

}
