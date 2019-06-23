package com.kaitait.registration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class RegistrationApplicationSpec extends Specification {
        @Autowired (required = false)
        RegistrationApplication registrationApplication

        def "when context is loaded registrationApplication is created"() {
            expect: "the registrationApplication is created"
            registrationApplication
        }
}
