package de.lamsal.elo.smash.smashelo.util

import de.lamsal.elo.smash.smashelo.game.loserWeight
import de.lamsal.elo.smash.smashelo.game.winnerWeight
import de.lamsal.elo.smash.smashelo.model.Player
import de.lamsal.elo.smash.smashelo.util.calcEstimate
import org.junit.Assert.*
import org.junit.Test
import kotlin.test.assertEquals

class EloCalculatorKtTest {

    @Test
    fun shouldResultInEqualEstimate_whenEqualElo() {
        assertEquals(0.5, calcEstimate(Player("p1"), Player("p2")), 0.0)
    }

    @Test
    fun shouldResultInFavorForP1_whenP1HasHigherElo() {
        val p1 = Player("p1")
        p1.elo = 1601
        assertTrue(0.5 < calcEstimate(p1, Player("p2")))
    }

    @Test
    fun shouldResultInFavorForP2_whenP2HasLowerElo() {
        val p1 = Player("p1")
        p1.elo = 1599
        assertTrue(0.5 > calcEstimate(p1, Player("p2")))
    }

    @Test
    fun shouldIncreasePoints_whenWon() {
        val p1 = Player("p1")
        assertTrue(calcNewElo(p1, 0.5, winnerWeight) > p1.elo)
    }

    @Test
    fun shouldNotIncreasePoints_whenLostWithMinimumEstimate() {
        val p1 = Player("p1")
        assertTrue(calcNewElo(p1, 0.1, loserWeight) <= p1.elo)
    }
}