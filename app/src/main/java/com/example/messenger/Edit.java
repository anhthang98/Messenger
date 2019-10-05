package com.example.messenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Edit extends AppCompatActivity {
    String newName, mNumber;
    EditText etName, etPhone;
    Button btnAction;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnAction = findViewById(R.id.btnAction);

        final Intent intent = getIntent();

        contact = (Contact) intent.getSerializableExtra("name");

        newName = contact.getName();
        if (newName.equals("Send")) btnAction.setText("Send");
        else {
            btnAction.setText("Update");
            etName.setText(newName);
        }

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("nameAdd", etName.getText().toString());

                //trả về kết quả Result cho mainActivity
                setResult(RESULT_OK, intent1);
                finish();
            }
        });

    }
}
