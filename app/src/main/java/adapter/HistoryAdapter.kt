package adapter

import Entitys.HistoryData
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(list: List<HistoryData>) {

    class  HistoryViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val historyName:TextView
        val historyText:TextView
        val historyValue:TextView
        init {
            historyName = view.findViewById()
            historyText = view.findViewById()
            historyValue= view.findViewById()
        }
    }
}