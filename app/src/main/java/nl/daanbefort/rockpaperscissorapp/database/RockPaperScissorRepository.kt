package nl.daanbefort.rockpaperscissorapp.database

import android.content.Context
import nl.daanbefort.rockpaperscissorapp.model.Game

class RockPaperScissorRepository(context: Context) {

    private val rockPaperScissorDao: RockPaperScissorDao

    init {
        val database =
            RockPaperScissorRoomDatabase.getDatabase(
                context
            )
        rockPaperScissorDao = database!!.rockPaperScissorDao()
    }

    suspend fun getAllGames(): List<Game> {
        return rockPaperScissorDao.getAllGames()
    }

    suspend fun countWins(): Int {
        return rockPaperScissorDao.countWins()
    }

    suspend fun countLoses(): Int {
        return rockPaperScissorDao.countLoses()
    }

    suspend fun countDraws(): Int {
        return rockPaperScissorDao.countDraws()
    }

    suspend fun insertGame(game: Game) {
        rockPaperScissorDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        rockPaperScissorDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        rockPaperScissorDao.deleteAllGames()
    }

}