package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dnyanyog.PatientApplicationMain;
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
@ContextConfiguration(classes = PatientApplicationMain.class)
public class PatientControllerJsonAndXmlTest {

  @Autowired private MockMvc mockMvc;

  // JSON test cases
  @Test
  @Order(1)
  public void verifyDirectoryOperationForPatientSuccess() throws Exception {

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/patient/add")
            .content(
                "{\n"
                    + "  \"patientNameEnglish\": \"ramesh\",\n"
                    + "  \"patientNameMarathi\": \"फ\",\n"
                    + "  \"birthDate\": \"2006-04-02\",\n"
                    + "  \"gender\": \"female\",\n"
                    + "  \"mobile\": \"9876543241\",\n"
                    + "  \"firstExaminationDate\": \"2024-05-07\",\n"
                    + "  \"address\": \"pune\"\n"
                    + "}")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Patient added successfully!"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void verifyPatientServiceForUpdatePatient() throws Exception {
    String patientId = "PATE67869D3";

    String requestBody =
        "{\n"
            + "  \"patientNameEnglish\": \"gauri\",\n"
            + "  \"patientNameMarathi\": \"फ\",\n"
            + "  \"birthDate\": \"2006-04-01\",\n"
            + "  \"gender\": \"male\",\n"
            + "  \"mobile\": \"9876543241\",\n"
            + "  \"firstExaminationDate\": \"2024-05-01\",\n"
            + "  \"address\": \"mumbai\"\n"
            + "}";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.put("/api/v1/patient/" + patientId)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("gauri"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameMarathi").value("फ"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate").value("2006-04-01"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("male"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.mobile").value("9876543241"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstExaminationDate").value("2024-05-01"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("mumbai"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Patient updated successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andReturn();
  }

  @Test
  @Order(3)
  public void verifyPatientServiceForUpdatePatientNotFound() throws Exception {
    String patientId = "non_exist";

    String requestBody =
        "{\n"
            + "  \"patientNameEnglish\": \"gauri\",\n"
            + "  \"patientNameMarathi\": \"फ\",\n"
            + "  \"birthDate\": \"2006-04-01\",\n"
            + "  \"gender\": \"male\",\n"
            + "  \"mobile\": \"9876543241\",\n"
            + "  \"firstExaminationDate\": \"2024-05-01\",\n"
            + "  \"address\": \"mumbai\"\n"
            + "}";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.put("/api/v1/patient/" + patientId)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Fail"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value("Patient not found or invalid request!"))
        .andReturn();
  }

  @Test
  @Order(4)
  public void verifyPatientServiceForSearchPatientSuccess() throws Exception {
    String patientId = "PATE67869D3";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/patient/" + patientId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Patient found successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("ramesh"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameMarathi").value("फ"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate").value("2006-04-01"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("male"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.mobile").value("9876543241"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstExaminationDate").value("2024-05-01"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("mumbai"))
        .andReturn();
  }

  @Test
  @Order(5)
  public void verifyDirectoryServiceForGetSingleUserNotFound() throws Exception {
    String patientId = "nonexistentUser";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/patient/" + patientId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Fail"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value("Patient not found or invalid request!"))
        .andReturn();
  }

  @Test
  @Order(6)
  public void verifyPatientServiceForDeletePatientSuccess() throws Exception {
    String patientId = "PATFC237B58";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/patient/" + patientId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Patient deleted successfully!"))
        .andReturn();
  }

