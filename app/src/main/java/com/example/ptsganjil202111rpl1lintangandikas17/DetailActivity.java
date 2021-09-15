package com.example.ptsganjil202111rpl1lintangandikas17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptsganjil202111rpl1lintangandikas17.Model.Model;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity {
    Bundle bundle;
    @BindView(R.id.nama)
    TextView nama;
    @BindView(R.id.notelp)
    TextView notelp;
    @BindView(R.id.image)
    CircleImageView image;
    @BindView(R.id.identitas)
    TextView identitas;
    @BindView(R.id.favorit)
    ImageView favorit;
    String nama1, notelp1, gambar, identitas1;
    Realm realm;
    RealmHelper realmHelper;
    Model model;
    private List<Model> userlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        bundle = getIntent().getExtras();
        Realm.init(DetailActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        if(bundle != null) {
            ButterKnife.bind(this);
            nama1 = bundle.getString("nama");
            notelp1 = bundle.getString("tahun");
            gambar = bundle.getString("gambar");
            identitas1 = bundle.getString("identitas");
            nama.setText(nama1);
            notelp.setText(notelp1);
            identitas.setText(identitas1);
            Picasso.get()
                    .load(gambar)
                    .error(R.drawable.ic_baseline_person_24)
                    .into(image);
            favorit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favorit.setImageResource(R.drawable.ic_baseline_favorite_25);
                    realmHelper = new RealmHelper(realm);
                    realmHelper.save(new Model(nama1, notelp1, identitas1, gambar ));
                    Log.i("hal","jj" + nama1 + notelp1 + identitas1 + gambar);
                    Toast.makeText(DetailActivity.this, "Berhasil Disimpan di Favorit :)", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}