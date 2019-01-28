package de.lamsal.elo.smash.smashelo.game

import de.lamsal.elo.smash.smashelo.model.EloChangeEvent
import de.lamsal.elo.smash.smashelo.model.Match
import de.lamsal.elo.smash.smashelo.model.Player
import de.lamsal.elo.smash.smashelo.repository.EloChangeEventRepository
import de.lamsal.elo.smash.smashelo.repository.MatchRepo
import de.lamsal.elo.smash.smashelo.repository.PlayerRepository
import de.lamsal.elo.smash.smashelo.util.calcEstimate
import de.lamsal.elo.smash.smashelo.util.calcNewElo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class GameService {

    @Autowired
    open lateinit var players: PlayerRepository
    @Autowired
    open lateinit var matches: MatchRepo
    @Autowired
    open lateinit var eloChanges: EloChangeEventRepository

    private fun createPlayer(playerName: String): Player {
        return players.save(Player(playerName))
    }

    fun matchByIDs(winnerId: String, loserId: String) {
        val winner = getPlayer(winnerId)
        val loser = getPlayer(loserId)

        val estimate = calcEstimate(winner, loser)
        val winnerEvent = updateWinnerElo(winner, estimate)
        val loserEvent = updateLoserElo(loser, 1 - estimate)

        val match = Match(winner, loser)
        winnerEvent.match = match
        loserEvent.match = match

        matches.save(match)
        eloChanges.saveAll(listOf(winnerEvent, loserEvent))
        players.saveAll(listOf(winner, loser))
    }

    fun getPlayer(name: String): Player {
        return players.findPlayerByName(name).takeIf { it.isPresent }?.get() ?: createPlayer(name)
    }

    public fun getPlayerHistory(playerName: String): List<Map<String, Any>> {
        val headers = listOf("player_name", "elo_before", "elo_after", "timestamp", "other_player_name")
        return players.findPlayerHistory(playerName)
                .map { it.slice(listOf(0, 1, 2, 3, (if (it[4] != playerName) 4 else 5))) } // decide which player is "other player" - either winner or loser
                .map { headers.zip(it).toMap() }
    }

    private fun updateLoserElo(loser: Player, estimate : Double) : EloChangeEvent =
            updatePlayerElos(loser, estimate, loserWeight)

    private fun updateWinnerElo(winner: Player, estimate : Double) : EloChangeEvent =
            updatePlayerElos(winner, estimate, winnerWeight)

    fun updatePlayerElos(player: Player, estimate : Double, points : Double) : EloChangeEvent =
        EloChangeEvent(player).also {
            player.elo = calcNewElo(player, estimate, points)
            it.setEloAfter(player.elo)
        }

    fun undoMatch(matchId: Long) {
        val foo: Any = eloChanges.findAllByMatch_Id(matchId)
        println(foo)
    }
}
