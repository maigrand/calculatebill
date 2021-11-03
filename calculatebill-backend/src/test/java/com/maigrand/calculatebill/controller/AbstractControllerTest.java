package com.maigrand.calculatebill.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maigrand.calculatebill.entity.TestEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.util.JsonPathExpectationsHelper;
import org.springframework.test.web.servlet.*;

import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.util.AssertionErrors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public abstract class AbstractControllerTest {

    protected static final MediaType MEDIA_TYPE = APPLICATION_JSON;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected TestEntityFactory entityFactory;

    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        return headers;
    }

    private String toJson(Object value) {
        try {
            return mapper.writer().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    protected static String getContent(MvcResult result) {
        try {
            return result.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static JsonPathExpectationsHelper createJsonPathHelper(
            String expression, Object... args) {
        return new JsonPathExpectationsHelper(expression, args);
    }

    public static <T> T evaluateJsonPath(MvcResult result,
            String expression, Object... args) {
        return evaluateJsonPath(getContent(result), expression, args);
    }

    public static <T> T evaluateJsonPath(String content,
            String expression, Object... args) {
        JsonPathExpectationsHelper jsonPathHelper =
                createJsonPathHelper(expression, args);
        return (T) jsonPathHelper.evaluateJsonPath(content);
    }

    protected ResultActions testCreate(String url, Object createDetails, HttpStatus status) throws Exception {
        return this.mockMvc.perform(post(url)
                        .headers(getHeaders())
                        .contentType(MEDIA_TYPE)
                        .content(toJson(createDetails)))
                .andExpect(result -> assertEquals("Status", status.value(), result.getResponse().getStatus()))
                .andExpect(content().contentTypeCompatibleWith(MEDIA_TYPE));
    }

    protected ResultActions testCreate(String url, Object createDetails) throws Exception {
        return testCreate(url, createDetails, HttpStatus.CREATED);
    }
}
