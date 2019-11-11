package nl.daanbefort.rockpaperscissorapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import nl.daanbefort.rockpaperscissorsapp.Move
import nl.daanbefort.rockpaperscissorsapp.Result
import java.util.*

@Parcelize
@Entity(tableName = "game_table")
data class Game(

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "cpuMove")
    var cpuMove: Move,

    @ColumnInfo(name = "playerMove")
    var playerMove: Move,

    @ColumnInfo(name = "result")
    var result: Result,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

) : Parcelable