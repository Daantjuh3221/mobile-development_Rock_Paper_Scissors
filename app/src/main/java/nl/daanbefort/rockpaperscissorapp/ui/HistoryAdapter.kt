package nl.daanbefort.rockpaperscissorapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_item.view.*
import nl.daanbefort.rockpaperscissorapp.R
import nl.daanbefort.rockpaperscissorapp.model.Game

class HistoryAdapter(private val games: List<Game>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        )
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(games[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.txtHistoryDate.text = game.date.toString()
            itemView.txtHistoryResult.text = game.result.message
            itemView.imgHistoryCpu.setImageResource(game.cpuMove.image)
            itemView.imgHistoryPlayer.setImageResource(game.playerMove.image)
        }
    }
}