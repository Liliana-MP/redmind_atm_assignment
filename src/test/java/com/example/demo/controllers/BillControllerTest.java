package com.example.demo.controllers;

import com.example.demo.models.Bill;
import com.example.demo.repositories.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class BillControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BillRepository mockRepo;

    @BeforeEach
    public void init(){
        Bill b1 = new Bill(100, 1L);
        Bill b2 = new Bill(100, 2L);
        Bill b3 = new Bill(100, 3L);
        Bill b4 = new Bill(100, 4L);
        Bill b5 = new Bill(100, 5L);
        Bill b6 = new Bill(500, 6L);
        Bill b7 = new Bill(500, 7L);
        Bill b8 = new Bill(500, 8L);
        Bill b9 = new Bill(1000, 9L);
        Bill b10 = new Bill(1000, 10L);


        when(mockRepo.findByValue(100)).thenReturn(Arrays.asList(b1, b2, b3, b4, b5));
        when(mockRepo.findByValue(500)).thenReturn(Arrays.asList(b6, b7, b8));
        when(mockRepo.findByValue(1000)).thenReturn(Arrays.asList(b9, b10));
        when(mockRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10)));
    }


    @Test
    public void getGreeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/bills/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Redmind - Backend Developer Assignment")));
    }

    @Test
    public void wrongURL() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/adads").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void accessDenied() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/bills/aadads").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllBills() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/bills/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"value\": 100\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 2,\n" +
                        "        \"value\": 100\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 3,\n" +
                        "        \"value\": 100\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 4,\n" +
                        "        \"value\": 100\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 5,\n" +
                        "        \"value\": 100\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 6,\n" +
                        "        \"value\": 500\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 7,\n" +
                        "        \"value\": 500\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 8,\n" +
                        "        \"value\": 500\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 9,\n" +
                        "        \"value\": 1000\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 10,\n" +
                        "        \"value\": 1000\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    public void withdrawOneThousand() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/bills/withdraw/1000").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 9,\n" +
                        "        \"value\": 1000\n" +
                        "    }\n" +
                        "]"));

    }

    @Test
    public void withdrawFiveHundred() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/bills/withdraw/500").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 6,\n" +
                        "    \"value\": 500\n" +
                        "  }\n" +
                        "]"));

    }

    @Test
    public void withdrawOneHundred() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/bills/withdraw/100").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"value\": 100\n" +
                        "  }\n" +
                        "]"));

    }



    @Test
    public void withdrawSevenHundred() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/bills/withdraw/700").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 6,\n" +
                        "    \"value\": 500\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"value\": 100\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"value\": 100\n" +
                        "  }\n" +
                        "]"));

    }

    @Test
    public void withdrawMoreThanFourThousand() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/bills/withdraw/5000").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Not enough bills")));

    }

    @Test
    public void withdrawNegativeAmount() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/bills/withdraw/-100").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("Not enough bills")));

    }


}