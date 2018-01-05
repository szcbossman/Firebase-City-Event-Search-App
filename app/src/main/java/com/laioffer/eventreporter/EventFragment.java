package com.laioffer.eventreporter;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    OnItemSelectListener mCallback;
    ListView mListView;

    public EventFragment() {
        // Required empty public constructor
    }


    public void onItemSelected(int position){
        for (int i = 0; i < mListView.getChildCount(); i++){
            if (position == i) {
                mListView.getChildAt(i).setBackgroundColor(Color.BLUE);
            } else {
                mListView.getChildAt(i).setBackgroundColor(Color.parseColor("#FAFAFA"));
            }
        }
    }


    // Container Activity must implement this interface, a single way op from event F to comment F
    //***Observer Mode***
    public interface OnItemSelectListener {
        public void onItemSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnItemSelectListener) context; //context implements interface, use this interface to attach fragment to activity
        } catch (ClassCastException e) {
            //do something
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        mListView = (ListView) view.findViewById(R.id.event_list); //convert an Android widget to java listView
        //listView.setAdapter(new EventAdapter(getActivity()));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                getEventNames());

        // Assign adapter to ListView.
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCallback.onItemSelected(i);
            }
        });

        return view;
    }

    private String[] getEventNames() {
        String[] names = {
                "Event1", "Event2", "Event3",
                "Event4", "Event5", "Event6",
                "Event7", "Event8", "Event9",
                "Event10", "Event11", "Event12"};
        return names;

    }


}
