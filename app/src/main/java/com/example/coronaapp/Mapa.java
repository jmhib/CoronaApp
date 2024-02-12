package com.example.coronaapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.coronaapp.adapter.TablaAdapter;
import com.example.coronaapp.modelo.Tabla;
import com.example.coronaapp.modelo.TablaService;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Mapa extends Fragment {

    RecyclerView rc;
    ImageView imgView;
    Button btn1, btn2, btn3;
    ChildEventListener listener;
    DatabaseReference reference;
    String sector;
    int cual = 0;

    public Mapa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        rc = view.findViewById(R.id.rc);


        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(RecyclerView.VERTICAL);
        rc.setLayoutManager(lm);

        TablaAdapter adapter = new TablaAdapter(TablaService.tablaList,
                R.layout.item, getActivity());

        rc.setAdapter(adapter);

        /*
        view.findViewById(R.id.sector1btn).setOnClickListener(mListener);
        view.findViewById(R.id.sector2btn).setOnClickListener(mListener);
        view.findViewById(R.id.sector3btn).setOnClickListener(mListener);*/

/*
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sector = "String I";
                cargaTablasFirebase(sector);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sector = "String II";
                cargaTablasFirebase(sector);
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sector = "Sector III";
                cargaTablasFirebase(sector);

            }
        });*/


        return view;
    }


    public void cargaTablasFirebase(String sector) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://coronaapp-5affa-default-rtdb.firebaseio.com/");
        reference = database.getReference(sector);

        cual = 1;

        listener = (ChildEventListener) reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Tabla tabla = snapshot.getValue(Tabla.class);
                tabla.setId(snapshot.getKey());

                if (!TablaService.tablaList.contains(tabla)) {
                    TablaService.addElemento(tabla);
                }
                rc.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Tabla tabla = snapshot.getValue(Tabla.class);
                tabla.setId(snapshot.getKey());

                if (TablaService.tablaList.contains(tabla)) {
                    TablaService.updateElemento(tabla);
                }
                rc.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Tabla tabla = snapshot.getValue(Tabla.class);
                tabla.setId(snapshot.getKey());

                if (TablaService.tablaList.contains(tabla)) {
                    TablaService.removeElemento(tabla);
                }
                rc.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


/*
    private final View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.sector1btn:

                    sector = "Sector I";
                    //cargaTablasFirebase(sector);
                    cual = 1;
                    break;
                case R.id.sector2btn:
                    if (cual != 0) {
                        FirebaseDatabase.getInstance("https://coronaapp-5affa-default-rtdb.firebaseio.com/").getReference(sector).removeEventListener(listener);
                    }
                    sector = "Sector II";
                    cargaTablasFirebase(sector);
                    cual = 1;
                    break;
                case R.id.sector3btn:
                    if (cual != 0) {
                        FirebaseDatabase.getInstance("https://coronaapp-5affa-default-rtdb.firebaseio.com/").getReference(sector).removeEventListener(listener);
                    }
                    sector = "Sector III";
                    cargaTablasFirebase(sector);
                    //reference.removeEventListener(listener);
                    cual = 1;
                    break;
            }
        }
    };*/


}