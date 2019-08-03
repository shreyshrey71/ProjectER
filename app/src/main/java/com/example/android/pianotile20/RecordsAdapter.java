package com.example.android.pianotile20;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.RecordsViewHolder> {

    private List<String> division = new Vector<String>();
    private List<String> rate = new Vector<String>();
    private List<String> time = new Vector<String>();
    private List<String> counter = new Vector<String>();
    private List<String> mode = new Vector<String>();
    private List<String> recording = new Vector<String>();
    private Context context;
    public RecordsAdapter(Context context,List<String>division,List<String> rate, List<String> time,List<String> counter,List<String> mode,List<String> recording) {
        this.context=context;
        this.division = division;
        this.rate=rate;
        this.time=time;
        this.counter=counter;
        this.mode=mode;
        this.recording = recording;
    }

    @NonNull
    @Override
    public RecordsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.records_block,viewGroup,false);
        return new RecordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordsViewHolder recordsViewHolder, int i) {
        int imode=Integer.parseInt(mode.get(i));
        String itime=time.get(i);
        String icount=counter.get(i);
        DecimalFormat abc = new DecimalFormat("#.#");
        String irate = abc.format(Float.valueOf(rate.get(i)));
        int idiv=Integer.parseInt(division.get(i));
        if(idiv==0)
            recordsViewHolder.divtext.setText("Normal");
        else if(idiv==1)
            recordsViewHolder.divtext.setText("Invert");
        switch (imode)
        {
            case 0 : {
                recordsViewHolder.modetext.setText("Classic");
                recordsViewHolder.criteriatext.setText(""+itime+" s");
                break;
            }
            case 1 : {
                recordsViewHolder.modetext.setText("Arcade");
                recordsViewHolder.criteriatext.setText(""+icount);
                break;
            }
            case 2 : {
                recordsViewHolder.modetext.setText("Zen");
                recordsViewHolder.criteriatext.setText(""+irate+" /s");
                break;
            }
            case 3 : {
                recordsViewHolder.modetext.setText("Binary");
                recordsViewHolder.criteriatext.setText(""+icount);
                if(idiv==2)
                    recordsViewHolder.divtext.setText("Increasing\nPeriodicity");
                else if(idiv==3)
                    recordsViewHolder.divtext.setText("Unstable");
                break;
            }
            case 4 : {
                recordsViewHolder.modetext.setText("Relay");
                recordsViewHolder.criteriatext.setText(""+icount);
                break;
            }
        }
        final int pos =i;
        recordsViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mode.size();
    }

    public class RecordsViewHolder extends RecyclerView.ViewHolder{
        LinearLayout parent;
        TextView modetext;
        TextView criteriatext;
        TextView divtext;

        public RecordsViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.leaderboardparent);
            modetext = itemView.findViewById(R.id.rmodeshow);
            criteriatext=itemView.findViewById(R.id.rcriteria);
            divtext=itemView.findViewById(R.id.rmodediv);
        }
    }
}
