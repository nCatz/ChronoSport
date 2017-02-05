package com.ncatz.chronosport.other;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;

import com.ncatz.chronosport.R;
import com.ncatz.chronosport.adapters.ChronoList_Adapter;

/**
 * Created by yeray697 on 5/02/17.
 */

public class MultiChoiceListener implements AbsListView.MultiChoiceModeListener {
    private ChronoList_Adapter adapter;
    private int nr = 0;

    public MultiChoiceListener(ChronoList_Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if (checked) {
            nr++;
            adapter.setNewSelection(position, checked);
        } else {
            nr--;
            adapter.removeSelection(position);
        }
        mode.setTitle(nr + " selected");
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        nr = 0;
        mode.getMenuInflater().inflate(R.menu.chronolist_selected_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_delete:
                nr = 0;
                adapter.clearSelection();
                mode.finish();
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        adapter.clearSelection();
        nr = 0;
    }

    public interface OnSelectItemListener{
        void onClick(int position);
    }
}
