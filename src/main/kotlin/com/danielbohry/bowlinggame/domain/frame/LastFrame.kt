package com.danielbohry.bowlinggame.domain.frame

class LastFrame : DefaultFrame() {

    companion object {
        const val MAX_ROLLS = 3
    }

    override fun roll(value: Int) {
        if (rolls.size < MAX_ROLLS) {
            rolls.add(value)
        }

        if (rolls.size == 2 && rolls.sum() < 10) {
            completed = true
        }

        if (rolls.size == MAX_ROLLS) {
            completed = true
        }
    }

    override fun partialScore(): Int {
        return if (rolls.size >= 2) rolls.subList(0, 2).sum() else rolls.sum()
    }

    override fun isStrike(): Boolean {
        return rolls.sum() == 30
    }

    override fun isSpare(): Boolean {
        return rolls.size >= 3 && rolls.subList(0, 2).sum() == 10
    }

}
