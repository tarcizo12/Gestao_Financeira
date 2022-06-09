package adapter

import Entitys.HistoryData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Activitys.R

class HistoryAdapter(val list: List<HistoryData>):RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class  HistoryViewHolder(view: View):RecyclerView.ViewHolder(view) { //view representa o history_cardview
        val historyName:TextView
        val historyText:TextView
        val historyValue:TextView

        init {
            historyName = view.findViewById(R.id.historyCardViewName)
            historyText = view.findViewById(R.id.historyCardViewText)
            historyValue= view.findViewById(R.id.historyCardViewValue)
        }
    }

    //cria o layout de cada linha
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_cardview, parent, false)
        val holder = HistoryViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.historyText.text = list[position].text
        holder.historyName.text = list[position].name
        holder.historyValue.text = list[position].value.toString()
    }

    //quantos itens tem a lista
    override fun getItemCount(): Int {
        return list.size
    }
}