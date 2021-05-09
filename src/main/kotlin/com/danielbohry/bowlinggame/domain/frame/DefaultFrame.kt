package com.danielbohry.bowlinggame.domain.frame

open class DefaultFrame : Frame {

    companion object {
        const val MAX_ROLLS = 2
    }

    protected var completed: Boolean = false
    protected var rolls = mutableListOf<Int>()

    override fun roll(value: Int) {
        if (rolls.size < MAX_ROLLS) {
            rolls.add(value)

            if (isStrike()) {
                completed = true
            }
        }

        if (rolls.size == MAX_ROLLS) {
            completed = true
        }
    }

    override fun isCompleted(): Boolean {
        return completed
    }

    override fun isStrike(): Boolean {
        return rolls.size == 1 && rolls.first() == 10
    }

    override fun isSpare(): Boolean {
        return rolls.size == 2 && rolls.sum() == 10
    }

    override fun score(): Int {
        return if (isCompleted()) rolls.sum() else 0
    }

    override fun partialScore(): Int {
        return rolls.sum()
    }

    override fun totalRolls(): Int {
        return rolls.size
    }

}
