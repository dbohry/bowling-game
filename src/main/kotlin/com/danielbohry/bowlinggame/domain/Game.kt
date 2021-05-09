package com.danielbohry.bowlinggame.domain

class Game {

    companion object {
        const val MAX_FRAMES = 10
    }

    var isGameOver: Boolean = false
    val player = Player()
    val score = Score(player)

    fun play(pins: Int) {
        if (isGameOver) {
            return
        }

        player.roll(pins)
        score.update()
        score.print()

        if (player.frameNumber() > MAX_FRAMES && player.currentFrame().isCompleted()) {
            score.print()
            isGameOver = true
        }

    }

}
