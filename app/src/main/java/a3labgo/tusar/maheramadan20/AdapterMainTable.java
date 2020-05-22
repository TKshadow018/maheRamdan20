package a3labgo.tusar.maheramadan20;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;

public class AdapterMainTable extends RecyclerView.Adapter<AdapterMainTable.ViewHolder> {
    private Context context;
    private ArrayList<String> roza_no, day, date, sahri, fazar, iftar;
    private String[] systemDayOfMonth = {"১","২","৩","৪","৫","৬","৭","৮","৯","১০","১১","১২","১৩","১৪","১৫","১৬","১৭","১৮","১৯","২০","২১","২২","২৩","২৪","২৫","২৬","২৭","২৮","২৯","৩০","৩১"};
    private String[] systemMonth = { "জানুয়ারী", "ফেব্রুয়ারী", "মার্চ", "এপ্রিল", "মে", "জুন", "জুলাই", "আগস্ট", "সেপ্টেম্বর", "অক্টোবর", "নভেম্বর", "ডিসেম্বর"};

    AdapterMainTable(ArrayList<String> roza_no, ArrayList<String> day, ArrayList<String> date, ArrayList<String> sahri, ArrayList<String> fazar, ArrayList<String> iftar, int[] systemDate, Context context) {
        this.roza_no = roza_no;
        this.day = day;
        this.date = date;
        this.sahri = sahri;
        this.fazar = fazar;
        this.iftar = iftar;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_table_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMainTable.ViewHolder holder, int position) {
        holder.roza_no.setText(roza_no.get(position));
        holder.day.setText(date.get(position));
        holder.date.setText(day.get(position%7));
        holder.sahri.setText(sahri.get(position));
        holder.fazar.setText(fazar.get(position));
        holder.iftar.setText(iftar.get(position));

        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.DAY_OF_MONTH) == getDay(position) && calendar.get(Calendar.MONTH) == getMonth(position)){
//        if(16 == getDay(position) && 4 == getMonth(position)){//test
            Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
            holder.roza_no.setBackgroundResource(R.drawable.border2);
            holder.roza_no.setTextColor(Color.parseColor("#aa2300"));
            holder.roza_no.setTypeface(boldTypeface);
            holder.day.setBackgroundResource(R.drawable.border2);
            holder.day.setTextColor(Color.parseColor("#aa2300"));
            holder.day.setTypeface(boldTypeface);
            holder.date.setBackgroundResource(R.drawable.border2);
            holder.date.setTextColor(Color.parseColor("#aa2300"));
            holder.date.setTypeface(boldTypeface);
            holder.sahri.setBackgroundResource(R.drawable.border2);
            holder.sahri.setTextColor(Color.parseColor("#aa2300"));
            holder.sahri.setTypeface(boldTypeface);
            holder.fazar.setBackgroundResource(R.drawable.border2);
            holder.fazar.setTextColor(Color.parseColor("#aa2300"));
            holder.fazar.setTypeface(boldTypeface);
            holder.iftar.setBackgroundResource(R.drawable.border2);
            holder.iftar.setTextColor(Color.parseColor("#aa2300"));
            holder.iftar.setTypeface(boldTypeface);
            holder.setIsRecyclable(false);
        }
        else {
            holder.roza_no.setBackgroundResource(R.drawable.border);
            holder.roza_no.setTextColor(context.getResources().getColor(R.color.main_font));
            holder.day.setBackgroundResource(R.drawable.border);
            holder.day.setTextColor(context.getResources().getColor(R.color.main_font));
            holder.date.setBackgroundResource(R.drawable.border);
            holder.date.setTextColor(context.getResources().getColor(R.color.main_font));
            holder.sahri.setBackgroundResource(R.drawable.border);
            holder.sahri.setTextColor(context.getResources().getColor(R.color.main_font));
            holder.fazar.setBackgroundResource(R.drawable.border);
            holder.fazar.setTextColor(context.getResources().getColor(R.color.main_font));
            holder.iftar.setBackgroundResource(R.drawable.border);
            holder.iftar.setTextColor(context.getResources().getColor(R.color.main_font));
        }

    }

    private int getDay(int position) {
        int day = 1;
        for(int i = 0; i<31; i++) {
            if (date.get(position).split("\\s+")[0].equals(systemDayOfMonth[i]))
                day = i+1;
        }
        return day;
    }
    private int getMonth(int position) {
        int month = 1;
        for(int i = 0; i<12; i++){
            if(date.get(position).split("\\s+")[1].equals(systemMonth[i]))
                month = i;
        }
        return month;
    }


    @Override
    public int getItemCount() {
        return roza_no.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView roza_no, day, date, sahri, fazar, iftar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            roza_no = itemView.findViewById(R.id.roza_no);
            day = itemView.findViewById(R.id.day);
            date = itemView.findViewById(R.id.date);
            sahri = itemView.findViewById(R.id.sahri);
            fazar = itemView.findViewById(R.id.fazar);
            iftar = itemView.findViewById(R.id.iftar);
        }
    }
}





