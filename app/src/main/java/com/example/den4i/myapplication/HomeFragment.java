package com.example.den4i.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class HomeFragment extends Fragment implements View.OnClickListener {
    EditText lcns;
    EditText name;
    Button add;
    ListView items;
    MainActivity.CarAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        ListView listView = (ListView) view.findViewById(R.id.nav_home);
        return view;
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        lcns = (EditText) findViewById(R.id.lcns);
        name = (EditText) getActivity().findViewById(R.id.name);
        add = (Button) getActivity().findViewById(R.id.add);
        items = (ListView) getActivity().findViewById(R.id.items);
        items.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(new Car1("test",name.getText().toString(),lcns.getText().toString()));
            }
        });
    }
}
