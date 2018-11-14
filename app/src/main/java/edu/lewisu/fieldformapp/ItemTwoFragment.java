package edu.lewisu.fieldformapp;

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
    private RowItemAdapter rowItemAdapter;

    public static ItemTwoFragment newInstance(MainActivity activity) {
        ItemTwoFragment fragment = new ItemTwoFragment();

        mainActivity = activity;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //rvFrag2 = getView().findViewById(R.id.rvFormsFrag2);

        getRows(this.getView());

        //((MainActivity)getActivity()).getRows(this.getView());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        rvFrag2 = (RecyclerView) inflater.inflate(R.id.rvFormsFrag2, container);
//        getView().findViewById(R.id.rvFormsFrag2);
//        return inflater.inflate(R.layout.fragment_item_two, container, false);

        View view = inflater.inflate(R.layout.fragment_item_two, container, false);
        rvFrag2 = (RecyclerView) view.findViewById(R.id.rvFormsFrag2);

        rvFrag2.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvFrag2.setAdapter(rowItemAdapter);
        rvFrag2.setItemAnimator(new DefaultItemAnimator());
        return view;
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

        RowItemData[] rowItems = new RowItemData[numRows - 1];

        for (int i = 0; i < numRows; i++) {
            if (rowID.indexOf(',') == -1) {
                tempID = rowID;
                tempFN = rowFN;
                tempMN = rowMN;
                tempLN = rowLN;
            } else {
                tempID = rowID.substring(0, rowID.indexOf(','));
                tempFN = rowFN.substring(0, rowFN.indexOf(','));
                tempMN = rowMN.substring(0, rowMN.indexOf(','));
                tempLN = rowLN.substring(0, rowLN.indexOf(','));
            }


            if (i < (numRows - 1)) {
                rowID = rowID.substring(rowID.indexOf(',') + 1);
                rowFN = rowFN.substring(rowFN.indexOf(',') + 1);
                rowMN = rowMN.substring(rowMN.indexOf(',') + 1);
                rowLN = rowLN.substring(rowLN.indexOf(',') + 1);
            }

            if (i != 0)
                rowItems[i-1] = new RowItemData(tempID, tempFN, tempMN, tempLN);
        }

//        setContentView(v);

//        RecyclerView recyclerView = (RecyclerView) mainActivity.rvFrag2;
        // Code excecutes until this point
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        RowItemAdapter rowItemAdapter = new RowItemAdapter(rowItems);
//        recyclerView.setAdapter(rowItemAdapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        RowItemAdapter rowItemAdapter = new RowItemAdapter(rowItems);
        rowItemAdapter = new RowItemAdapter(rowItems);
//        rvFrag2.setAdapter(rowItemAdapter);


//        rvFrag2.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        rvFrag2.setItemAnimator(new DefaultItemAnimator());

        // Reset ContentView to the main activity
//        setContentView(R.layout.activity_main);
    }
}
