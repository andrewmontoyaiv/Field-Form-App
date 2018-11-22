package edu.lewisu.fieldformapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
        FloatingActionButton createForm = (FloatingActionButton) v.findViewById(R.id.add_form);
        createForm.setOnClickListener(this);

        return v;
    }

    // Handle Button Presses
    @Override
    public void onClick(View v) {
//        Intent intent;

        switch (v.getId()) {
            case R.id.add_form:
                Intent intent = new Intent(getActivity(), FormDefault.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent = new Intent(getActivity(), FormDefault.class);

                intent.putExtra("Form Type", "R");
                startActivity(intent);
                break;
            case R.id.button4:
                intent = new Intent(getActivity(), FormDefault.class);

                intent.putExtra("Form Type", "H");
                startActivity(intent);
                break;
        }
    }
}


//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//
//@Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//}
