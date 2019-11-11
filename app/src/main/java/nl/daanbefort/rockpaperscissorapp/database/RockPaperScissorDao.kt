package nl.daanbefort.rockpaperscissorapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import nl.daanbefort.rockpaperscissorapp.model.Game

@Dao
interface RockPaperScissorDao {
    @Query("SELECT * FROM game_table")
    suspend fun getAllGames(): List<Game>

    @Query("SELECT COUNT(*) FROM game_table WHERE result == 'WIN'")
    suspend fun countWins(): Int

    @Query("SELECT COUNT(*) FROM game_table WHERE result == 'LOSE'")
    suspend fun countLoses(): Int

    @Query("SELECT COUNT(*) FROM game_table WHERE result == 'DRAW'")
    suspend fun countDraws(): Int

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM game_table")
    suspend fun deleteAllGames()
}
