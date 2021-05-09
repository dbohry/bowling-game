package com.danielbohry.bowlinggame

import com.danielbohry.bowlinggame.domain.Game

class BowlingGame {

    fun start() {
        val game = Game()

        listOf(
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
        ).forEach { game.play(it) }

    }

}

fun main() {
    BowlingGame().start()
}
