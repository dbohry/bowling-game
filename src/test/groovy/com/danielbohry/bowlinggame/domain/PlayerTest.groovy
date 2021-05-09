package com.danielbohry.bowlinggame.domain

import spock.lang.Specification

class PlayerTest extends Specification {

    def "should keep at frame 1 when no played rolls"() {
        when:
        def player = new Player()

        then:
        player.frameNumber() == 1
    }

    def "should keep at frame 1 after one roll (no strike)"() {
        given:
        def player = new Player()

        when:
        player.roll(5)

        then:
        player.frameNumber() == 1
    }

    def "should move to the next frame after two rolls"() {
        given:
        def player = new Player()

        when:
        [4, 5].each { player.roll(it) }

        then:
        player.frameNumber() == 2
    }

    def "should move to the next frame after strike"() {
        given:
        def player = new Player()

        when:
        player.roll(10)

        then:
        player.frameNumber() == 2
    }

}
