package nl.daanbefort.rockpaperscissorapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.daanbefort.rockpaperscissorapp.R
import nl.daanbefort.rockpaperscissorapp.database.RockPaperScissorRepository
import nl.daanbefort.rockpaperscissorapp.model.Game
import nl.daanbefort.rockpaperscissorsapp.Move
import java.util.*

const val SHOW_HISTORY = 100

class MainActivity : AppCompatActivity() {

    private lateinit var rockPaperScissorRepository: RockPaperScissorRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        rockPaperScissorRepository = RockPaperScissorRepository(this)
        initViews()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_show_history -> {
                startHistoryActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            SHOW_HISTORY -> {
                updateStatistics()
            }
        }
    }

    private fun initViews() {
        imgRock.setOnClickListener {
            playGame(Move.ROCK)
        }

        imgPaper.setOnClickListener {
            playGame(Move.PAPER)
        }

        imgScissor.setOnClickListener {
            playGame(Move.SCISSOR)
        }
        updateStatistics()
    }

    private fun updateStatistics() {
        mainScope.launch {
            val wins = rockPaperScissorRepository.countWins()
            val loses = rockPaperScissorRepository.countLoses()
            val draws = rockPaperScissorRepository.countDraws()
            txtStatistics.setText("Wins: ${wins} Loses: ${loses} Draws: ${draws}")
        }
    }

    private fun playGame(playerMove : Move) {
        imgPlayer.setImageResource(playerMove.image)
        val cpuMove = Move.getRandomMove();
        imgCpu.setImageResource(cpuMove.image)

        val result = Move.winner(playerMove, cpuMove);
        txtResultDisplay.setText(result.message)

        val game = Game(date = Date(),
            cpuMove = cpuMove,
            playerMove = playerMove,
            result = result)

        mainScope.launch {
            rockPaperScissorRepository.insertGame(game)
            updateStatistics()
        }
    }

    private fun startHistoryActivity() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivityForResult(intent, SHOW_HISTORY)
    }
}
