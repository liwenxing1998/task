package com.puhui.restful;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestfulApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    /*MockMvc 实现了对 Http 请求的模拟，能够直接使用网络的形式，转换到 Controller 的调用*/
    private MockMvc mockMvc;

    /*提前加载 MVC 环境*/
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        saveMessages();
    }

    @Test
    public void saveMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("text", "text");
        params.add("summary", "summary");
        /*MockMvcRequestBuilders.post 代表使用 post 请求*/
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/save").
                params(params)).andReturn().getResponse().getContentAsString();
        System.out.println(mvcResult);
    }


    private void saveMessages() {
        for (int i = 0; i < 10; i++) {
            final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("text", "text" + i);
            params.add("summary", "summary" + i);
            /*MockMvcRequestBuilders.post 代表使用 post 请求*/
            try {
                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/save").
                        params(params)).andReturn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void findAll() throws Exception {
        String findAll = mockMvc.perform(MockMvcRequestBuilders.get("/findAll")).
                andReturn().getResponse().getContentAsString();
        System.out.println("Result===" + findAll);
    }

    @Test
    public void findOne() throws Exception {
        String findOne = mockMvc.perform(MockMvcRequestBuilders.get("/message/4")).
                andReturn().getResponse().getContentAsString();
        System.out.println(findOne);
    }

    @Test
    public void modifyMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "6");
        params.add("text", "text");
        params.add("summary", "summary");
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.put("/update").params(params)).
                andReturn().getResponse().getContentAsString();
        System.out.printf("Result===" + contentAsString);
    }

    @Test
    public void modiyText() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "6");
        params.add("text", "text");
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.patch("/update/text").params(params)).
                andReturn().getResponse().getContentAsString();
        System.out.println("result===" + contentAsString);
    }

    @Test
    public void deleteMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/3"))
                .andReturn();
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/findAll"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

}
