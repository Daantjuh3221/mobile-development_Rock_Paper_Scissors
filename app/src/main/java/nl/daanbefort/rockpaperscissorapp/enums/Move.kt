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
                    if (cpuMove == SCISSOR)
                        return Result.WIN
                    if (cpuMove == PAPER)
                        return Result.LOSE
                    return Result.DRAW
                }
                PAPER -> {
                    if (cpuMove == ROCK)
                        return Result.WIN
                    if (cpuMove == SCISSOR)
                        return Result.LOSE
                    return Result.DRAW
                }
                SCISSOR -> {
                    if (cpuMove == PAPER)
                        return Result.WIN
                    if (cpuMove == ROCK)
                        return Result.LOSE
                    return Result.DRAW
                }
            }
        }

    }
}