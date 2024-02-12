package com.example.coronaapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coronaapp.adapter.TablaAdapter;
import com.example.coronaapp.modelo.Tabla;
import com.example.coronaapp.modelo.TablaService;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Predic extends Fragment {

    RecyclerView rcP;

    public Predic() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewP = inflater.inflate(R.layout.fragment_predic, container, false);

        /* Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) viewP.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getFragmentManager(),
                getActivity()));*/

       /*// Get the ViewPager and set it's PagerAdapter so that it can display items

        ViewPager viewPager = (ViewPager) viewP.findViewById(R.id.viewpager);
        SampleFragmentPagerAdapter pagerAdapter =
                new SampleFragmentPagerAdapter(getFragmentManager(), getActivity());
        viewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) viewP.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }*/


        /*rcP        = viewP.findViewById(R.id.rcP);

        LinearLayoutManager lmP = new LinearLayoutManager(getContext());
        lmP.setOrientation(RecyclerView.VERTICAL);
        rcP.setLayoutManager(lmP);

        TablaAdapter adapterP = new TablaAdapter(TablaService.tablaList,
                R.layout.item, getActivity());

        rcP.setAdapter(adapterP);

        cargaTablasFirebasePredic();*/

        return viewP;
    }
/*
    public void cargaTablasFirebasePredic() {
        FirebaseDatabase databaseP = FirebaseDatabase.getInstance("https://coronaapp-5affa-default-rtdb.firebaseio.com/");
        DatabaseReference referenceP = databaseP.getReference("Sector II");

        referenceP.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Tabla tablaP = snapshot.getValue(Tabla.class);
                tablaP.setId(snapshot.getKey());

                if (!TablaService.tablaList.contains(tablaP)) {
                    TablaService.addElemento(tablaP);
                }
                rcP.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {
                Tabla tablaP = snapshot.getValue(Tabla.class);
                tablaP.setId(snapshot.getKey());

                if (TablaService.tablaList.contains(tablaP)) {
                    TablaService.updateElemento(tablaP);
                }
                rcP.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Tabla tablaP = snapshot.getValue(Tabla.class);
                tablaP.setId(snapshot.getKey());

                if (TablaService.tablaList.contains(tablaP)) {
                    TablaService.removeElemento(tablaP);
                }
                rcP.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

}