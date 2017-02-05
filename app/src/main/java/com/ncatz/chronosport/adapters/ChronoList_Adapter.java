package com.ncatz.chronosport.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ncatz.chronosport.R;
import com.ncatz.chronosport.model.ChronoElement;
import com.ncatz.chronosport.other.MultiChoiceListener;
import com.ncatz.chronosport.utils.ViewAnimationUtils;
import com.ncatz.chronosport.model.Chrono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by yeray697 on 4/02/17.
 */

public class ChronoList_Adapter extends ArrayAdapter<Chrono> {

    private OnPlayListener onPlayListener;
    private MultiChoiceListener.OnSelectItemListener onSelectItemListener;

    public ChronoList_Adapter(Context context, List<Chrono> objects) {
        super(context, R.layout.chronolist_item, objects);
    }

    public interface OnPlayListener{
        void onPlay(Chrono clickedChrono);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ChronoHolder holder;
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.chronolist_item,parent,false);
            holder = new ChronoHolder(view);
            view.setTag(holder);
        } else {
            holder = (ChronoHolder) view.getTag();
        }
        final Chrono chrono = getItem(position);
        holder.tvTitle.setText(chrono.getName());
        //holder.tvTime.setText();
        holder.tvRepetitions.setText("Repetitions: " + chrono.getRepetitions());

        holder.rlHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItemsCount() == 0) {
                    int rotation = 180;
                    if (chrono.isExpanded()) {
                        rotation = 0;
                        ViewAnimationUtils.collapse(holder.rlBody, 200);
                    } else
                        ViewAnimationUtils.expand(holder.rlBody, 200);
                    chrono.invertExpand();
                    holder.btExpandCollapse.animate().rotation(rotation).start();
                } else {
                    if (onSelectItemListener != null)
                        onSelectItemListener.onClick(position);
                }
            }
        });
        holder.rlHead.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
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
                if (selectedItemsCount() == 0) {
                    if (onPlayListener != null){
                        onPlayListener.onPlay(chrono);
                    }
                } else {
                    if (onSelectItemListener != null)
                        onSelectItemListener.onClick(position);
                }
            }
        });
        return view;
    }

    class ChronoHolder{
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

    public void setOnSelectItemListener(MultiChoiceListener.OnSelectItemListener listener){
        this.onSelectItemListener = listener;
    }







    private HashMap<Integer, Boolean> mSelection = new HashMap<Integer, Boolean>();

    public void setSelected(int position, boolean value) {
        mSelection.put(position, value);
        notifyDataSetChanged();
    }

    public int selectedItemsCount(){
        return mSelection.size();
    }

    public boolean isPositionChecked(int position) {
        Boolean result = mSelection.get(position);
        return result == null ? false : result;
    }

    public Set<Integer> getCurrentCheckedPosition() {
        return mSelection.keySet();
    }

    public void removeSelection(int position) {
        mSelection.remove(position);
        notifyDataSetChanged();
    }

    public void clearSelection() {
        mSelection = new HashMap<Integer, Boolean>();
        notifyDataSetChanged();
    }
}
