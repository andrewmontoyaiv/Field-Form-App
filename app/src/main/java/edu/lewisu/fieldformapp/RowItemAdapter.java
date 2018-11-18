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

public class RowItemAdapter extends RecyclerView.Adapter<RowItemAdapter.ViewHolder> {
    private RowItemData[] rowItemData;
    private ClickListener listener;
//    private View.OnClickListener onItemClickListener;

    RowItemAdapter(RowItemData[] rowItemData, ClickListener listener) {
        this.rowItemData = rowItemData;
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
        viewHolder.txtViewID.setText(rowItemData[position].getID());
        viewHolder.txtViewFN.setText(rowItemData[position].getFName());
        viewHolder.txtViewMN.setText(rowItemData[position].getMName());
        viewHolder.txtViewLN.setText(rowItemData[position].getLName());

        viewHolder.posIndex = Integer.parseInt(rowItemData[position].getID());
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
        return rowItemData.length;
    }

    void updateRow(String[] modifiedRecord, int position) {
        rowItemData[position].setFName(modifiedRecord[1]);
        rowItemData[position].setMName(modifiedRecord[2]);
        rowItemData[position].setLName(modifiedRecord[3]);
    }
}