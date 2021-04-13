package a3labgo.tusar.maheramadan20

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class AdapterMainTable (private val context: Context) : RecyclerView.Adapter<AdapterMainTable.ViewHolder>() {
    private val systemDayOfMonth = arrayOf("১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯", "১০", "১১", "১২", "১৩", "১৪", "১৫", "১৬", "১৭", "১৮", "১৯", "২০", "২১", "২২", "২৩", "২৪", "২৫", "২৬", "২৭", "২৮", "২৯", "৩০", "৩১")
    private val systemMonth = arrayOf("জানুয়ারী", "ফেব্রুয়ারী", "মার্চ", "এপ্রিল", "মে", "জুন", "জুলাই", "আগস্ট", "সেপ্টেম্বর", "অক্টোবর", "নভেম্বর", "ডিসেম্বর")

    private var roza_no: ArrayList<String> = ArrayList()
    private var day: ArrayList<String> = ArrayList()
    private var date: ArrayList<String> = ArrayList()
    private var sahri: ArrayList<String> = ArrayList()
    private var fazar: ArrayList<String> = ArrayList()
    private var iftar: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_table_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.roza_no.text = roza_no[position]
        holder.day.text = date[position]
        holder.date.text = day[position % 7]
        holder.sahri.text = sahri[position]
        holder.fazar.text = fazar[position]
        holder.iftar.text = iftar[position]
        val calendar = Calendar.getInstance()
        if (calendar[Calendar.DAY_OF_MONTH] == getDay(position) && calendar[Calendar.MONTH] == getMonth(position)) {
//        if(16 == getDay(position) && 4 == getMonth(position)){//test
            val boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD)
            holder.roza_no.setBackgroundResource(R.drawable.border2)
            holder.roza_no.setTextColor(Color.parseColor("#aa2300"))
            holder.roza_no.typeface = boldTypeface
            holder.day.setBackgroundResource(R.drawable.border2)
            holder.day.setTextColor(Color.parseColor("#aa2300"))
            holder.day.typeface = boldTypeface
            holder.date.setBackgroundResource(R.drawable.border2)
            holder.date.setTextColor(Color.parseColor("#aa2300"))
            holder.date.typeface = boldTypeface
            holder.sahri.setBackgroundResource(R.drawable.border2)
            holder.sahri.setTextColor(Color.parseColor("#aa2300"))
            holder.sahri.typeface = boldTypeface
            holder.fazar.setBackgroundResource(R.drawable.border2)
            holder.fazar.setTextColor(Color.parseColor("#aa2300"))
            holder.fazar.typeface = boldTypeface
            holder.iftar.setBackgroundResource(R.drawable.border2)
            holder.iftar.setTextColor(Color.parseColor("#aa2300"))
            holder.iftar.typeface = boldTypeface
            holder.setIsRecyclable(false)
        } else {
            holder.roza_no.setBackgroundResource(R.drawable.border)
            holder.roza_no.setTextColor(context.resources.getColor(R.color.main_font))
            holder.day.setBackgroundResource(R.drawable.border)
            holder.day.setTextColor(context.resources.getColor(R.color.main_font))
            holder.date.setBackgroundResource(R.drawable.border)
            holder.date.setTextColor(context.resources.getColor(R.color.main_font))
            holder.sahri.setBackgroundResource(R.drawable.border)
            holder.sahri.setTextColor(context.resources.getColor(R.color.main_font))
            holder.fazar.setBackgroundResource(R.drawable.border)
            holder.fazar.setTextColor(context.resources.getColor(R.color.main_font))
            holder.iftar.setBackgroundResource(R.drawable.border)
            holder.iftar.setTextColor(context.resources.getColor(R.color.main_font))
        }
    }

    private fun getDay(position: Int): Int {
        var day = 1
        for (i in 0..30) {
            if (date[position].split("\\s+").toTypedArray()[0] == systemDayOfMonth[i]) day = i + 1
        }
        return day
    }

    private fun getMonth(position: Int): Int {
        var month = 1
        for (i in 0..11) {
            if (date[position].split("\\s+").toTypedArray()[1] == systemMonth[i]) month = i
        }
        return month
    }

    override fun getItemCount(): Int {
        println(roza_no)
        return roza_no.size
    }

    public fun setData(roza_no: ArrayList<String>,day: ArrayList<String>,date: ArrayList<String>,sahri: ArrayList<String>,fazar: ArrayList<String>,iftar: ArrayList<String>){
        this.roza_no = roza_no
        this.day = day
        this.date = date
        this.sahri = sahri
        this.fazar = fazar
        this.iftar = iftar
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var roza_no: TextView = itemView.findViewById(R.id.roza_no)
        var day: TextView = itemView.findViewById(R.id.day)
        var date: TextView = itemView.findViewById(R.id.date)
        var sahri: TextView = itemView.findViewById(R.id.sahri)
        var fazar: TextView = itemView.findViewById(R.id.fazar)
        var iftar: TextView = itemView.findViewById(R.id.iftar)
    }
}