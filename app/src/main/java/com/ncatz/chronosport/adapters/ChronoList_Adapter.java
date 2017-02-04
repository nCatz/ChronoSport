package com.ncatz.chronosport.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ncatz.chronosport.R;
import com.ncatz.chronosport.model.ChronoElement;
import com.ncatz.chronosport.utils.ViewAnimationUtils;
import com.ncatz.chronosport.model.Chrono;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeray697 on 4/02/17.
 */

public class ChronoList_Adapter extends RecyclerView.Adapter<ChronoList_Adapter.ChronoHolder> {

    private ArrayList<Chrono> items;

    public ChronoList_Adapter(ArrayList<Chrono> items) {
        this.items = items;
    }

    private OnPlayListener onPlayListener;

    public interface OnPlayListener{
        void onPlay(Chrono clickedChrono);
    }

    @Override
    public ChronoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChronoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chronolist_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final ChronoHolder holder, int position) {
        final Chrono chrono = items.get(holder.getAdapterPosition());
        holder.tvTitle.setText(chrono.getName());
        //holder.tvTime.setText();
        holder.tvRepetitions.setText("Repetitions: " + chrono.getRepetitions());

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rotation = 180;
                if (chrono.isExpanded()) {
                    rotation = 0;
                    ViewAnimationUtils.collapse(holder.rlBody,200);
                } else
                    ViewAnimationUtils.expand(holder.rlBody,200);
                chrono.invertExpand();
                holder.btExpandCollapse.animate().rotation(rotation).start();
            }
        });
        if (chrono.isExpanded()) {
            holder.rlBody.setVisibility(View.VISIBLE);
            holder.btExpandCollapse.setRotation(180);
            holder.rlBody.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        else {
            holder.rlBody.setVisibility(View.GONE);
            holder.btExpandCollapse.setRotation(0);
            holder.rlBody.getLayoutParams().height = 0;
        }
        holder.setElements(chrono.getElements());
        holder.btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPlayListener != null){
                    onPlayListener.onPlay(chrono);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (items == null)?0:items.size();
    }

    class ChronoHolder extends RecyclerView.ViewHolder {
        View root;
        RelativeLayout rlHead,rlBody;
        TextView tvTitle;
        TextView tvTime;
        TextView tvRepetitions;
        ImageView btExpandCollapse;
        RecyclerView rvSubList;
        ChronoSubList_Adapter adapter;
        Button btPlay;

        public ChronoHolder(View itemView) {
            super(itemView);
            adapter = new ChronoSubList_Adapter();
            root = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tvChronoTitle_item);
            tvTime = (TextView) itemView.findViewById(R.id.tvChronoTime_item);
            tvRepetitions = (TextView) itemView.findViewById(R.id.tvChronoRepetitions_item);
            btExpandCollapse = (ImageView) itemView.findViewById(R.id.btChronoExpand_item);
            rlHead = (RelativeLayout) itemView.findViewById(R.id.header_chronolist_item);
            rlBody = (RelativeLayout) itemView.findViewById(R.id.body_chronolist_item);
            rvSubList = (RecyclerView) itemView.findViewById(R.id.rvChronoSubList);
            btPlay = (Button) itemView.findViewById(R.id.btPlayChrono_item);
            rvSubList.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            rvSubList.setAdapter(adapter);
            rlBody.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Event used to consume the rlHead click listener and to not collapse when user clicks it
                }
            });
        }
        public void setElements(List<ChronoElement> elements){
            adapter.clearElements();
            adapter.addAll(elements);
            adapter.notifyDataSetChanged();
        }
    }

    public void setOnPlayListener(OnPlayListener onPlayListener) {
        this.onPlayListener = onPlayListener;
    }
}
