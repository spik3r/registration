package com.kaitait.registration.controller

import com.kaitait.registration.domain.User
import com.kaitait.registration.service.UserService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Unroll

class UserControllerSpec extends Specification {

    UserController controller
    ControllerExceptionHandler controllerExceptionHandler
    UserService userService
    MockMvc mockMvc

    def registerUri = '/api/users/register'
    def updateUri = '/api/users/update'
    def byIdUri = '/api/users/id/'
    def baseUri = '/api/users/'
    def byEmailUri = '/api/users/email/'


    void setup() {
        userService = Mock()
        controllerExceptionHandler = new ControllerExceptionHandler()
        controller = new UserController(userService)
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .alwaysDo(MockMvcResultHandlers.print())
                .setControllerAdvice(controllerExceptionHandler)
                .build()
    }

    def "register endpoint should perform as expected for valid input"() {
        given:
        User newUser = new User("bob", "smith", "bob@email.com", "123")
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(registerUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "bob")
                .param("lastName", "smith")
                .param("email", "bob@email.com")
                .param("password", "123")
        )
        then:
        noExceptionThrown()
        1 * userService.register(_) >> newUser

        and:
        actions.andExpect(MockMvcResultMatchers.status().isCreated())
    }

    def "register endpoint should throw 400 for missing firstName"() {
        given:
        User newUser = new User("bob", "smith", "bob@email.com", "123")
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(registerUri)
                .param("lastName", "smith")
                .param("email", "bob@mail.com")
                .param("password", "abc123")
        )

        then:
        noExceptionThrown()
        0 * userService.register(_) >> newUser

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    def "register endpoint should throw 400 for missing lastName"() {
        given:
        User newUser = new User("bob", "smith", "bob@email.com", "123")
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(registerUri)
                .param("firstName", "bob")
                .param("email", "bob@mail.com")
                .param("password", "abc123")
        )

        then:
        noExceptionThrown()
        0 * userService.register(_) >> newUser

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    def "register endpoint should throw 400 for missing email"() {
        given:
        User newUser = new User("bob", "smith", "bob@email.com", "123")
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(registerUri)
                .param("firstName", "bob")
                .param("lastName", "smith")
                .param("password", "abc123")
        )

        then:
        noExceptionThrown()
        0 * userService.register(_) >> newUser

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    def "register endpoint should throw 400 for missing password"() {
        given:
        User newUser = new User("bob", "smith", "bob@email.com", "123")
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(registerUri)
                .param("firstName", "bob")
                .param("lastName", "smith")
                .param("email", "bob@mail.com")
        )

        then:
        noExceptionThrown()
        0 * userService.register(_) >> newUser

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    def "update endpoint works as expected for valid id and parameters"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(updateUri)
                .param("id", "abc_123_xyz")
                .param("firstName", "bob")
                .param("lastName", "smith")
                .param("email", "bob@mail.com")
                .param("password", "abc123")
        )

        then:
        noExceptionThrown()
        1 * userService.updateBy("abc_123_xyz", _)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isOk())
    }

    def "update endpoint throws 400 for missing id"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(updateUri)
                .param("firstName", "bob")
                .param("lastName", "smith")
                .param("email", "bob@mail.com")
                .param("password", "abc123")
        )

        then:
        noExceptionThrown()
        0 * userService.updateBy(_, _)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    def "update endpoint throws 400 for missing firstName"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(updateUri)
                .param("id", "abc_123_xyz")
                .param("lastName", "smith")
                .param("email", "bob@mail.com")
                .param("password", "abc123")
        )

        then:
        noExceptionThrown()
        0 * userService.updateBy(_, _)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    def "update endpoint throws 400 for missing lastName"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(updateUri)
                .param("id", "abc_123_xyz")
                .param("firstName", "bob")
                .param("email", "bob@mail.com")
                .param("password", "abc123")
        )

        then:
        noExceptionThrown()
        0 * userService.updateBy(_, _)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    def "update endpoint throws 400 for missing email"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(updateUri)
                .param("id", "abc_123_xyz")
                .param("firstName", "bob")
                .param("lastName", "smith")
                .param("password", "abc123")
        )

        then:
        noExceptionThrown()
        0 * userService.updateBy(_, _)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    def "update endpoint throws 400 for missing password"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(updateUri)
                .param("id", "abc_123_xyz")
                .param("firstName", "bob")
                .param("lastName", "smith")
                .param("email", "bob@mail.com")
        )

        then:
        noExceptionThrown()
        0 * userService.updateBy(_, _)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    def "getAll endpoint works as expected"() {
        given:
        User newUser = new User("bob", "smith", "bob@email.com", "123")

        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(baseUri)
        )

        then:
        noExceptionThrown()
        1 * userService.getAllUsers() >> Collections.emptyList()

        and:
        actions.andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Unroll
    void "getById endpoint should perform as expected id #id"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(byIdUri)
                .param("id", id))

        then:
        noExceptionThrown()

        1 * userService.getById(id)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isOk())

        where:
        id << ["abc", "foo", "false"]
    }

    void "getById endpoint should return 400 for null id"() {

        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(byIdUri))

        then:
        noExceptionThrown()

        0 * userService.getById(_)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }


    @Unroll
    void "deleteById endpoint should perform as expected email #email"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .delete(byIdUri)
                .param("id", id))

        then:
        noExceptionThrown()

        1 * userService.deleteById(id)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isNoContent())

        where:
        id << ["123_abc_xyz", "321_abc_qwerty", "asdfghjkl_01"]
    }

    void "deleteById endpoint should return 400 for null email"() {

        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .delete(byIdUri))

        then:
        noExceptionThrown()

        0 * userService.deleteById(_)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    @Unroll
    void "getByEmail endpoint should perform as expected email #email"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(byEmailUri)
                .param("email", email))

        then:
        noExceptionThrown()

        1 * userService.findByEmail(email)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isOk())

        where:
        email << ["a@a.com", "b@.b.de", "c@c.com.au"]
    }

    void "getByEmail endpoint should return 400 for null email"() {

        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(byEmailUri))

        then:
        noExceptionThrown()

        0 * userService.findByEmail(_)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }

    @Unroll
    void "deleteByEmail endpoint should perform as expected email #email"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .delete(byEmailUri)
                .param("email", email))

        then:
        noExceptionThrown()

        1 * userService.deleteByEmail(email)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isNoContent())

        where:
        email << ["a@a.com", "b@.b.de", "c@c.com.au"]
    }

    void "deleteByEmail endpoint should return 400 for null email"() {

        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .delete(byEmailUri))

        then:
        noExceptionThrown()

        0 * userService.deleteByEmail(_)

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())
    }
}