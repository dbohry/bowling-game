package com.danielbohry.bowlinggame.domain.frame

interface Frame {

    fun roll(value: Int)

    fun isCompleted(): Boolean

    fun isStrike() : Boolean

    fun isSpare() : Boolean

    fun score() : Int

    fun partialScore() : Int

    fun totalRolls() : Int

}
