package hr.foi.air.trucktrack.Adapters;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import entities.JobModel;
import entities.RouteModel;
import hr.foi.air.trucktrack.DriverJobsFragment;
import hr.foi.air.trucktrack.Interface.CustomDialog;
import hr.foi.air.trucktrack.R;
import hr.foi.air.trucktrack.ViewHolders.ChildViewHolder;
import hr.foi.air.trucktrack.ViewHolders.ParentViewHolder;

/**
 * Created by Ivan on 1.12.2017..
 */

public class JobListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Object> dataOfTheList = null;
    String mTipPrikaza = "";
    Fragment contextAct;
    CustomDialog customDialog;
    final int DIALOG_DELETE_JOB = 100;
    final int DIALOG_SAVE_JOB = 200;
    final int DIALOG_SET_DONE = 400;
    final int DIALOG_ACK_TO_JOB = 500;
    final int JOB_ACK = 1;

    public JobListAdapter(ArrayList<Object> data, String tip, Fragment context, CustomDialog customDialog) {
        dataOfTheList = data;
        mTipPrikaza = tip;
        contextAct = context;
        this.customDialog = customDialog;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View v;
            if (mTipPrikaza == "Vozac") {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_driver_job, parent, false);
            } else {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_disponent_job, parent, false);
            }
            return new ParentViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_driver_job, parent, false);
            return new ChildViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final View parent = holder.itemView;
         /*DISPONENT-1JOB
        * ako se radi o prvom poslu koji je prihvaćen tada mora promjeniti background - dokumentacija za detalje
        * Čekanje potvrde mora biti boldano
        * */

        if (holder instanceof ParentViewHolder) {
            if (mTipPrikaza == "Vozac") {
                ImageView button1 = (ImageView) parent.findViewById(R.id.btnACKJob);
                ImageView button2 = (ImageView) parent.findViewById(R.id.btnSetDoneJob);
                if (((RouteModel) dataOfTheList.get(position)).getButton() == DIALOG_SET_DONE) {//done
                    button1.setVisibility(View.GONE);
                    button2.setVisibility(View.VISIBLE);
                } else {//accept
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.GONE);
                }
            }

            String datum2 = ((RouteModel) dataOfTheList.get(position)).getIstovarDatum().toString();
            ((TextView) parent.findViewById(R.id.datumi)).setText(datum2);
            ((TextView) parent.findViewById(R.id.datumi)).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            ((TextView) parent.findViewById(R.id.utovar)).setText(((RouteModel) dataOfTheList.get(position)).getMjestoUtovara().toString());
            ((TextView) parent.findViewById(R.id.istovar)).setText(((RouteModel) dataOfTheList.get(position)).getMjestoIstovara());
            ((TextView) parent.findViewById(R.id.status)).setText(((RouteModel) dataOfTheList.get(position)).getStatus().toString());


             if (((RouteModel) dataOfTheList.get(position)).getStatusId() == JOB_ACK || ((RouteModel) dataOfTheList.get(position)).getStatusId().equals(JOB_ACK)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    parent.setBackgroundColor(contextAct.getResources().getColor(R.color.colorACK, contextAct.getContext().getTheme()));
                } else {
                    parent.setBackgroundColor(contextAct.getResources().getColor(R.color.colorACK));
                }

                setColorWhite((ViewGroup) parent);
            } else {
                 parent.setBackgroundColor(0x00000000);
                 setColorBlack((ViewGroup) parent);
             }

            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<JobModel> jobs = ((RouteModel) dataOfTheList.get(position)).getPoslovi();
                    if (parent.isActivated()) {
                        removeFrom(position, jobs.size());
                    } else {
                        addInFrontOf(position, jobs);
                    }
                    parent.setActivated(!parent.isActivated());
                    notifyDataSetChanged();
                }
            });

            if (contextAct instanceof DriverJobsFragment) {
                parent.findViewById(R.id.btnMapShow).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((DriverJobsFragment) contextAct).clickedOnMap(3);
                    }
                });

                parent.findViewById(R.id.btnSetDoneJob).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int idRute = ((RouteModel) dataOfTheList.get(position)).getIdRuta();
                        customDialog.showCustomDialog(DIALOG_SET_DONE, idRute);
                    }
                });

                parent.findViewById(R.id.btnACKJob).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int idRute = ((RouteModel) dataOfTheList.get(position)).getIdRuta();
                        customDialog.showCustomDialog(DIALOG_ACK_TO_JOB, idRute);
                    }
                });

            } else {
                parent.findViewById(R.id.btnDeleteJob).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int idRute = ((RouteModel) dataOfTheList.get(position)).getIdRuta();
                        customDialog.showCustomDialog(DIALOG_DELETE_JOB, idRute);
                    }
                });

                parent.findViewById(R.id.btnEditJob).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int idRute = ((RouteModel) dataOfTheList.get(position)).getIdRuta();
                        customDialog.showCustomDialog(DIALOG_SAVE_JOB, idRute);
                    }
                });
            }

        } else {
            ((TextView) parent.findViewById(R.id.poslovi)).setText(((JobModel) dataOfTheList.get(position)).getMjestoIstovara());
        }
    }

    @Override
    public int getItemCount() {
        if (dataOfTheList != null)
            return dataOfTheList.size();
        else return 0;
    }


    @Override
    public int getItemViewType(int position) {
        if (dataOfTheList.get(position) instanceof RouteModel) return 1;
        else return 0;
    }

    public void addInFrontOf(int position, List<JobModel> jobs) {
        for (int i = 0; i < jobs.size(); i++)
            dataOfTheList.add(position + i + 1, jobs.get(i));
    }

    public void removeFrom(int position, int numOfJobs) {
        for (int i = 0; i < numOfJobs; i++)
            dataOfTheList.remove(position + 1);
    }

    public void setColorWhite(ViewGroup view) {
        for (int i = 0; i < view.getChildCount(); i++) {
            View linear = view.getChildAt(i);
            for (int j = 0; j < ((ViewGroup) linear).getChildCount(); j++) {
                View child = ((ViewGroup) linear).getChildAt(j);
                if (child instanceof TextView) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ((TextView) child).setTextColor(contextAct.getResources().getColor(R.color.textbody, contextAct.getContext().getTheme()));
                    } else {
                        ((TextView) child).setTextColor(contextAct.getResources().getColor(R.color.textbody));
                    }
                } else if (child instanceof ImageView) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ((ImageView) child).setColorFilter(contextAct.getResources().getColor(R.color.textbody, contextAct.getContext().getTheme()));
                    } else {
                        ((ImageView) child).setColorFilter(contextAct.getResources().getColor(R.color.textbody));
                    }
                }
            }
        }
    }

    public void setColorBlack(ViewGroup view) {
        for (int i = 0; i < view.getChildCount(); i++) {
            View linear = view.getChildAt(i);
            for (int j = 0; j < ((ViewGroup) linear).getChildCount(); j++) {
                View child = ((ViewGroup) linear).getChildAt(j);
                if (child instanceof TextView) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ((TextView) child).setTextColor(contextAct.getResources().getColor(R.color.defaultTextColor, contextAct.getContext().getTheme()));
                    } else {
                        ((TextView) child).setTextColor(contextAct.getResources().getColor(R.color.defaultTextColor));
                    }
                } else if (child instanceof ImageView) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ((ImageView) child).setColorFilter(contextAct.getResources().getColor(R.color.defaultTextColor, contextAct.getContext().getTheme()));
                    } else {
                        ((ImageView) child).setColorFilter(contextAct.getResources().getColor(R.color.defaultTextColor));
                    }
                }
            }
        }
    }
}

