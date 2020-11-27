package io.baselogic.springsecurity.userdetails;

import io.baselogic.springsecurity.dao.TestUtils;
import io.baselogic.springsecurity.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * DefaultEventServiceTests
 *
 * @since chapter03.03
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class EventUserDetailsServiceTests {

    // Mockito:
    @MockBean
    private UserDao userDao;

    @Autowired
    private EventUserDetailsService eventUserDetailsService;


    //-----------------------------------------------------------------------//


    @BeforeEach
    void beforeEachTest() {
    }


    @Test
    @DisplayName("initOperations")
    void initOperations() {
        assertThat(eventUserDetailsService).isNotNull();
    }

    //-----------------------------------------------------------------------//


    /*@Test
    @DisplayName("loadUserByUsername - user1")
    void test_loadUserByUsername_user1() {

        // Expectation
        given(userDao.findByEmail(any(String.class)))
                .willReturn(TestUtils.user1);

        UserDetails result = eventUserDetailsService.loadUserByUsername(TestUtils.user1.getEmail());

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("loadUserByUsername - admin1")
    void test_loadUserByUsername_admin1() {

        // Expectation
        given(userDao.findByEmail(any(String.class)))
                .willReturn(TestUtils.admin1);

        UserDetails result = eventUserDetailsService.loadUserByUsername(TestUtils.admin1.getEmail());

        assertThat(result).isNotNull();
    }


    @Test
    @DisplayName("loadUserByUsername - no User found")
    void test_loadUserByUsername__null_credentials() {

        assertThrows(UsernameNotFoundException.class, () -> {
            UserDetails result = eventUserDetailsService.loadUserByUsername("foobar");
        });
    }


    @Test
    @DisplayName("loadUserByUsername - null User")
    void test_loadUserByUsername_null_user() {

        // Expectation
        given(userDao.findByEmail(any(String.class)))
                .willReturn(null);

        String email = TestUtils.admin1.getEmail();

        assertThrows(UsernameNotFoundException.class, () -> {
            eventUserDetailsService.loadUserByUsername(email);
        });
    }*/


} // The End...
