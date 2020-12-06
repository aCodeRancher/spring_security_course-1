package io.baselogic.springsecurity.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockEventUserDetails(username="disabled1@baselogic.com", password="disabled1")
public @interface WithMockDisabledUser {
}
