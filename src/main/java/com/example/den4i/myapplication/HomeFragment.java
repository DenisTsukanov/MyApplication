package com.example.den4i.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    DatabaseReference databaseParkings;

    Context context;

    RecyclerView spisok;
    EditText lcns;
    EditText name;
    String mName;
    String mLcns;

    ArrayList<Car1> carList = new ArrayList<>();

    CarAdapter adapter = new CarAdapter(carList);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_table, container, false);
        context = view.getContext();

        databaseParkings = FirebaseDatabase.getInstance().getReference().child("parkings");
        lcns = view.findViewById(R.id.lcns);
        name = view.findViewById(R.id.name);
        final Button add = view.findViewById(R.id.add);
        spisok = view.findViewById(R.id.items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        spisok.setLayoutManager(layoutManager);

       readCars(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCar(adapter);
            }
        });

        return view;
    }

    public void readCars(final CarAdapter adapter){

        databaseParkings.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                showData(dataSnapshot,adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void showData(DataSnapshot dataSnapshot,CarAdapter adapter){

        carList.clear();

        for (DataSnapshot carSnapshot : dataSnapshot.getChildren()) {
            Car1 car1 = carSnapshot.getValue(Car1.class);
            carList.add(car1);
        }

        spisok.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void addCar(CarAdapter adapter) {
        mName = name.getText().toString();
        mLcns = lcns.getText().toString();
        if (TextUtils.isEmpty(mName)) {

            Toast.makeText(context, "You should enter a name", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(mLcns)) {

            Toast.makeText(context, "You should enter a license", Toast.LENGTH_LONG).show();

        } else {
            String id = databaseParkings.push().getKey();
            Car1 tmp = new Car1("test", mName, mLcns,id);
            databaseParkings.child(id).setValue(tmp);
            adapter.notifyDataSetChanged();
        }
    }

    private class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

        private ArrayList<Car1> mCars;

        class CarViewHolder extends RecyclerView.ViewHolder {

            public TextView nameField;
            public TextView lcnsField;
            public TextView modelField;

            public CarViewHolder(@NonNull View itemView) {
                super(itemView);

                nameField = itemView.findViewById(R.id.name);
                lcnsField = itemView.findViewById(R.id.lcns);
                modelField = itemView.findViewById(R.id.model);
            }
        }

        public CarAdapter(ArrayList<Car1> cars) {
            mCars = cars;
        }

        @NonNull
        @Override
        public CarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
            View view = getLayoutInflater().inflate(R.layout.fragment_home, null, false);
            CarViewHolder carViewHolder = new CarViewHolder(view);
            return carViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CarViewHolder carViewHolder, int position) {
            Car1 currentItem = mCars.get(position);

            carViewHolder.modelField.setText(currentItem.model);
            carViewHolder.lcnsField.setText(currentItem.license_plate);
            carViewHolder.nameField.setText(currentItem.owner);
        }

        @Override
        public int getItemCount() {
            return mCars.size();
        }
    }
}
