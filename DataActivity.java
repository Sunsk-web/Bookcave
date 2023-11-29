package com.example.bookscave;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataActivity extends AppCompatActivity {

    private EditText Fullnameedit, emailadressEdt ;
    private Button saveDatabtn;
    FirebaseDatabase firebaseDatabase;
    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    // creating a variable for
    // our object class
    UserData userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Fullnameedit = findViewById(R.id.Username);
        emailadressEdt = findViewById(R.id.Useremail);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserData");
        userInfo = new UserData();
        saveDatabtn = findViewById(R.id.Savebtnn);
        //To support action Bar
        getSupportActionBar().hide();
        saveDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text from our edittext fields.
                String name = Fullnameedit.getText().toString();
                String email = emailadressEdt.getText().toString();
                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(DataActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(name, email);
                }
               startActivity(new Intent(getApplicationContext(),CategoryActivity.class));
            }
        });
    }
    private void addDatatoFirebase(String name, String phone) {
        userInfo.setFullname(name);
        userInfo.setEmailadress(phone);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(userInfo);
                // after adding this data we are showing toast message.
                Toast.makeText(DataActivity.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(DataActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}