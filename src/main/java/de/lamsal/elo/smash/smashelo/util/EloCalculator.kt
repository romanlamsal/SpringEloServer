package de.lamsal.elo.smash.smashelo.util

import de.lamsal.elo.smash.smashelo.game.*
import de.lamsal.elo.smash.smashelo.model.Player

/**
 * Calc estimate that p1 wins against p2
 */
fun calcEstimate(p1 : Player, p2 : Player) : Double {
    val weightingFactor = estimateWeightFactor // read from properties
    val estimate = 1 + Math.pow(10.0, (p2.elo - p1.elo) / weightingFactor)
    return 1.0 / estimate
}

fun calcNewElo(player : Player, estimate : Double, points : Double) : Long {
    return player.elo + (eloUpdateFactor * (points - Math.max(loserWeight, estimate))).toLong()
}
