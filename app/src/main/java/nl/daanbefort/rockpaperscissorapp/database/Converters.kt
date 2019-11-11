package nl.daanbefort.rockpaperscissorapp.database

import androidx.room.TypeConverter
import nl.daanbefort.rockpaperscissorsapp.Move
import nl.daanbefort.rockpaperscissorsapp.Result
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromStringToMove(move: String): Move {
        return Move.valueOf(move)
    }

    @TypeConverter
    fun moveToString(move: Move): String {
        return move.name
    }

    @TypeConverter
    fun fromStringToResult(result: String): Result {
        return Result.valueOf(result)
    }

    @TypeConverter
    fun resultToString(result: Result): String {
        return result.name
    }
}