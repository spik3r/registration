package com.kaitait.registration.service

import com.kaitait.registration.domain.User
import org.springframework.kafka.core.KafkaTemplate
import spock.lang.Specification

class ProducerSpec extends Specification {
    Producer producer
    KafkaTemplate kafkaTemplate
    User user

    void setup() {
        user = Mock()
        kafkaTemplate = Mock()
        producer = new Producer(kafkaTemplate)
    }

    def "SendMessage works as expected"() {
        when:
        producer.sendMessage(user)

        then:
        1 * kafkaTemplate.send(_, user)
    }
}
