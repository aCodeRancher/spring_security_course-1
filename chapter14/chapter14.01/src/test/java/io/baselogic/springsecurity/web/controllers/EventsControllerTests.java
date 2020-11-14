package io.baselogic.springsecurity.web.controllers;


import io.baselogic.springsecurity.annotations.WithMockEventUserDetailsUser1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * RegistrationControllerTests
 *
 * @since chapter03.00 Created
 * @since chapter14.01 Refactored for WebFlux
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class EventsControllerTests {

    private WebTestClient client;

    /**
     * Customize the WebClient
     *
     * @param context WebApplicationContext
     */
    @BeforeEach
    void beforeEachTest(ApplicationContext context) {
        this.client = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .build();
    }


    //-----------------------------------------------------------------------//
    // All Events
    //-----------------------------------------------------------------------//

    /**
     * Test the URI for All Events.
     * In this test, BASIC Authentication is activated through
     * auto configuration so there is now a 401 Unauthorized redirect.
     */
    @Test
    @DisplayName("All Events: UnAuthorized - WithAnonymousUser - RequestPostProcessor")
    void allEvents_not_authenticated__WithAnonymousUser() throws Exception {

        /*MvcResult result = mockMvc.perform(get("/events/")
        )
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "http://localhost/login/form"))

                // The login page should be displayed
                .andReturn();*/
    }


    /**
     * Test the URI for All Events.
     */
    @Test
    @DisplayName("MockMvc All Events - user1")
    @WithMockEventUserDetailsUser1
    void allEvents_not_authenticated__WithUser1() throws Exception {
        /*MvcResult result = mockMvc.perform(get("/events/"))

                .andExpect(status().isForbidden())
                .andReturn();*/

    }


    /**
     * Test the URI for All Events.
     */
//    @Test
//    @DisplayName("MockMvc All Events - user1 - ROLE_USER")
//    @WithMockUser(username="user1@baselogic.com", roles={"USER"})
//    void allEvents_not_authenticated__WithUser1_and_roles() throws Exception {
//        MvcResult result = mockMvc.perform(get("/events/"))
//                .andExpect(status().isForbidden())
//                .andReturn();
//
//    }


    /**
     * Test the URI for All Events.
     * Using @WithMockUser("admin1@baselogic.com") to ensure this user does not have ADMIN role
     */
//    @Test
//    @DisplayName("MockMvc All Events - admin1")
//    @WithMockUser("admin1@baselogic.com")
//    void allEventsPage() throws Exception {
//        MvcResult result = mockMvc.perform(get("/events/"))
//                .andExpect(status().isForbidden())
//                .andReturn();
//
//    }


    /**
     * Test the URI for All Events.
     */
//    @Test
//    @DisplayName("MockMvc All Events - admin1 - ROLE_ADMIN")
//    @WithMockEventUserDetailsAdmin1
//    void allEventsPage__WithUser1_roles() throws Exception {
//        MvcResult result = mockMvc.perform(get("/events/"))
//
//                .andExpect(status().isOk())
//
//                .andExpect(content().string(containsString("All Event")))
//                .andExpect(model().attribute("events", hasSize(greaterThanOrEqualTo(3))))
//
//                .andReturn();
//    }


    //-----------------------------------------------------------------------//
    // All User Events
    //-----------------------------------------------------------------------//

    /**
     * Test the URI for Current User Events with MockMvc.
     */
//    @Test
//    @DisplayName("All Events: UnAuthorized - WithNoUser - RequestPostProcessor")
//    void testCurrentUsersEventsPage() throws Exception {
//
//        MvcResult result = mockMvc.perform(get("/events/my"))
//                .andExpect(status().isFound())
//                .andExpect(header().string("Location", "http://localhost/login/form"))
//                .andReturn();
//
//    }


    /**
     * Test the URI for Current User Events with HtmlUnit.
     */
//    @Test
//    @DisplayName("Current Users Events")
//    @WithMockEventUserDetailsUser1
//    void testCurrentUsersEventsPage_htmlUnit() throws Exception {
//
//        MvcResult result = mockMvc.perform(get("/events/my"))
//
//                .andExpect(status().isOk())
//
//                .andExpect(content().string(containsString("Current User Events")))
//                .andExpect(content().string(containsString("This shows all events for the current appUser.")))
//                .andExpect(model().attribute("events", hasSize(greaterThanOrEqualTo(2))))
//                .andReturn();
//    }

    //-----------------------------------------------------------------------//
    // Events Details
    //-----------------------------------------------------------------------//

    /**
     * Test the URI for showing Event details with MockMvc.
     */
//    @Test
//    @DisplayName("Show Event Details - user1")
//    @WithMockUser("user1@baselogic.com")
//    @WithMockEventUserDetailsUser1
//    void testShowEvent_user1() throws Exception {
//
//        MvcResult result = mockMvc.perform(get("/events/100"))
//
//                .andExpect(status().isOk())
//
//                .andExpect(content().string(containsString("Birthday Party")))
//                .andExpect(content().string(containsString("Time to have my yearly party!")))
//
//                .andReturn();
//    }


    //-----------------------------------------------------------------------//
    // Event Form
    //-----------------------------------------------------------------------//

    /**
     * Test the URI for creating a new Event with MockMvc.
     */
//    @Test
//    @DisplayName("Show Event Form - WithUser")
//    @WithMockEventUserDetailsUser1
//    void showEventForm__WithUser() throws Exception {
//        MvcResult result = mockMvc.perform(get("/events/form"))
//
//                .andExpect(status().isOk())
//                .andExpect(view().name("events/create"))
//
//                .andExpect(content().string(containsString("Create New Event")))
//
//                .andReturn();
//    }

//    @Test
//    @DisplayName("Show Event Form Auto Populate")
//    @WithMockEventUserDetailsUser1
//    void showEventFormAutoPopulate() throws Exception {
//        MvcResult result = mockMvc.perform(post("/events/new")
//
//                .param("auto", "auto")
//        )
//                .andExpect(status().isOk())
//                .andExpect(view().name("events/create"))
//
//                .andExpect(content().string(containsString("Create New Event")))
//                .andExpect(content().string(containsString("A new event....")))
//                .andExpect(content().string(containsString("This was auto-populated to save time creating a valid event.")))
//
//                .andReturn();
//    }

//    @Test
//    @DisplayName("Show Event Form Auto Populate - admin1")
//    @WithMockEventUserDetailsAdmin1
//    void showEventFormAutoPopulate_admin1() throws Exception {
//        MvcResult result = mockMvc.perform(post("/events/new")
//
//                .param("auto", "auto")
//        )
//                .andExpect(status().isOk())
//                .andExpect(view().name("events/create"))
//
//                .andExpect(content().string(containsString("Create New Event")))
//                .andExpect(content().string(containsString("A new event....")))
//                .andExpect(content().string(containsString("This was auto-populated to save time creating a valid event.")))
//
//                .andReturn();
//    }

//    private Matcher<String> doesNotContainString(String s) {
//        return CoreMatchers.not(containsString(s));
//    }


//    @Test
//    @DisplayName("Submit Event Form")
//    @WithMockEventUserDetailsUser1
//    void createEvent() throws Exception {
//
//        MvcResult result = mockMvc.perform(post("/events/new")
//
//                .param("attendeeEmail", "user2@baselogic.com")
//                .param("when", "2020-07-03 00:00:01")
//                .param("summary", "Test Summary")
//                .param("description", "Test Description")
//        )
//                .andExpect(status().isFound())
//                .andExpect(redirectedUrl("/events/my"))
//                .andExpect(header().string("Location", "/events/my"))
//                .andExpect(flash().attribute("message", "Successfully added the new event"))
//
//                .andReturn();
//
//    }


    /*@Test
    @DisplayName("Submit Event Form - null email")
    @WithMockEventUserDetailsUser1
    void createEvent_null_email() throws Exception {

        MvcResult result = mockMvc.perform(post("/events/new")

//                .param("attendeeEmail", "user2@baselogic.com")
                .param("when", "2020-07-03 00:00:01")
                .param("summary", "Test Summary")
                .param("description", "Test Description")
        )
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("eventDto", "attendeeEmail"))

                .andReturn();

    }*/

//    @Test
//    @DisplayName("Submit Event Form - not found email")
//    @WithMockEventUserDetailsUser1
//    void createEvent_not_found_email() throws Exception {
//
//        MvcResult result = mockMvc.perform(post("/events/new")
//
//                .param("attendeeEmail", "notFound@baselogic.com")
//                .param("when", "2020-07-03 00:00:01")
//                .param("summary", "Test Summary")
//                .param("description", "Test Description")
//        )
//                .andExpect(status().isOk())
//                .andExpect(model().hasErrors())
//                .andExpect(model().attributeHasFieldErrors("eventDto", "attendeeEmail"))
//
//                .andReturn();
//
//    }


    //-----------------------------------------------------------------------//

//    @Test
//    @DisplayName("Submit Event Form - null when")
//    @WithMockEventUserDetailsUser1
//    void createEvent_null_when() throws Exception {
//
//        MvcResult result = mockMvc.perform(post("/events/new")
//
//                .param("attendeeEmail", "user2@baselogic.com")
////                .param("when", "2020-07-03 00:00:01")
//                .param("summary", "Test Summary")
//                .param("description", "Test Description")
//        )
//                .andExpect(status().isOk())
//                .andExpect(model().hasErrors())
//                .andExpect(model().attributeHasFieldErrors("eventDto", "when"))
//
//                .andReturn();
//
//    }


    //-----------------------------------------------------------------------//

//    @Test
//    @DisplayName("Submit Event Form - null summary")
//    @WithMockEventUserDetailsUser1
//    void createEvent_null_summary() throws Exception {
//
//        MvcResult result = mockMvc.perform(post("/events/new")
//
//                .param("attendeeEmail", "user2@baselogic.com")
//                .param("when", "2020-07-03 00:00:01")
////                .param("summary", "Test Summary")
//                .param("description", "Test Description")
//        )
//                .andExpect(status().isOk())
//                .andExpect(model().hasErrors())
//                .andExpect(model().attributeHasFieldErrors("eventDto", "summary"))
//
//                .andReturn();
//
//    }


    //-----------------------------------------------------------------------//

//    @Test
//    @DisplayName("Submit Event Form - null description")
//    @WithMockEventUserDetailsUser1
//    void createEvent_null_description() throws Exception {
//
//        MvcResult result = mockMvc.perform(post("/events/new")
//
//                .param("attendeeEmail", "notFound@baselogic.com")
//                .param("when", "2020-07-03 00:00:01")
//                .param("summary", "Test Summary")
////                .param("description", "Test Description")
//        )
//                .andExpect(status().isOk())
//                .andExpect(model().hasErrors())
//                .andExpect(model().attributeHasFieldErrors("eventDto", "description"))
//
//                .andReturn();
//
//    }

    //-----------------------------------------------------------------------//

//    @Test
//    @DisplayName("Validate EventUserDetails")
//    void validate_EventUserDetails() throws Exception {
//
//        assertThat(TestUtils.user1UserDetails.isAccountNonExpired()).isTrue();
//        assertThat(TestUtils.user1UserDetails.isAccountNonLocked()).isTrue();
//        assertThat(TestUtils.user1UserDetails.isCredentialsNonExpired()).isTrue();
//        assertThat(TestUtils.user1UserDetails.isEnabled()).isTrue();
//
//    }

} // The End...