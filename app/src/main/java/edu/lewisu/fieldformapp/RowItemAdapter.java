package edu.lewisu.fieldformapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RowItemAdapter extends RecyclerView.Adapter<RowItemAdapter.ViewHolder> {
    private RowItemData[] rowItemData;

    public RowItemAdapter(RowItemData[] rowItemData) {
        this.rowItemData = rowItemData;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RowItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_item, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.txtViewID.setText(rowItemData[position].getID());
        viewHolder.txtViewFN.setText(rowItemData[position].getFName());
        viewHolder.txtViewMN.setText(rowItemData[position].getMName());
        viewHolder.txtViewLN.setText(rowItemData[position].getLName());



    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewID;
        public TextView txtViewFN;
        public TextView txtViewMN;
        public TextView txtViewLN;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewID = (TextView) itemLayoutView.findViewById(R.id.textView);
            txtViewFN = (TextView) itemLayoutView.findViewById(R.id.textView2);
            txtViewMN = (TextView) itemLayoutView.findViewById(R.id.textView3);
            txtViewLN = (TextView) itemLayoutView.findViewById(R.id.textView4);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return rowItemData.length;
    }
}