  @Test
  @Order(7)
  public void verifyPatientServiceForDeletePatientNotFound() throws Exception {
    String patientId = "nonexistentUser";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/patient/" + patientId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Fail"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value("Patient not found or invalid request!"))
        .andReturn();
  }

  // XML test cases
  @Test
  @Order(8)
  public void verifyPatientOperationForPatientSuccessUsingXml() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/patient/add")
            .content(
                "<request>\r\n"
                    + "  <patientNameEnglish>ravina</patientNameEnglish>\r\n"
                    + "  <patientNameMarathi>रविण</patientNameMarathi>\r\n"
                    + "  <birthDate>2003-04-01</birthDate>\r\n"
                    + "  <gender>Female</gender>\r\n"
                    + "  <mobile>1234567898</mobile>\r\n"
                    + "  <address>kopargaon</address>\r\n"
                    + "  <firstExaminationDate>2024-04-01</firstExaminationDate>\r\n"
                    + "</request>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Patient added successfully!"))
        .andReturn();
  }

  @Test
  @Order(9)
  public void verifyPatientServiceForUpdateUsingXml() throws Exception {
    String patientId = "PAT07DF4F52";

    String requestBody =
        "<request>\r\n"
            + "  <patientNameEnglish>nidhi</patientNameEnglish>\r\n"
            + "  <patientNameMarathi>रविण</patientNameMarathi>\r\n"
            + "  <birthDate>2003-04-02</birthDate>\r\n"
            + "  <gender>Male</gender>\r\n"
            + "  <mobile>1234567899</mobile>\r\n"
            + "  <address>pune</address>\r\n"
            + "  <firstExaminationDate>2024-04-02</firstExaminationDate>\r\n"
            + "</request>";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.put("/api/v1/patient/" + patientId)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("nidhi"))
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameMarathi").string("रविण"))
        .andExpect(MockMvcResultMatchers.xpath("/response/birthDate").string("2003-04-02"))
        .andExpect(MockMvcResultMatchers.xpath("/response/gender").string("Male"))
        .andExpect(MockMvcResultMatchers.xpath("/response/mobile").string("1234567899"))
        .andExpect(MockMvcResultMatchers.xpath("/response/address").string("pune"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/firstExaminationDate").string("2024-04-02"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string("Patient updated successfully!"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andReturn();
  }

  @Test
  @Order(10)
  public void verifyPatientServiceForUpdateNotFoundUsingXml() throws Exception {
    String patientId = "non_exist";

    String requestBody =
        "<request>\r\n"
            + "  <patientNameEnglish>nidhi</patientNameEnglish>\r\n"
            + "  <patientNameMarathi>रविण</patientNameMarathi>\r\n"
            + "  <birthDate>2003-04-02</birthDate>\r\n"
            + "  <gender>Male</gender>\r\n"
            + "  <mobile>1234567899</mobile>\r\n"
            + "  <address>pune</address>\r\n"
            + "  <firstExaminationDate>2024-04-02</firstExaminationDate>\r\n"
            + "</request>";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.put("/api/v1/patient/" + patientId)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Fail"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string("Patient not found or invalid request!"))
        .andReturn();
  }

  @Test
  @Order(11)
  public void verifyPatientServiceForSearchPatientSuccessXml() throws Exception {
    String patientId = "PAT07DF4F52";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/patient/" + patientId)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("ravina"))
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameMarathi").string("रविण"))
        .andExpect(MockMvcResultMatchers.xpath("/response/birthDate").string("2003-04-02"))
        .andExpect(MockMvcResultMatchers.xpath("/response/gender").string("Male"))
        .andExpect(MockMvcResultMatchers.xpath("/response/mobile").string("1234567899"))
        .andExpect(MockMvcResultMatchers.xpath("/response/address").string("pune"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/firstExaminationDate").string("2024-04-02"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Patient found successfully!"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andReturn();
  }

  @Test
  @Order(12)
  public void verifyPatientServiceForSearchPatientSuccessUsingPatientNameXml() throws Exception {
    String patientNameEnglish = "ravina";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/patient/name/" + patientNameEnglish)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("ravina"))
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameMarathi").string("रविण"))
        .andExpect(MockMvcResultMatchers.xpath("/response/birthDate").string("2003-04-02"))
        .andExpect(MockMvcResultMatchers.xpath("/response/gender").string("Male"))
        .andExpect(MockMvcResultMatchers.xpath("/response/mobile").string("1234567899"))
        .andExpect(MockMvcResultMatchers.xpath("/response/address").string("pune"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/firstExaminationDate").string("2024-04-02"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Patient found successfully!"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andReturn();
  }

  @Test
  @Order(13)
  public void verifyPatientServiceForDeletePatientSuccessXml() throws Exception {
    String patientId = "PAT07DF4F52";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/patient/" + patientId)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string("Patient deleted successfully!"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andReturn();
  }
}
