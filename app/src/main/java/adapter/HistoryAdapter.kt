package adapter

import Entitys.HistoryData
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.Activitys.R
import kotlin.math.log

class HistoryAdapter(val list: List<HistoryData>):RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class  HistoryViewHolder(view: View):RecyclerView.ViewHolder(view) { //view representa o history_cardview
        val historyName:TextView = view.findViewById(R.id.historyCardViewName)
        val historyValue:TextView = view.findViewById(R.id.historyCardViewValue)
        val historyBackground: ConstraintLayout = view.findViewById(R.id.backgroud)

    }

    //cria o layout de cada linha
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_cardview, parent, false)

        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val formattedNumber = String.format("%.2f", list[position].value.replace(',','.').toDouble())

        holder.historyName.text = list[position].name
        holder.historyValue.text = formattedNumber

        if(list[position].typeValue) {
            holder.historyName.setTextColor(Color.GREEN)
            holder.historyValue.setTextColor(Color.GREEN)
        }else{
            holder.historyName.setTextColor(Color.RED)
            holder.historyValue.setTextColor(Color.RED)
        }
    }

    //quantos itens tem a lista
    override fun getItemCount(): Int {
        return list.size
    }
}