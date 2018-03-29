package com.example.minhthanh.listview_lab3_androidth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Fragment1 extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    private Fragment1.AFragmentListener listener;
    public interface AFragmentListener{
        void onButtonClick();
    }

    public static Fragment1 newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        Log.d("PAGE",""+mPage);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_fragment1, container, false);

        RecyclerView rvContacts = (RecyclerView)view.findViewById(R.id.recycler_view);

        GsonBuilder buider = new GsonBuilder();
        Gson gson = buider.create();
        Type listType = new TypeToken<List<Profile>>(){}.getType();
        if( mPage == 1){
            List<Profile> chapters = (List<Profile>) gson.fromJson(MainActivity.serverResponse, listType);

            ContactsAdapter adapter = new ContactsAdapter(getActivity(), chapters);
            rvContacts.setAdapter(adapter);
            rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));}
        else if(mPage == 2)
        {
            List<Profile> chapters = (List<Profile>) gson.fromJson(MainActivity.serverRespondeTopRate, listType);

            ContactsAdapter adapter = new ContactsAdapter(getActivity(), chapters);
            rvContacts.setAdapter(adapter);
            rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        return view;
    }
}