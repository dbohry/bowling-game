package com.danielbohry.bowlinggame.domain.frame

import spock.lang.Specification
import spock.lang.Subject

class DefaultFrameTest extends Specification {

    @Subject
    def frame = new DefaultFrame()

    def "new frame should be isCompleted false"() {
        expect:
        !frame.completed
    }

    def "after one roll (no strike) isCompleted should be false"() {
        when:
        frame.roll(6)

        then:
        !frame.completed
    }

    def "after strike isCompleted should be true"() {
        when:
        frame.roll(10)

        then:
        frame.completed
    }

    def "after two rolls (no strike) isCompleted should be true"() {
        when:
        [2, 4].each { frame.roll(it) }

        then:
        frame.completed
    }

}
