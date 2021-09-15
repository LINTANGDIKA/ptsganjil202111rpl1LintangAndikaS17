package com.example.ptsganjil202111rpl1lintangandikas17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.ptsganjil202111rpl1lintangandikas17.Adapter.AdapterMain;
import com.example.ptsganjil202111rpl1lintangandikas17.Model.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private AdapterMain adapterMain;
    RecyclerView recyclerView;
    private ArrayList<Model> userlist;
    @BindView(R.id.favorit)
    Button favorit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        userlist = new ArrayList<>();
        getDataApi();
        recyclerView = (RecyclerView) findViewById(R.id.rv_ball);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(getApplicationContext(), FavoritActivity.class);
                startActivity(move);
            }
        });
    }
    private void getDataApi(){
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_leagues.php?c=England")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray result = response.getJSONArray("countrys");
                            for (int i = 0; i < result.length(); i++) {
                                JSONObject data = result.getJSONObject(i);
                                Log.i("hh","hh"+data.getString("strLeague"));
                                String nama = data.getString("strLeague");
                                String tahun = data.getString("strCurrentSeason");
                                String gambar = data.getString("strBadge");
                                String identitas = data.getString("strDescriptionEN");
                                userlist.add(new Model( nama, tahun, identitas, gambar));
                                adapterMain = new AdapterMain( getApplicationContext(),userlist, new AdapterMain.Callback() {
                                    @Override
                                    public void onClick(int position) {
                                        Intent move = new Intent(getApplicationContext(),DetailActivity.class);
                                        Model model = userlist.get(position);
                                        move.putExtra("nama",model.getNama());
                                        move.putExtra("tahun",model.getTahun());
                                        move.putExtra("identitas",model.getIdentitas());
                                        move.putExtra("gambar",model.getGambar());
                                        startActivity(move);
                                    }
                                });
                                recyclerView.setAdapter(adapterMain);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}