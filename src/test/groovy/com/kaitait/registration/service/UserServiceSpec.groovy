package com.kaitait.registration.service

import com.kaitait.registration.domain.User
import com.kaitait.registration.repository.UserRepository
import spock.lang.Specification
import spock.lang.Unroll

class UserServiceSpec extends Specification {
    UserService userService
    UserRepository userRepository
    Producer producer
    User user
    User user2

    void setup() {
        user = new User("123",new Date(123456L),"John", "Snow", "js@gmail.com","iLoveSnow")
        user2 = new User("John", "Smith", "js@gmail.com","pochahontas123")

        producer = Mock()
        userRepository = Mock()
        userService = new UserService(userRepository, producer)
    }

    def "Register works as expected"() {
        when:
        userService.register(user)

        then:
        1 * userRepository.save(user) >> user2
        1 * producer.sendMessage(user2)
    }

    def "GetAllUsers works as expected"() {
        when:
        userService.getAllUsers()

        then:
        1 * userRepository.findAll()
    }

    @Unroll
    def "GetById works for id: #id"() {
        when:
        userService.getById(id)

        then:
        1 * userRepository.getById(id)

        where: id << ["1","12","123","1234","112345","1123456"]
    }

    @Unroll
    def "DeleteById works for id: #id"() {
        when:
        userService.deleteById(id)

        then:
        1 * userRepository.deleteById(id)

        where: id << ["1","12","123","1234","112345","1123456"]
    }

    @Unroll
    def "findByEmail works for id: #email"() {
        when:
        userService.findByEmail(email)

        then:
        1 * userRepository.findByEmail(email)

        where: email << ["email@1","email@12","email@123","email@1234","email@112345","email@1123456"]
    }

    @Unroll
    def "DeleteByEmail works for id: #email"() {
        when:
        userService.deleteByEmail(email)

        then:
        1 * userRepository.deleteByEmail(email)

        where: email << ["email@1","email@12","email@123","email@1234","email@112345","email@1123456"]
    }

    def "Update works as expected"() {
        when:
        userService.updateBy("123", user2)

        then:
        1 * userRepository.getById("123") >> user
        1 * userRepository.save(_)
    }
}
