package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MessActivity extends AppCompatActivity {
    ArrayList<String> friendList;
    Spinner spType;
    Button btnSend;
    EditText edtTitle, edtDescription;
    Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess);
        edtDescription = findViewById(R.id.edtDescription);
        spType = findViewById(R.id.spType);
        btnSend = findViewById(R.id.btnSend);
        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("nameMess");

        friendList = new ArrayList<>();
        friendList.add(contact.getName());


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1, friendList);
        spType.setAdapter(arrayAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("mess", edtDescription.getText().toString());

                //trả về kết quả Result cho mainActivity
                setResult(RESULT_OK, intent1);
                finish();
            }
        });
    }
}
