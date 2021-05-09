package com.danielbohry.bowlinggame.domain

import com.danielbohry.bowlinggame.domain.frame.DefaultFrame
import com.danielbohry.bowlinggame.domain.frame.Frame
import com.danielbohry.bowlinggame.domain.frame.LastFrame

class Player {

    private val frames = mutableListOf<Frame>()
    private var currentFrame: Frame = DefaultFrame()

    fun roll(pins: Int) {
        currentFrame.roll(pins)

        if (currentFrame.isCompleted()) {
            frames.add(currentFrame)
            if (currentFrame !is LastFrame) {
                currentFrame = if (frameNumber() == 10) LastFrame() else DefaultFrame()
            }
        }

    }

    fun frameNumber(): Int {
        return frames.size + 1
    }

    fun getFrame(index: Int): Frame {
        return frames[index]
    }

    fun currentFrame(): Frame {
        return currentFrame
    }

    fun currentFramePartialScore(): Int {
        return currentFrame.partialScore()
    }

}
