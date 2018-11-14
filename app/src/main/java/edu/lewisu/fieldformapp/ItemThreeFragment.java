package edu.lewisu.fieldformapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemThreeFragment extends Fragment {
    private static MainActivity mainActivity;

    public static ItemThreeFragment newInstance(MainActivity activity) {
        ItemThreeFragment fragment = new ItemThreeFragment();

        mainActivity = activity;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_three, container, false);
    }

//    private View.OnClickListener mButtonClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//                mainActivity.(v);
//
//        }
//    };

    private void generateData(View v) {
        ((MainActivity)getActivity()).generateData(v);
    }

    private void view(View v) {
        ((MainActivity)getActivity()).view(v);
    }
}
