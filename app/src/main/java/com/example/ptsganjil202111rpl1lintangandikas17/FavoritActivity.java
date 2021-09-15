package com.example.ptsganjil202111rpl1lintangandikas17;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ptsganjil202111rpl1lintangandikas17.Adapter.AdapterFavorit;
import com.example.ptsganjil202111rpl1lintangandikas17.Model.Model;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoritActivity extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    AdapterFavorit adapterFavorit;
    List<Model> models;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.rv_favorit);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        models = new ArrayList<>();

        models = realmHelper.getAllBall();

        show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        adapterFavorit.notifyDataSetChanged();
        show();
    }

    public void show(){
        adapterFavorit = new AdapterFavorit(this, models);
        recyclerView.setAdapter(adapterFavorit);
    }
}