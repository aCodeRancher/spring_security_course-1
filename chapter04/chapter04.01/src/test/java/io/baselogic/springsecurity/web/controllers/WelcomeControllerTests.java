package io.baselogic.springsecurity.web.controllers;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
class WelcomeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    // HtmlUnit uses the Rhino Engine
    private WebClient webClient;

    /**
     * Customize the WebClient to work with HtmlUnit
     *
     * @param context WebApplicationContext
     */
    @BeforeEach
    void beforeEachTest(WebApplicationContext context) {

        // https://docs.spring.io/spring-security/site/docs/current/reference/html5/#test-mockmvc-setup
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(context, springSecurity())
                .build();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(true);
    }


    //-----------------------------------------------------------------------//

    @Test
    @DisplayName("Mock Mvc Welcome Home Page")
    void testHomePage() throws Exception {
        MvcResult result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).contains("Welcome to the EventManager!")
                .contains("Chapter 04.01");

    }

    @Test
    @DisplayName("HTML Unit Welcome Home Page")
    void testHomePage_htmlUnit() throws Exception {

        HtmlPage welcomePage = webClient.getPage("http://localhost/");

        String id = welcomePage.getTitleText();
        assertThat(id).isEqualTo("Welcome to the EventManager!");

        String chapterHeading = welcomePage.getHtmlElementById("chapterHeading").getTextContent();
        assertThat(chapterHeading).contains("Chapter 04.01");

        String chapterTitle = welcomePage.getHtmlElementById("chapterTitle").getTextContent();
        assertThat(chapterTitle).contains("Each chapter will have a slightly different summary depending on what has been done.");

        String summary = welcomePage.getHtmlElementById("summary").getTextContent();
        assertThat(summary).contains("Configuring Jdbc Support");
    }

    //-----------------------------------------------------------------------//


} // The End...
