package edu.lewisu.fieldformapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RowItemAdapter extends RecyclerView.Adapter<RowItemAdapter.ViewHolder> {
    private ArrayList<RowItemData> rowItemList;
    private ClickListener listener;

    RowItemAdapter(RowItemData[] rowItemData, ClickListener listener) {
        rowItemList = new ArrayList<>(rowItemData.length);
        Collections.addAll(rowItemList, rowItemData);
//        this.rowItemData = rowItemData;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RowItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_item, parent, false);

        return new ViewHolder(itemLayoutView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        viewHolder.txtViewID.setText(rowItemList.get(position).getID());
        viewHolder.txtViewFN.setText(rowItemList.get(position).getFName());
        viewHolder.txtViewMN.setText(rowItemList.get(position).getMName());
        viewHolder.txtViewLN.setText(rowItemList.get(position).getLName());

        viewHolder.posIndex = Integer.parseInt(rowItemList.get(position).getID());
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtViewID;
        TextView txtViewFN;
        TextView txtViewMN;
        TextView txtViewLN;
        AppCompatImageButton btnTEMPNAME;

        private WeakReference<ClickListener> listenerRef;

        int posIndex;

        ViewHolder(final View itemLayoutView, ClickListener listener) {
            super(itemLayoutView);

            listenerRef = new WeakReference<>(listener);

            txtViewID = (TextView) itemLayoutView.findViewById(R.id.textView);
            txtViewFN = (TextView) itemLayoutView.findViewById(R.id.textView2);
            txtViewMN = (TextView) itemLayoutView.findViewById(R.id.textView3);
            txtViewLN = (TextView) itemLayoutView.findViewById(R.id.textView4);
            btnTEMPNAME = itemLayoutView.findViewById(R.id.imageButton);

            btnTEMPNAME.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return rowItemList.size();
    }

    void updateRow(String[] modifiedRecord, int position) {
        rowItemList.get(position).setFName(modifiedRecord[1]);
        rowItemList.get(position).setMName(modifiedRecord[2]);
        rowItemList.get(position).setLName(modifiedRecord[3]);
    }

    int getRowID(int position) { return Integer.parseInt(rowItemList.get(position).getID()); }

    void removeRow(int position) {
        rowItemList.remove(position);
    }

    void addRow(RowItemData newRow) {
        rowItemList.add(newRow);
    }
}