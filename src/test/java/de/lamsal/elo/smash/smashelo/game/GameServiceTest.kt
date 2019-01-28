package de.lamsal.elo.smash.smashelo.game

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import de.lamsal.elo.smash.smashelo.model.EloChangeEvent
import de.lamsal.elo.smash.smashelo.model.Player
import de.lamsal.elo.smash.smashelo.repository.EloChangeEventRepository
import de.lamsal.elo.smash.smashelo.repository.MatchRepo
import de.lamsal.elo.smash.smashelo.repository.PlayerRepository
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class GameServiceTest {

    @Test
    fun shouldSetLoserAsOtherPersonInHistory_whenPlayer1IsWinner() {
        val gameService = GameServiceMock(Player("player1"), Player("player2"))
        assertEquals("player2", gameService.getPlayerHistory("player1")//[0] = game where p1 won
                [0]["other_player_name"])
    }

    @Test
    fun shouldSetWinnerAsOtherPersonInHistory_whenPlayer1IsLoser() {
        val gameService = GameServiceMock(Player("player1"), Player("player2"))
        assertEquals("player2", gameService.getPlayerHistory("player1")//[0] = game where p1 won
                [1]["other_player_name"])
    }

    @Test
    fun shouldCreateEventForWinnerWithHigherEloAfter() {
        val gameService = GameServiceMock(Player("player1"), Player("player2"))
        val winnerChangeEvent = gameService.updatePlayerElos(gameService.player1, 0.5, winnerWeight)
        assertEquals(1600, winnerChangeEvent.eloBefore)
        assertTrue(1600 < winnerChangeEvent.eloAfter)
    }

    @Test
    fun shouldCreateEventForLoserWithLowerEloAfter() {
        val gameService = GameServiceMock(Player("player1"), Player("player2"))
        val winnerChangeEvent = gameService.updatePlayerElos(gameService.player1, 0.5, loserWeight)
        assertEquals(1600, winnerChangeEvent.eloBefore)
        assertTrue(1600 > winnerChangeEvent.eloAfter)
    }

    @Test
    fun shouldSetElosAccordingly() {
        val player1 = Player("player1")
        val player2 = Player("player2")
        GameServiceMock(player1, player2).matchByIDs(player1.name, player2.name)

        assertTrue(player1.elo > 1600)
        assertTrue(player2.elo < 1600)
    }

    class GameServiceMock(val player1 : Player, val player2 : Player) : GameService() {
        private val player1history : List<Array<Any>> = listOf(
                arrayOf("player1", 1600, 1601, "timestamp1", "player1", "player2"),
                arrayOf("player1", 1600, 1599, "timestamp2", "player2", "player1"))
        override var matches = mock<MatchRepo>()
        override var eloChanges = mock<EloChangeEventRepository>()
        override var players = mock<PlayerRepository> {
            on { findPlayerByName("player1") } doReturn Optional.of(player1)
            on { findPlayerByName("player2") } doReturn Optional.of(player2)
            on { findPlayerHistory("player1") } doReturn player1history
        }
    }
}