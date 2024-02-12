package com.example.coronaapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coronaapp.adapter.TablaAdapter;
import com.example.coronaapp.modelo.Tabla;
import com.example.coronaapp.modelo.TablaService;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SectorA extends Fragment {

    RecyclerView recyclerViewA;


    public SectorA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewA = inflater.inflate(R.layout.fragment_sector_a, container, false);
        return viewA;
    }

    @Override
    public void onViewCreated(View viewA, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(viewA, savedInstanceState);

        recyclerViewA = (RecyclerView) viewA.findViewById(R.id.recviA);

        LinearLayoutManager layoutManagerA = new LinearLayoutManager(getContext());
        layoutManagerA.setOrientation(RecyclerView.VERTICAL);
        recyclerViewA.setLayoutManager(layoutManagerA);

        TablaAdapter adapter1 = new TablaAdapter(TablaService.tablaList,
                R.layout.item, getActivity());

        recyclerViewA.setAdapter(adapter1);

        cargaTablasFirebasePredicA();
    }



    public void cargaTablasFirebasePredicA() {
        FirebaseDatabase databaseA = FirebaseDatabase.getInstance("https://coronaapp-5affa-default-rtdb.firebaseio.com/");
        DatabaseReference referenceA = databaseA.getReference("Sector I");

        referenceA.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Tabla tablaA = snapshot.getValue(Tabla.class);
                tablaA.setId(snapshot.getKey());

                if (!TablaService.tablaList.contains(tablaA)) {
                    TablaService.addElemento(tablaA);
                }
                recyclerViewA.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Tabla tablaA = snapshot.getValue(Tabla.class);
                tablaA.setId(snapshot.getKey());

                if (TablaService.tablaList.contains(tablaA)) {
                    TablaService.updateElemento(tablaA);
                }
                recyclerViewA.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Tabla tablaA = snapshot.getValue(Tabla.class);
                tablaA.setId(snapshot.getKey());

                if (TablaService.tablaList.contains(tablaA)) {
                    TablaService.removeElemento(tablaA);
                }
                recyclerViewA.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}