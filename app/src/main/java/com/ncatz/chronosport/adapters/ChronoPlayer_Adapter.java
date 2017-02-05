package com.ncatz.chronosport.adapters;

import android.content.Context;
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
 * Created by amador on 5/02/17.
 */

public class ChronoPlayer_Adapter extends RecyclerView.Adapter <ChronoPlayer_Adapter.ChronoElementViewHolder>{

    private List<ChronoElement> list;
    private Context context;


    public ChronoPlayer_Adapter(Context context){

        this.context = context;
        list = new ArrayList<ChronoElement>();
    }

    @Override
    public ChronoElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.chrono_element_item, parent, false);

        return new ChronoElementViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ChronoElementViewHolder holder, int position) {

        ChronoElement element = list.get(position);
        holder.txvNameElement.setText(element.getName());

        if(element instanceof ChronoRepetitionElement){

            holder.txvActionElement.setText(String.valueOf(((ChronoRepetitionElement) element).getRepetitions())+" "
                    +context.getString(R.string.repetitions));

        }else {

            holder.txvActionElement.setText(String.valueOf(((ChronoTimeElement)element).getTime())+" "
                    +context.getString(R.string.time));
        }

    }


    public void addItem(ChronoElement element){

        list.add(0, element);
        notifyItemInserted(0);
    }

    public void addAllItems(List<ChronoElement> elementList){

        list.addAll(elementList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ChronoElementViewHolder extends RecyclerView.ViewHolder {

        TextView txvNameElement, txvActionElement;

        public ChronoElementViewHolder(View itemView) {
            super(itemView);
            txvActionElement = (TextView)itemView.findViewById(R.id.txvActionElement);
            txvNameElement = (TextView)itemView.findViewById(R.id.txvNameElement);

        }
    }
}
