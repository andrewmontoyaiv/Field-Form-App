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
import java.util.Collections;

public class RowItemAdapter extends RecyclerView.Adapter<RowItemAdapter.ViewHolder> {
    private ArrayList<RowItemData> rowItemList;
    private ClickListener listener;

    RowItemAdapter(RowItemData[] rowItemData, ClickListener listener) {
        rowItemList = new ArrayList<>(rowItemData.length);
        Collections.addAll(rowItemList, rowItemData);
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
        viewHolder.txtViewID.setText(String.valueOf(rowItemList.get(position).getID()));
        viewHolder.txtViewFN.setText(rowItemList.get(position).getFName());
        viewHolder.txtViewMN.setText(rowItemList.get(position).getMName());
        viewHolder.txtViewLN.setText(rowItemList.get(position).getLName());
        if (rowItemList.get(position).getFType() == 'R')
            viewHolder.txtViewFT.setText("Recruiter Form");
        else if (rowItemList.get(position).getFType() == 'H')
            viewHolder.txtViewFT.setText("Healthcare Form");
        else
            viewHolder.txtViewFT.setText(String.valueOf(rowItemList.get(position).getFType()));

        viewHolder.posIndex = rowItemList.get(position).getID();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtViewID;
        TextView txtViewFN;
        TextView txtViewMN;
        TextView txtViewLN;
        TextView txtViewFT;
        AppCompatImageButton btnTEMPNAME;

        private WeakReference<ClickListener> listenerRef;

        int posIndex;

        ViewHolder(final View view, ClickListener listener) {
            super(view);

            listenerRef = new WeakReference<>(listener);

            txtViewID = (TextView) view.findViewById(R.id.textView);
            txtViewFN = (TextView) view.findViewById(R.id.textView2);
            txtViewMN = (TextView) view.findViewById(R.id.textView3);
            txtViewLN = (TextView) view.findViewById(R.id.textView4);
            txtViewFT = (TextView) view.findViewById(R.id.textView5);
            btnTEMPNAME = view.findViewById(R.id.imageButton);

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
        rowItemList.get(position).setFName(modifiedRecord[2]);
        rowItemList.get(position).setMName(modifiedRecord[3]);
        rowItemList.get(position).setLName(modifiedRecord[4]);
    }

    int getRowID(int position) { return rowItemList.get(position).getID(); }

    void removeRow(int position) {
        rowItemList.remove(position);
    }
}