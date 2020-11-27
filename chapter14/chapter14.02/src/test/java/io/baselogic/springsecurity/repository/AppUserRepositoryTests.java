package io.baselogic.springsecurity.repository;

import io.baselogic.springsecurity.configuration.MongoDataInitializer;
import io.baselogic.springsecurity.dao.TestUtils;
import io.baselogic.springsecurity.domain.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author mickknutson
 *
 * @since chapter14.01 Updated for Reactive WebFlux
 */
@DataMongoTest
@Import({MongoDataInitializer.class})
@Slf4j
class AppUserRepositoryTests {

    @Autowired
    private AppUserRepository repository;

    @BeforeEach
    void beforeEachTest() {
    }

    @Test
    @DisplayName("Initialize Repository")
    void initRepositoryOperations() {
        assertThat(repository).isNotNull();
    }


    @Test
    @DisplayName("test_findAll")
	void test_findAll() {

        Flux<AppUser> result = repository.findAll();

        StepVerifier
                .create(result.log())
                .expectNextCount(3)
                .expectComplete()
                .verify();
	}

    @Test
    @DisplayName("findByEmail_user1")
	void test_findByEmail_user1() {
        String username = "user1@baselogic.com";

        Mono<AppUser> result = repository.findByEmail(username);

        StepVerifier
                .create(result.log())
                .assertNext(u -> {
                    log.info("AppUser: {} ", u);
                    assertThat(u)
                            .hasFieldOrPropertyWithValue("id", 0)
                            .hasFieldOrPropertyWithValue("email", "user1@baselogic.com")
                            .hasFieldOrPropertyWithValue("name", "user1@baselogic.com")
                            .hasFieldOrPropertyWithValue("persisted", false)
                            .hasFieldOrPropertyWithValue("isNew", true)
                    ;

                })
                .expectComplete()
                .verify();
	}

	@Test
    @DisplayName("findByEmail_admin1")
	void test_findByEmail_admin1() {
	    String username = "admin1@baselogic.com";

        Mono<AppUser> result = repository.findByEmail(username);

        StepVerifier
                .create(result.log())
                //.expectNextMatches { it.isNotEmpty() }
                .assertNext(u -> {
                    log.info("Result: {} ", u);
                    assertThat(u)
                            .hasFieldOrPropertyWithValue("id", 1)
                            .hasFieldOrPropertyWithValue("email", "admin1@baselogic.com");

                })
                .expectComplete()
                .verify();
	}

    @Test
    @DisplayName("findById - user1")
    void test_findById_user1() {

        Mono<AppUser> result = repository.findById(0);

        StepVerifier
                .create(result.log())
                .assertNext(r -> {
                    log.info("Result: {} ", r);
                    assertThat(r)
                            .hasFieldOrPropertyWithValue("id", 0)
                            .hasFieldOrPropertyWithValue("email", "user1@baselogic.com");

                }).expectComplete().verify();
    }


    @Test
    @DisplayName("findByEmail - email not found")
    void test_findByEmail_no_results() {
        String username = "foo@baselogic.com";

        Mono<AppUser> result = repository.findByEmail(username);

        StepVerifier
                .create(result.log())
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }


    @Test
    @DisplayName("findAllByEmail")
    void test_findAllByEmail() {
        String username = "@baselogic.com";

        Flux<AppUser> result = repository.findAllByEmailContaining(username);

        StepVerifier
                .create(result.log())
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("AppUserRepository - findAllByEmail - not found")
    void test_findAllByEmail_no_results() {
        String username = "@baselogic.BAD";

        Flux<AppUser> result = repository.findAllByEmailContaining(username);

        StepVerifier
                .create(result.log())
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }


    @Test
    @DisplayName("save user - no Id")
    void test_saveUser_no_id() {

        AppUser testUser = TestUtils.createMockUser("test@baselogic.com", "test", "example");

        StepVerifier
                .create(repository.save(testUser).log())
                .expectError(InvalidDataAccessApiUsageException.class)
                .verify();
    }

    @Test
    @DisplayName("save user - with ID")
    void test_saveUser_with_id() {

        String username = "@baselogic.com";

        Flux<AppUser> result = repository.findAllByEmailContaining(username);
        StepVerifier.create(result.log()).expectNextCount(3).expectComplete().verify();

//        StepVerifier.create(repository.count().log()).expectNextCount(3).expectComplete().verify();

        AppUser testUser = TestUtils.testUser1;

        StepVerifier
                .create(repository.save(testUser).log())
                .assertNext(u -> {
                    log.info("AppUser: {} ", u);
                    assertThat(u)
                            .hasFieldOrPropertyWithValue("id", 42)
                            .hasFieldOrPropertyWithValue("email", "test@baselogic.com")
                            .hasFieldOrPropertyWithValue("password", "test");

                })
                .expectComplete()
                .verify();


        result = repository.findAllByEmailContaining(username);
        StepVerifier.create(result.log()).expectNextCount(4).expectComplete().verify();
    }


} // The End...
