package a3labgo.tusar.maheramadan20;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;

public class AdapterMainTable extends RecyclerView.Adapter<AdapterMainTable.ViewHolder> {
    Context context;
    String[] roza_no, day, date, sahri, fazar, iftar;
    int[] systemDate = new int[2];
    String systemDayOfMonth[] = {"১","২","৩","৪","৫","৬","৭","৮","৯","১০","১১","১২","১৩","১৪","১৫","১৬","১৭","১৮","১৯","২০","২১","২২","২৩","২৪","২৫","২৬","২৭","২৮","২৯","৩০","৩১"};
    String systemMonth[] = { "জানুয়ারী", "ফেব্রুয়ারী", "মার্চ", "এপ্রিল", "মে", "জুন", "জুলাই", "আগস্ট", "সেপ্টেম্বর", "অক্টোবর", "নভেম্বর", "ডিসেম্বর"};

    public AdapterMainTable(String[] roza_no, String[] day, String[] date, String[] sahri, String[] fazar, String[] iftar, int systemDate[], Context context) {
        this.roza_no = roza_no;
        this.day = day;
        this.date = date;
        this.sahri = sahri;
        this.fazar = fazar;
        this.iftar = iftar;
        this.systemDate = systemDate;
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
        holder.roza_no.setText(roza_no[position]);
        holder.day.setText(day[position%7]);
        holder.date.setText(date[position]);
        holder.sahri.setText(sahri[position]);
        holder.fazar.setText(fazar[position]);
        holder.iftar.setText(iftar[position]);

        Calendar calendar = Calendar.getInstance();
//        System.out.println("day = " + calendar.get(Calendar.DAY_OF_MONTH) + " - month = " + calendar.get(Calendar.MONTH));
        if(calendar.get(Calendar.DAY_OF_MONTH) == getDay(position) && calendar.get(Calendar.MONTH) == getMonth(position)){
//        if(16 == getDay(position) && 4 == getMonth(position)){//test
            holder.roza_no.setBackgroundResource(R.drawable.border2);
            holder.roza_no.setTextColor(Color.parseColor("#aa2300"));
            holder.day.setBackgroundResource(R.drawable.border2);
            holder.day.setTextColor(Color.parseColor("#aa2300"));
            holder.date.setBackgroundResource(R.drawable.border2);
            holder.date.setTextColor(Color.parseColor("#aa2300"));
            holder.sahri.setBackgroundResource(R.drawable.border2);
            holder.sahri.setTextColor(Color.parseColor("#aa2300"));
            holder.fazar.setBackgroundResource(R.drawable.border2);
            holder.fazar.setTextColor(Color.parseColor("#aa2300"));
            holder.iftar.setBackgroundResource(R.drawable.border2);
            holder.iftar.setTextColor(Color.parseColor("#aa2300"));
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
            if (date[position].split("\\s+")[0].equals(systemDayOfMonth[i]))
                day = i+1;
        }
        return day;
    }
    private int getMonth(int position) {
        int month = 1;
        for(int i = 0; i<12; i++){
            if(date[position].split("\\s+")[1].equals(systemMonth[i]))
                month = i;
        }
        return month;
    }


    @Override
    public int getItemCount() {
        return roza_no.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView roza_no, day, date, sahri, fazar, iftar;

        public ViewHolder(@NonNull View itemView) {
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





