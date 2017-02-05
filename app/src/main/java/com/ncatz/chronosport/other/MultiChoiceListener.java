package com.ncatz.chronosport.other;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;

import com.ncatz.chronosport.R;
import com.ncatz.chronosport.interfaces.IChronoList;

import java.util.ArrayList;

/**
 * Created by yeray697 on 5/02/17.
 */

public class MultiChoiceListener implements AbsListView.MultiChoiceModeListener {
    private IChronoList presenter;
    private int nr = 0;

    public MultiChoiceListener(IChronoList presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if (checked) {
            nr++;
            presenter.setSelected(position, checked);
        } else {
            nr--;
            presenter.removeSelection(position);
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
                presenter.clearSelection();
                mode.finish();
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        presenter.clearSelection();
        nr = 0;
    }

    public interface OnSelectItemListener{
        void onClick(int position);
    }
}
