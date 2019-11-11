package nl.daanbefort.rockpaperscissorapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.daanbefort.rockpaperscissorapp.R
import nl.daanbefort.rockpaperscissorapp.database.RockPaperScissorRepository
import nl.daanbefort.rockpaperscissorapp.model.Game

class HistoryActivity : AppCompatActivity() {

    private val games = arrayListOf<Game>()
    private val historyAdapter = HistoryAdapter(games)

    private lateinit var rockPaperScissorRepository: RockPaperScissorRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        rockPaperScissorRepository = RockPaperScissorRepository(this)
        initViews()
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        rvHistory.layoutManager =
            LinearLayoutManager(this@HistoryActivity, RecyclerView.VERTICAL, false)
        rvHistory.adapter = historyAdapter
        rvHistory.addItemDecoration(
            DividerItemDecoration(
                this@HistoryActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        getHistory()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> { // Used to identify when the user has clicked the back button
                finish()
                true
            }
            R.id.action_delete_history -> {
                removeHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getHistory() {
        mainScope.launch {
            val games = rockPaperScissorRepository.getAllGames()

            this@HistoryActivity.games.clear()
            this@HistoryActivity.games.addAll(games)
            historyAdapter.notifyDataSetChanged()
        }
    }

    fun removeHistory() {
        mainScope.launch {
            val games = rockPaperScissorRepository.deleteAllGames()
            this@HistoryActivity.games.clear()
            historyAdapter.notifyDataSetChanged()
        }
    }

}
