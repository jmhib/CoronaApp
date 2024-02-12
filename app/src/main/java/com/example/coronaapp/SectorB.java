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


public class SectorB extends Fragment {

    RecyclerView recyclerViewB;


    public SectorB() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewB = inflater.inflate(R.layout.fragment_sector_b, container, false);
        return viewB;
    }

    @Override
    public void onViewCreated(View viewB, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(viewB, savedInstanceState);

        recyclerViewB = (RecyclerView) viewB.findViewById(R.id.recviB);

        LinearLayoutManager layoutManagerB = new LinearLayoutManager(getContext());
        layoutManagerB.setOrientation(RecyclerView.VERTICAL);
        recyclerViewB.setLayoutManager(layoutManagerB);

        TablaAdapter adapter2 = new TablaAdapter(TablaService.tablaList,
                R.layout.item, getActivity());

        recyclerViewB.setAdapter(adapter2);

        cargaTablasFirebasePredicB();
    }



    public void cargaTablasFirebasePredicB() {
        FirebaseDatabase databaseB = FirebaseDatabase.getInstance("https://coronaapp-5affa-default-rtdb.firebaseio.com/");
        DatabaseReference referenceB = databaseB.getReference("Sector II");

        referenceB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Tabla tablaB = snapshot.getValue(Tabla.class);
                tablaB.setId(snapshot.getKey());

                if (!TablaService.tablaList.contains(tablaB)) {
                    TablaService.addElemento(tablaB);
                }
                recyclerViewB.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Tabla tablaB = snapshot.getValue(Tabla.class);
                tablaB.setId(snapshot.getKey());

                if (TablaService.tablaList.contains(tablaB)) {
                    TablaService.updateElemento(tablaB);
                }
                recyclerViewB.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Tabla tablaB = snapshot.getValue(Tabla.class);
                tablaB.setId(snapshot.getKey());

                if (TablaService.tablaList.contains(tablaB)) {
                    TablaService.removeElemento(tablaB);
                }
                recyclerViewB.getAdapter().notifyDataSetChanged();
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