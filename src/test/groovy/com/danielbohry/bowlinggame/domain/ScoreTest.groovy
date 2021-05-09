package com.danielbohry.bowlinggame.domain


import spock.lang.Specification

class ScoreTest extends Specification {

    def "should return 0 when first frame not completed"() {
        given:
        def player = new Player()
        def score = new Score(player)

        and: "player rolls"
        [5].each { player.roll(it) }

        when:
        score.update()

        then:
        score.total() == 0
    }

    def "should return total when first frame is completed"() {
        given:
        def player = new Player()
        def score = new Score(player)

        and: "player rolls"
        [5, 4].each { player.roll(it) }

        when:
        score.update()

        then:
        score.total() == 9
    }

    def "should return total when first frame is completed but second frame is ongoing"() {
        given:
        def player = new Player()
        def score = new Score(player)

        and: "player rolls"
        [5, 4, 2].each { player.roll(it) }

        when:
        score.update()

        then:
        score.total() == 9
    }

    def "should return total when two frames played (no strike and no spare)"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [5, 4, 2, 2].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 13
    }

    def "should return 0 when first frame is a spare"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [5, 5].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 0
    }

    def "should return total when first frame is a spare and second frame is ongoing"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [5, 5, 5].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 15
    }

    def "should return total when first frame is a spare and second frame is done"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [5, 5, 5, 2].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 22
    }

    def "should return total when two spares and next frame is ongoing"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [5, 5, 5, 5, 2].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 27
    }

    def "should return total when two spares and next frame is done"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [5, 5, 5, 5, 2, 3].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 32
    }

    def "should return total when first frame was strike and second frame is ongoing"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [10, 5].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 0
    }

    def "should return total when first frame was strike and second frame is done"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [10, 5, 2].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 24
    }

    def "should return total when first frame was strike and second frame is a spare"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [10, 5, 5].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 20
    }

    def "should return total when first frame was strike and second frame is a spare and third is ongoing"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [10, 5, 5, 2].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 32
    }

    def "should return total when first frame was strike and second frame is a spare and third is done"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [10,
         5, 5,
         2, 2].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 36
    }

    def "should return 0 when two strikes"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [10,
         10].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 0
    }

    def "should return total when three strikes"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [10,
         10,
         10].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 30
    }

    def "should calculate score (example)"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [
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
        ].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 133
    }

    def "should calculate score (example all rolls are 2 with strike)"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                10,
                2, 2,
                2, 2
        ].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 50
    }

    def "should calculate score (example all rolls are 2 with final spare)"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                2, 8,
                2, 2
        ].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 48
    }

    def "should calculate score (example all rolls are 2 with final spare after strike)"() {
        given:
        def player = new Player()
        def score = new Score(player)

        when:
        [
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                2, 2,
                10,
                2, 8,
                2, 2
        ].each {
            player.roll(it)
            score.update()
        }

        then:
        score.total() == 64
    }

}
