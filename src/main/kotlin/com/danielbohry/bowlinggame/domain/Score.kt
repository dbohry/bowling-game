package com.danielbohry.bowlinggame.domain

class Score(private val player: Player) {

    private val score = mutableListOf<Int>()
    private var frameNumber = player.frameNumber()

    fun update() {
        if (hasOneNotComputedFrame()) {
            val last = player.getFrame(frameNumber - 1)

            if (isGameOver()) {
                addScore(last.score())
            } else if (last.isSpare() && player.currentFrame().totalRolls() == 1) {
                addScore(last.score() + player.currentFramePartialScore())
                frameNumber += 1
            } else if (!last.isSpare() && !last.isStrike()) {
                addScore(last.score())
                frameNumber += 1
            }
        } else if (hasOneNotComputedStrike()) {
            val before = player.getFrame(frameNumber - 1)
            val last = player.getFrame(frameNumber)

            if (isGameOver()) {
                addScore(before.score() + last.partialScore())
                frameNumber += 1
                return update()
            }

            if (before.isSpare() || (last.isCompleted() && !last.isStrike())) {
                addScore(before.score() + last.score())
                frameNumber += 1
                return update()
            }

        } else if (hasTwoNotComputedStrikes()) {
            val firstStrike = player.getFrame(frameNumber - 1)
            val secondStrike = player.getFrame(frameNumber)
            val last = player.getFrame(frameNumber + 1)
            val lastFrameScore = if (last.score() == 30) 10 else last.score()

            addScore(firstStrike.score() + secondStrike.score() + lastFrameScore)
            frameNumber += 1
            return update()
        }

    }

    private fun isGameOver() = player.frameNumber() > 10 && player.currentFrame().isCompleted()

    private fun addScore(value: Int) {
        val last = if (score.isEmpty()) 0 else score.last()
        score.add(last + value)
    }

    private fun hasOneNotComputedFrame() = player.frameNumber() - frameNumber == 1

    private fun hasOneNotComputedStrike() = player.frameNumber() - frameNumber == 2

    private fun hasTwoNotComputedStrikes() = player.frameNumber() - frameNumber == 3

    fun total(): Int {
        return if (score.isEmpty()) 0 else score.last()
    }

    fun print() {
        println(score.joinToString(" | "))
        println("Total: ${total()}\n")
    }

}
