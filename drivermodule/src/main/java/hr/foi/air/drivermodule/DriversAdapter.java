package hr.foi.air.drivermodule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import entities.DriverModel;

import static android.R.attr.button;
import static android.R.attr.y;

/**
 * Created by Ivan on 26.10.2017..
 */

public class DriversAdapter extends RecyclerView.Adapter<ViewHolderTilesOfList> {
    // Za lagano dohvaćanje liste vozača
    private List<DriverModel> mDrivers;
    // Za lagano dohvaćanje konteksta
    private Context mContext;
    // Zastavica koja predstavlja layout prikaza - list(0) i grid(1)
    private int mViewType;

    // U konstruktoru adaptera se prosljeđuje lista vozača - za sada su testni primjeri
    public DriversAdapter(Context context,  List<DriverModel> drivers, int viewType) {
        mDrivers = drivers;
        mContext = context;
        mViewType = viewType;
    }

    @Override
    public ViewHolderTilesOfList onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View driverView;

        if (mViewType == 0) {
            driverView = inflater.inflate(R.layout.item_driver_tile, parent, false);
        }
        else {
            driverView = inflater.inflate(R.layout.item_driver_list, parent, false);
        }
        return new ViewHolderTilesOfList(driverView);
    }

    @Override
    public void onBindViewHolder(ViewHolderTilesOfList holder, int position) {
        holder.driverName.setText(mDrivers.get(position).getIme()+" "+mDrivers.get(position).getPrezime());
        holder.driverImage.setImageResource(R.drawable.test01);
        holder.dView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("App", "clicked");
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mDrivers == null) return 0;

        return mDrivers.size();
    }
}
