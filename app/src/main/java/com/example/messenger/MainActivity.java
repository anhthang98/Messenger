package com.example.messenger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Contact> contactList;
    RecyclerView recyclerView;
    ContactAdapterDemo contactAdapterDemo;
    View vAdd;
    int mPosition = -1;
    ImageView imgIcon;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvList);
        vAdd = findViewById(R.id.vAdd);
        imgIcon = findViewById(R.id.imgIcon);
        contactList = new ArrayList<>();


        contactList.add(new Contact("Mr A", false));
        contactList.add(new Contact("Mr B", true));
        contactList.add(new Contact("Mr C", false));
        contactList.add(new Contact("Mr D", true));
        contactList.add(new Contact("Mr E", false));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),
                LinearLayoutManager.VERTICAL, false);

        contactAdapterDemo = new ContactAdapterDemo(contactList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactAdapterDemo);

        contactAdapterDemo.setIonClickContact(new IonClickContact() {
            @Override
            public void onClickName(String name, int position) {
            }

            @Override
            public void onClickPhone(Contact contact) {

            }
        });

        contactAdapterDemo.setOnItemClickListener(new ContactAdapterDemo.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, final int position) {

                PopupMenu popupMenu = new PopupMenu(getBaseContext(), itemView);
                popupMenu.getMenuInflater().inflate(R.menu.item_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mnItemFromFile:
                                Intent intent1 = new Intent(getBaseContext(), MessActivity.class);
                                intent1.putExtra("nameMess", contactList.get(position));
                                startActivityForResult(intent1, 19);
                                break;
                            case R.id.mnItemDefault:
                                mPosition = position;
                                Intent intent = new Intent(getBaseContext(), Edit.class);
                                intent.putExtra("name", contactList.get(position));
                                startActivityForResult(intent, 114);

                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();


            }

        });

        vAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Edit.class);
                intent.putExtra("name", "Send");
                startActivityForResult(intent, 115);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_OK:
                String name = data.getStringExtra("nameAdd");
                String mess = data.getStringExtra("mess");
                if (requestCode == 114) {
                    contactList.set(mPosition, new Contact(name, false));
                    contactAdapterDemo.notifyDataSetChanged();

                } else if (requestCode == 115) {
                    contactList.add(new Contact(name, false));
                    contactAdapterDemo.notifyDataSetChanged();
                }else if (requestCode == 19){
                    contactList.add(new Contact(mess, false));
                    contactAdapterDemo.notifyDataSetChanged();
                }
        }

    }
}
