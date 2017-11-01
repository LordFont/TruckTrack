package hr.foi.air.drivermodule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Driver;
import java.util.List;

import static android.R.attr.button;
import static android.R.attr.y;

/**
 * Created by Ivan on 26.10.2017..
 */

public class DriversAdapter extends RecyclerView.Adapter<DriversAdapter.ViewHolder> {
    // ovdje se nalazi varijabla te interface za listener, ne moramo ga implementirati vec
    //ga definiramo unuter driver adaptera
    private OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Omogućuje direktan pristup svakom pogledu zajedno sa članovima toga pogleda, odnosno itemima
    // Sprema poglede zajedno sa itemima i njihovim layoutom
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView nameImageView;
        public TextView infoAboutDriver;

        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameImageView = (ImageView) itemView.findViewById(R.id.driver_image);
            infoAboutDriver = (TextView) itemView.findViewById(R.id.info_text);

            //ovdje smo dodali click listener za prepoznavanje klika
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public ImageView nameImageView;
        public TextView infoAboutDriver;

        public ListViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameImageView = (ImageView) itemView.findViewById(R.id.driver_image);
            infoAboutDriver = (TextView) itemView.findViewById(R.id.info_text);

            //ovdje smo dodali click listener za prepoznavanje klika
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    // Za lagano dohvaćanje liste vozača
    private List<String> mDrivers;
    // Za lagano dohvaćanje konteksta
    private Context mContext;
    // Zastavica koja predstavlja layout prikaza - list(0) i grid(1)
    private int mViewType;

    // U konstruktoru adaptera se prosljeđuje lista vozača - za sada su testni primjeri
    public DriversAdapter(Context context, List<String> drivers, int viewType) {
        mDrivers = drivers;
        mContext = context;
        mViewType = viewType;
    }

    // Dohvat kontekst objekta u recyclerViewu - mozda ce trebati kasnije, za sad ne treba
    private Context getContext() {
        return mContext;
    }

    // Metoda koja uključuje integraciju layouta iz xml-a te vraća instancu holdera
    @Override
    public DriversAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Integrira layout za item
        View driverView = inflater.inflate(R.layout.item_driver_tile, parent, false);

        // Vraća novu instancu holdera.
        ViewHolder viewHolder = new ViewHolder(driverView);
        return viewHolder;
    }

    // Ova metoda ukljucuje ispunjavanje itema podacima
    @Override
    public void onBindViewHolder(DriversAdapter.ViewHolder viewHolder, int position) {
        // Dohvaća odgovarajući podatkovni objekt na temelju pozicije koja je prosljeđena
        String driver = mDrivers.get(position);

        // Postavlja iteme iz liste na temelju data modela
        ImageView imageView = viewHolder.nameImageView;
        imageView.setImageResource(R.drawable.test01);
        TextView textView = viewHolder.infoAboutDriver;
        textView.setText(driver);
    }

    // Vraća broj itema u listi
    @Override
    public int getItemCount() {
        return mDrivers.size();
    }
}
