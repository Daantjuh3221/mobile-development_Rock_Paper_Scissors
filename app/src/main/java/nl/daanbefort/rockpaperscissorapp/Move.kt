package nl.daanbefort.rockpaperscissorsapp

import nl.daanbefort.rockpaperscissorapp.R
import java.util.*

enum class Move(val image : Int) {
    ROCK(R.drawable.rock),
    PAPER(R.drawable.paper),
    SCISSOR(R.drawable.scissors);

    companion object {
        fun getRandomMove() : Move {
            val random = Random()
            return values()[random.nextInt(values().size)]
        }

        fun winner(playerMove : Move, cpuMove : Move) : Result {
            when(playerMove) {
                ROCK -> {
                    if (cpuMove.equals(SCISSOR))
                        return Result.WIN
                    if (cpuMove.equals(PAPER))
                        return Result.LOSE
                    return Result.DRAW
                }
                PAPER -> {
                    if (cpuMove.equals(ROCK))
                        return Result.WIN
                    if (cpuMove.equals(SCISSOR))
                        return Result.LOSE
                    return Result.DRAW
                }
                SCISSOR -> {
                    if (cpuMove.equals(PAPER))
                        return Result.WIN
                    if (cpuMove.equals(ROCK))
                        return Result.LOSE
                    return Result.DRAW
                }
            }
        }

    }
}