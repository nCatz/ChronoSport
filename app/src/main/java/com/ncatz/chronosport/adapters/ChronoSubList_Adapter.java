package com.ncatz.chronosport.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ncatz.chronosport.R;
import com.ncatz.chronosport.model.ChronoElement;
import com.ncatz.chronosport.model.ChronoRepetitionElement;
import com.ncatz.chronosport.model.ChronoTimeElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeray697 on 4/02/17.
 */

public class ChronoSubList_Adapter extends RecyclerView.Adapter<ChronoSubList_Adapter.ChronoElementHolder> {

    private ArrayList<ChronoElement> items;

    public ChronoSubList_Adapter() {
        this.items = new ArrayList<>();
    }
    @Override
    public ChronoElementHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChronoElementHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chronosublist_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final ChronoElementHolder holder, int position) {
        final ChronoElement element = items.get(holder.getAdapterPosition());
        holder.tvNumber.setText(String.valueOf(holder.getAdapterPosition()+1));
        holder.tvName.setText(element.getName());
        if (element instanceof ChronoTimeElement) {
            int timeAux = ((ChronoTimeElement)element).getTime()/1000;
            String time = (timeAux / 60) + ":" + (timeAux % 60);
            holder.tvTime.setText(time);
        } else {
            holder.tvTime.setText("x"+String.valueOf(((ChronoRepetitionElement)element).getRepetitions()));
        }
    }

    @Override
    public int getItemCount() {
        return (items == null)?0:items.size();
    }

    public void addAll(List<ChronoElement> elements) {
        items.addAll(elements);
    }

    public void clearElements() {
        items.clear();
    }

    class ChronoElementHolder extends RecyclerView.ViewHolder {
        View root;
        TextView tvNumber;
        TextView tvName;
        TextView tvTime;

        public ChronoElementHolder(View itemView) {
            super(itemView);
            root = itemView;
            tvNumber = (TextView) root.findViewById(R.id.tvChronoElementNumber_item);
            tvName = (TextView) root.findViewById(R.id.tvChronoElementName_item);
            tvTime = (TextView) root.findViewById(R.id.tvChronoElementTime_item);
        }
    }
}
