package com.example.coronaapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronaapp.adapter.TablaAdapter;
import com.example.coronaapp.modelo.Tabla;
import com.example.coronaapp.modelo.TablaService;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SectorC extends Fragment {

    RecyclerView recyclerViewC;


    public SectorC() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewC = inflater.inflate(R.layout.fragment_sector_c, container, false);
        return viewC;
    }

    @Override
    public void onViewCreated(View viewC, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(viewC, savedInstanceState);

        recyclerViewC = (RecyclerView) viewC.findViewById(R.id.recviC);

        LinearLayoutManager layoutManagerC = new LinearLayoutManager(getContext());
        layoutManagerC.setOrientation(RecyclerView.VERTICAL);
        recyclerViewC.setLayoutManager(layoutManagerC);

        TablaAdapter adapter3 = new TablaAdapter(TablaService.tablaList,
                R.layout.item, getActivity());

        recyclerViewC.setAdapter(adapter3);

        cargaTablasFirebasePredicC();
    }



    public void cargaTablasFirebasePredicC() {
        FirebaseDatabase databaseC = FirebaseDatabase.getInstance("https://coronaapp-5affa-default-rtdb.firebaseio.com/");
        DatabaseReference referenceC = databaseC.getReference("Sector III");

        referenceC.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Tabla tablaC = snapshot.getValue(Tabla.class);
                tablaC.setId(snapshot.getKey());

                if (!TablaService.tablaList.contains(tablaC)) {
                    TablaService.addElemento(tablaC);
                }
                recyclerViewC.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Tabla tablaC = snapshot.getValue(Tabla.class);
                tablaC.setId(snapshot.getKey());

                if (TablaService.tablaList.contains(tablaC)) {
                    TablaService.updateElemento(tablaC);
                }
                recyclerViewC.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Tabla tablaC = snapshot.getValue(Tabla.class);
                tablaC.setId(snapshot.getKey());

                if (TablaService.tablaList.contains(tablaC)) {
                    TablaService.removeElemento(tablaC);
                }
                recyclerViewC.getAdapter().notifyDataSetChanged();
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