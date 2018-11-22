package edu.lewisu.fieldformapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemTwoFragment extends Fragment {
    private static MainActivity mainActivity;
    private RecyclerView rvFrag2;
    public RowItemAdapter rowItemAdapter;

    // Variables used to track what changes have been made and where
    private int openRecordID = -1, openRecordIndex = -1;


    public static ItemTwoFragment newInstance(MainActivity activity) {
        ItemTwoFragment fragment = new ItemTwoFragment();

        mainActivity = activity;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRows(this.getView());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_two, container, false);
        rvFrag2 = view.findViewById(R.id.rvFormsFrag2);

        rvFrag2.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvFrag2.setAdapter(rowItemAdapter);
        rvFrag2.setItemAnimator(new DefaultItemAnimator());
        return view;
    }


    public void onResume() {
        super.onResume();

        // TODO: If deletion has occurred, recreate the forms
        // TODO add ability to track what type of change has been made.  Delete vs update

        // If statement that will only run if user has chosen to review a previous record
        if (openRecordIndex != -1) {
            if (!mainActivity.sql.doesIdExist(openRecordID)) {
                deleteRow();
            } else {
                updateRow();
            }

            // Reset the change trackers
            openRecordID = -1;
            openRecordIndex = -1;
        }
    }

    public void deleteRow() {
        rowItemAdapter.removeRow(openRecordIndex);
        rowItemAdapter.notifyDataSetChanged();
    }

    public void updateRow() {
        // TODO: validate if row is null
        // check if exists

            String[] rowData = mainActivity.sql.getSingleRecord(openRecordID);
            String[] rowDataSub = {rowData[0], rowData[1], rowData[2], rowData[3]};

            rowItemAdapter.updateRow(rowDataSub, openRecordIndex);

            rowItemAdapter.notifyItemChanged(openRecordIndex);
    }

    public void getRows(View v) {
        String[] rowData = mainActivity.getRowDataString();

        String rowID, rowFN, rowMN, rowLN;
        String tempID, tempFN, tempMN, tempLN;
        // Starting at 1 since the headers automatically take 1 place
        int numRows = 1;

        rowID = rowData[0];
        rowFN = rowData[1];
        rowMN = rowData[2];
        rowLN = rowData[3];

        for (int i = 0; i < rowID.length(); i++)
            if (rowData[0].charAt(i) == ',')
                numRows++;

        final RowItemData[] rowItems = new RowItemData[numRows - 1];

        for (int i = 0; i < numRows; i++) {
            if (rowID.indexOf(',') == -1) { // If no commas exist, it just uses the full string
                tempID = rowID;
                tempFN = rowFN;
                tempMN = rowMN;
                tempLN = rowLN;
            } else { // Creates a substring from the beginning until the first comma
                tempID = rowID.substring(0, rowID.indexOf(','));
                tempFN = rowFN.substring(0, rowFN.indexOf(','));
                tempMN = rowMN.substring(0, rowMN.indexOf(','));
                tempLN = rowLN.substring(0, rowLN.indexOf(','));
            }


            // Creates substring for all iterations except final
            if (i < (numRows - 1)) {
                rowID = rowID.substring(rowID.indexOf(',') + 1);
                rowFN = rowFN.substring(rowFN.indexOf(',') + 1);
                rowMN = rowMN.substring(rowMN.indexOf(',') + 1);
                rowLN = rowLN.substring(rowLN.indexOf(',') + 1);
            }

            // Creates a new RowItemData for all iterations except the first (which is null/blank)
            if (i != 0) {
                rowItems[i - 1] = new RowItemData(tempID, tempFN, tempMN, tempLN);
            }
        }

        // Creates an anonymous click listener that will be used when the user presses the button.
        rowItemAdapter = new RowItemAdapter(rowItems, new ClickListener() {
            @Override public void onPositionClicked(int position) {
                // callback performed on click

                int rowIndex = rowItemAdapter.getRowID(position);

                String[] tempStringArr = mainActivity.sql.getSingleRecord(rowIndex);

                openRecordID = Integer.parseInt(tempStringArr[0]);
                openRecordIndex = position;
                Intent intent = new Intent(getActivity(), FormDefault.class);
                intent.putExtra("Record Data", tempStringArr);
                // get record ID


                intent.putExtra("Record ID",  openRecordID);
                startActivity(intent);
            }
            @Override public void onLongClicked(int position) {
                // callback performed on click
            }
        });

    }

}
