package com.maigrand.calculatebill.controller;

import com.maigrand.calculatebill.payload.BillDetails;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static com.maigrand.calculatebill.TestOrder.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestInstance(PER_CLASS)
@DirtiesContext(classMode = AFTER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class BillControllerTest extends SecurityControllerTest {

    protected String id;

    @Test
    @Commit
    @Order(CREATE)
    public void testCreate() throws Exception {
        BillDetails details = new BillDetails();
        details.setName("bill1");
        details.setTips(10);

        testCreate("/api/v1/bill", details)
                .andDo(result -> this.id = evaluateJsonPath(result, "$.id"))
                .andExpect(jsonPath("$.id", greaterThan(0)));
    }
}
