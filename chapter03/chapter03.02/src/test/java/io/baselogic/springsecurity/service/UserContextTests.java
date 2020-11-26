package io.baselogic.springsecurity.service;

import io.baselogic.springsecurity.dao.TestUtils;
import io.baselogic.springsecurity.domain.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * UserContextTests
 *
 * @since chapter1.00
 */

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class UserContextTests {

    @Autowired
    private UserContext userContext;

    // Mockito:
    @MockBean
    private SecurityContext securityContext;

    @MockBean
    private Authentication authentication;

    @MockBean
    private EventService eventService;


    //-----------------------------------------------------------------------//

    private AppUser appUser1 = new AppUser();
    private AppUser testAppUser1 = new AppUser();


    @BeforeEach
    void beforeEachTest() {
        appUser1 = TestUtils.user1;
        testAppUser1 = TestUtils.TEST_APP_USER_1;
    }


    @Test
    void initJdbcOperations() {
        assertThat(userContext).isNotNull();
    }

    //-----------------------------------------------------------------------//


    @Test
    @DisplayName("getCurrentUser - null Authentication returns null")
    void test_getCurrentUser__null_authentication() {

        // Expectation
        // SecurityContext:
        given(this.securityContext.getAuthentication())
                .willReturn(authentication);

        // Authentication:
        given(this.authentication.getPrincipal())
                .willReturn(new AppUser());

        AppUser appUser = userContext.getCurrentUser();

        assertThat(appUser).isNull();
//        verify(this.securityContext).getAuthentication();
//        verify(this.authentication).getPrincipal();
    }

//    @Test
    @DisplayName("getCurrentUser - null User email - returns null")
    @WithMockUser("user1@baselogic.com")
    void test_getCurrentUser__null_user_email() {

        // Expectation
        // SecurityContext:
        given(this.securityContext.getAuthentication())
                .willReturn(authentication);

        // Authentication:
        given(this.authentication.getPrincipal())
                .willReturn(new AppUser());

        AppUser appUser = userContext.getCurrentUser();

        assertThat(appUser).isNull();
    }

    @Test
    @DisplayName("getCurrentUser - throws IllegalStateException")
    @WithMockUser("user1@baselogic.com")
    void test_getCurrentUser__throws_IllegalStateException() {

        AppUser appUser = new AppUser();
        appUser.setEmail("test@foobar.com");


        // Expectation
        // SecurityContext:
        given(this.securityContext.getAuthentication())
                .willReturn(authentication);

        // Authentication:
        given(this.authentication.getPrincipal())
                .willReturn(new AppUser());

        // Authentication:
        given(this.eventService.findUserByEmail(any(String.class)))
                .willReturn(null);

        assertThrows(IllegalStateException.class, () -> {
            userContext.getCurrentUser();
        });
    }


    //-----------------------------------------------------------------------//

    @Test
    void test_setCurrentUser() {
        userContext.setCurrentUser(appUser1);

        assertThrows(IllegalStateException.class, () -> {
            userContext.getCurrentUser();
        });
    }

    @Test
    void test_setCurrentUser__UsernameNotFoundException() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userContext.setCurrentUser(testAppUser1);
        });
    }

    @Test
    void test_setCurrentUser_null_User() {
        assertThrows(NullPointerException.class, () -> {
            userContext.setCurrentUser(null);
        });
    }

    @Test
    void test_setCurrentUser_invalid_User() {
        assertThrows(NullPointerException.class, () -> {
            userContext.setCurrentUser(new AppUser());
        });
    }


    @Test
    @DisplayName("getCurrentUser with a null authentication from SecurityContext")
    void test_getCurrentUser_null_authentication() {
        SecurityContextHolder.clearContext();
        AppUser result = userContext.getCurrentUser();
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Test getCurrentUser without any exception")
    @WithMockUser(username="user1@baselogic.com", roles="USER")
    void test_getCurrentUser(){
        String email = "user1@baselogic.com";
        given(eventService.findUserByEmail(email)).willReturn(appUser1);
        AppUser appUser = userContext.getCurrentUser();
        assertTrue(appUser.getEmail().equals(appUser1.getEmail()));
        verify(eventService,times(1)).findUserByEmail(email);
    }

    @Test
    @DisplayName("Test with a random user in security context")
    @WithMockUser(username="helen@baselogic.com", password="helenPIN" , roles="STUDENT")
    void test_getCurrentRandomUser(){
        String email = "helen@baselogic.com";
        AppUser randomUser = new AppUser();
        randomUser.setEmail(email);
        given(eventService.findUserByEmail(email)).willReturn(randomUser);
        AppUser appUser = userContext.getCurrentUser();
        assertTrue(appUser.getEmail().equals(email));
        verify(eventService,times(1)).findUserByEmail(email);
        Authentication authentication = userContext.getCurrentContext().getAuthentication();
        assertTrue(authentication.getCredentials().toString().equals("helenPIN"));
        Iterator itr = authentication.getAuthorities().iterator();
        assertTrue(itr.next().toString().equals("ROLE_STUDENT"));
        assertTrue(authentication.getName().equals("helen@baselogic.com"));
    }

    @DisplayName("Test with appUser1 without @WithMockUser")
    @Test
    void test_setCurrentUser_AppUser1(){
         userContext.setCurrentUser(appUser1);
         Authentication authentication = userContext.getCurrentContext().getAuthentication();
         assertTrue(authentication.getCredentials().toString().equals("user1"));
         Iterator itr = authentication.getAuthorities().iterator();
         assertTrue(itr.next().toString().equals("ROLE_USER"));
         assertTrue(authentication.getName().equals("user1@baselogic.com"));
    }


} // The End...
