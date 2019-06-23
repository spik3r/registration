package com.kaitait.registration.domain

import spock.lang.Specification

class UserSpec extends Specification {

    def "User update constructor works as expected"() {
        given:
        User oldUser = new User("firstName","lastName","email","pass")
        oldUser.setId("123")
        oldUser.setCreatedAt(new Date(123456L))

        and:
        User newUser = new User("John","Snow","js@mail.org","winter123")

        when:
        User updatedUser = new User(oldUser, newUser)

        then:
        updatedUser.getId() == "123"
        updatedUser.getCreatedAt() == new Date(123456L)
        updatedUser.getFirstName() == "John"
        updatedUser.getLastName() == "Snow"
        updatedUser.getEmail() == "js@mail.org"
        updatedUser.getPassword() == "winter123"
    }
}
