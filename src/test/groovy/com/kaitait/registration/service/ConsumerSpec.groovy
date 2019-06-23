package com.kaitait.registration.service

import com.kaitait.registration.domain.User
import spock.lang.Specification

class ConsumerSpec extends Specification {
    Consumer consumer
    User user

    void setup() {
        consumer = new Consumer()
        user = Mock()
    }
    def "Consume works as expected"() {
        when:
        User consumedUser = consumer.consume(user)

        then:
        noExceptionThrown()
        consumedUser == user
    }
}
