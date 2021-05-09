package com.danielbohry.bowlinggame.domain.frame

import spock.lang.Specification
import spock.lang.Subject

class LastFrameTest extends Specification {

    @Subject
    def frame = new LastFrame()

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

    def "after first strike isCompleted should be false"() {
        when:
        frame.roll(10)

        then:
        !frame.completed
    }

    def "after two rolls (no strike) isCompleted should be true"() {
        when:
        [2, 4].each { frame.roll(it) }

        then:
        frame.completed
    }

    def "after a spare the isCompleted should be false"() {
        when:
        [8, 2].each { frame.roll(it) }

        then:
        !frame.completed
    }

    def "after a spare plus a bonus roll isCompleted should be true"() {
        when:
        [8, 2, 5].each { frame.roll(it) }

        then:
        frame.completed
    }

    def "after two strikes isCompleted should be false"() {
        when:
        [10, 10].each { frame.roll(it) }

        then:
        !frame.completed
    }

    def "after three strikes isCompleted should be true"() {
        when:
        [10, 10, 10].each { frame.roll(it) }

        then:
        frame.completed
    }

}
