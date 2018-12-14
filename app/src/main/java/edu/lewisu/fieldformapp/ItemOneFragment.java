package edu.lewisu.fieldformapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {//@link ItemOneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

// TODO Remove all unnecessary code - Only needs newInstance, onCreate, and onCreateView to start
public class ItemOneFragment extends Fragment implements View.OnClickListener{
    public ItemOneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * <p>
     * //@param param1 Parameter 1.
     * //@param param2 Parameter 2.
     * //@return A new instance of fragment ItemOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item_one, container, false);
//        FloatingActionButton createForm = (FloatingActionButton) v.findViewById(R.id.add_form);
        Button button3 = v.findViewById(R.id.button3);
        Button button4 = v.findViewById(R.id.button4);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
//        createForm.setOnClickListener(this);

        return v;
    }

    // Handle Button Presses
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), FormDefault.class);

        switch (v.getId()) {
//            case R.id.add_form:
//                // No changes made to intent
//                break;
            case R.id.button3:
                // Will adjust form to Recruiter
                intent.putExtra("Form Type", 'R');
                break;
            case R.id.button4:
                // Will adjust form to Healthcare
                intent.putExtra("Form Type", 'H');
                break;
        }

        startActivity(intent);
    }
}
