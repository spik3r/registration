package com.kaitait.registration.controller


import org.springframework.http.HttpStatus
import spock.lang.Specification

import javax.servlet.http.HttpServletRequest

class ControllerExceptionHandlerSpec extends Specification {

    ControllerExceptionHandler controllerExceptionHandler
    HttpServletRequest request = Mock()
    IllegalArgumentException iae
    RuntimeException rte

    void setup() {
        controllerExceptionHandler = new ControllerExceptionHandler()
        request = Mock()
        iae = new IllegalArgumentException("IAE")
        rte = new RuntimeException("RTE")
    }

    def "IllegalArgumentExceptionErrorHandler works as expected"() {
        when:
        def result = controllerExceptionHandler.IllegalArgumentExceptionErrorHandler(request, iae)

        then:
        result.getStatusCode() == HttpStatus.BAD_REQUEST
        result.getBody() == "Something unexpected happened"

    }

    def "RuntimeExceptionErrorHandler works as expected"() {
        when:
        def result = controllerExceptionHandler.RuntimeExceptionErrorHandler(request, rte)

        then:
        result.getStatusCode() == HttpStatus.BAD_REQUEST
        result.getBody() == "Something unexpected happened"
    }
}
