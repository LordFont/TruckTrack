package hr.foi.air.drivermodule;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ssajcic on 02.11.17..
 */

public class ViewHolderTilesOfList extends RecyclerView.ViewHolder {

    public ImageView driverImage;
    public TextView driverName;
    public View dView;

    public ViewHolderTilesOfList(View itemView) {
        super(itemView);
        dView=itemView;
        driverImage = dView.findViewById(R.id.driver_image);
        driverName = dView.findViewById(R.id.info_text);
    }
}
