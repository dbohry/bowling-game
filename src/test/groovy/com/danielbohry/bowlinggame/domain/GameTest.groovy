package com.danielbohry.bowlinggame.domain

import spock.lang.Specification

class GameTest extends Specification {

    def "should keep game until game is over"() {
        given:
        def game = new Game()

        when:
        def rolls = [
                5, 5,
                2, 3,
                6, 0,
                0, 5,
                2, 5,
                10
        ]
        rolls.each { game.play(it) }

        then:
        !game.isGameOver()
    }

    def "should finish the game after 10 frames"() {
        given:
        def game = new Game()

        when:
        def rolls = [
                5, 5,
                2, 3,
                6, 0,
                0, 5,
                2, 5,
                10,
                0, 0,
                6, 1,
                4, 3,
                9, 0
        ]
        rolls.each { game.play(it) }

        then:
        game.isGameOver()
    }

    def "should finish the game after 10 frames (following example)"() {
        given:
        def game = new Game()

        when:
        def rolls = [
                1, 4,
                4, 5,
                6, 4,
                5, 5,
                10,
                0, 1,
                7, 3,
                6, 4,
                10,
                2, 8, 6
        ]
        rolls.each { game.play(it) }

        then:
        game.isGameOver()
        game.score.print()

        and:
        game.score.total() == 133
    }

    def "should return max score if perfect game"() {
        given:
        def game = new Game()

        when:
        def rolls = [
                10,
                10,
                10,
                10,
                10,
                10,
                10,
                10,
                10,
                10,
                10, 10
        ]
        rolls.each { game.play(it) }

        then:
        game.isGameOver()

        and:
        game.score.total() == 300
    }
}
