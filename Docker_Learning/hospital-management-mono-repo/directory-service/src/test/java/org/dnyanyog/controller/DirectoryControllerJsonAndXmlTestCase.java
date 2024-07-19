package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dnyanyog.DirectoryServiceMain;
import org.dnyanyog.common.ResponseCode;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = DirectoryServiceMain.class)
public class DirectoryControllerJsonAndXmlTestCase {

  @Autowired MockMvc mockMvc;

  // JSON test cases

  @Test
  @Order(1)
  public void verifyDirectoryOperationForDirectorySuccess() throws Exception {

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/directory/add")
            .content(
                "{\r\n"
                    + "  \"confirm_password\": \"5678912\",\r\n"
                    + "  \"password\": \"5678912\",\r\n"
                    + "  \"email\": \"sdga123@gmail.com\",\r\n"
                    + "  \"userName\": \"hari\",\r\n"
                    + "  \"role\": \"user\",\r\n"
                    + "  \"mobileNumber\": \"1234567898\"\r\n"
                    + "}")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.status").value(ResponseCode.USER_ADDED.getStatus()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value(ResponseCode.USER_ADDED.getMessage()))
        .andReturn();
  }

  @Test
  @Order(2)
  public void verifyDirectoryOperationForUpdate() throws Exception {

    String user_id = "USR571BCB";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/directory/" + user_id)
            .content(
                "{\r\n"
                    + "  \"confirm_password\": \"1234556\",\r\n"
                    + "  \"password\": \"1234556\",\r\n"
                    + "  \"email\": \"hari23@gmail.com\",\r\n"
                    + "  \"userName\": \"hari\",\r\n"
                    + "  \"role\": \"user\",\r\n"
                    + "  \"mobileNumber\": \"9898989876\"\r\n"
                    + "}")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.status").value(ResponseCode.USER_UPDATED.getStatus()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value(ResponseCode.USER_UPDATED.getMessage()))
        .andReturn();
  }

  @Test
  @Order(3)
  public void verifyDirectoryOperationForSearch() throws Exception {

    String user_id = "USR571BCB";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/directory/" + user_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.status").value(ResponseCode.SEARCH_USER.getStatus()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value(ResponseCode.SEARCH_USER.getMessage()))
        .andReturn();
  }

  @Test
  @Order(4)
  public void verifyDirectoryOperationForDelete() throws Exception {

    String user_id = "USR571BCB";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/directory/" + user_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.status").value(ResponseCode.DELETE_USER.getStatus()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value(ResponseCode.DELETE_USER.getMessage()))
        .andReturn();
  }

  // XML test cases

  @Test
  @Order(5)
  public void verifyDirectoryServiceforAddUser() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/directory/add")
            .content(
                "<request>\r\n"
                    + "  <confirm_password>2222222</confirm_password>\r\n"
                    + "  <password>2222222</password>\r\n"
                    + "  <email>sai123@gmail.com</email>\r\n"
                    + "  <userName>sayli</userName>\r\n"
                    + "  <role>user</role>\r\n"
                    + "  <mobileNumber>1234567889</mobileNumber>\r\n"
                    + "</request>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.xpath("/response/status")
                .string(ResponseCode.USER_ADDED.getStatus()))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string(ResponseCode.USER_ADDED.getMessage()))
        .andReturn();
  }

  @Test
  @Order(6)
  public void verifyDirectoryServiceforUpdateUser() throws Exception {
    String user_id = "USRF28C02";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/directory/" + user_id)
            .content(
                "<request>\r\n"
                    + "  <confirm_password>1111111</confirm_password>\r\n"
                    + "  <password>1111111</password>\r\n"
                    + "  <email>sai123@gmail.com</email>\r\n"
                    + "  <userName>sayli</userName>\r\n"
                    + "  <role>user</role>\r\n"
                    + "  <mobileNumber>1234567889</mobileNumber>\r\n"
                    + "</request>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.xpath("/response/status")
                .string(ResponseCode.USER_UPDATED.getStatus()))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string(ResponseCode.USER_UPDATED.getMessage()))
        .andReturn();
  }

  @Test
  @Order(7)
  public void verifyDirectoryServiceforSearchUser() throws Exception {
    String user_id = "USRF28C02";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/directory/" + user_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.xpath("/response/status")
                .string(ResponseCode.SEARCH_USER.getStatus()))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string(ResponseCode.SEARCH_USER.getMessage()))
        .andReturn();
  }

  @Test
  @Order(8)
  public void verifyDirectoryServiceforDeleteUser() throws Exception {
    String user_id = "USRF28C02";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/directory/" + user_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.xpath("/response/status")
                .string(ResponseCode.DELETE_USER.getStatus()))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string(ResponseCode.DELETE_USER.getMessage()))
        .andReturn();
  }
}
