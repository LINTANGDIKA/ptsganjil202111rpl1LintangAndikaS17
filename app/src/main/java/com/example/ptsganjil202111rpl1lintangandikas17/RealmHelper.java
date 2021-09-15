package com.example.ptsganjil202111rpl1lintangandikas17;

import android.util.Log;

import com.example.ptsganjil202111rpl1lintangandikas17.Model.Model;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final Model model){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Number currentIdNum = realm.where(Model.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1; }
                    model.setId(nextId);
                    Model modell = realm.copyToRealm(model);
                    Log.e("Created", "Database was created" + modell);

                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<Model> getAllBall(){
        RealmResults<Model> results = realm.where(Model.class).findAll();
        return results;
    }

    // untuk menghapus data
    public void delete(Integer id){

        final RealmResults<Model> model = realm.where(Model.class).equalTo("id", id).findAll();
        Log.e("Created", "Database was created" + model  + id);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
}
