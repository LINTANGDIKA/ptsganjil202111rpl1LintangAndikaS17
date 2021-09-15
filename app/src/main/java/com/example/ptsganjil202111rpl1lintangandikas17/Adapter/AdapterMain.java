package com.example.ptsganjil202111rpl1lintangandikas17.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ptsganjil202111rpl1lintangandikas17.Model.Model;
import com.example.ptsganjil202111rpl1lintangandikas17.R;
import com.example.ptsganjil202111rpl1lintangandikas17.RealmHelper;
import com.squareup.picasso.Picasso;

import java.net.CookieHandler;
import java.util.ArrayList;

import javax.security.auth.callback.Callback;

import io.realm.Realm;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {
    private Context context;
    private Callback callback;
    int posku;
    View viewku;
    private ArrayList<Model> userlist;
    Realm realm;
    RealmHelper realmHelper;
    public interface Callback {
        void onClick(int position);
    }
    public AdapterMain( Context context, ArrayList<Model> userlist, Callback callback) {
        this.context = context;
        this.userlist = userlist;
        this.callback = callback;
    }

    @NonNull
    @Override
    public AdapterMain.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMain.ViewHolder holder, int position) {
        holder.nama.setText(userlist.get(position).getNama());
        holder.tahun.setText(userlist.get(position).getTahun());
        Picasso.get()
                .load(userlist.get(position).getGambar())
                .error(R.mipmap.ic_launcher_round)
                .into(holder.image)
        ;
    }
    @Override
    public int getItemCount() {
        return (userlist != null) ? userlist.size() : 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView nama, tahun;
        CardView contact;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewku = itemView;
            itemView.setOnCreateContextMenuListener(this::onCreateContextMenu);
            this.image = itemView.findViewById(R.id.image);
            this.nama = itemView.findViewById(R.id.nama);
            this.tahun = itemView.findViewById(R.id.tahun);
            this.contact = itemView.findViewById(R.id.contact);

            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getAdapterPosition());
                }
            });
        }
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem Edit = menu.add(Menu.NONE, 1, 1, "Edit");
            MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Delete");

            Edit.setOnMenuItemClickListener(onlickcontextmenu);
            Delete.setOnMenuItemClickListener(onlickcontextmenu);
        }

    }

    private final MenuItem.OnMenuItemClickListener onlickcontextmenu = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case 1:
                    //Do stuff
                    Toast.makeText(viewku.getContext(), "Edit data di posisi "+posku, Toast.LENGTH_SHORT).show();
                    break;

                case 2:
                    //Delete data, butuh konfirmasi dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(viewku.getContext());
                    builder.setMessage("Are you sure you want to delete data?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
//                                    userlist.remove(posku);
                                    Log.d("hh","posisi : "+posku);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            //Set your icon here
                            .setTitle("Delete data")
                            .setIcon(R.mipmap.ic_launcher);
                    AlertDialog alert = builder.create();
                    alert.show();//showing the dialog

                    break;
            }
            return true;
        }
    };
}
