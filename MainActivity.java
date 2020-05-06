package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static TextView Txt1;
    Button btn;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                           @Override
                                           public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                               if (!task.isSuccessful()) {
                                                   Log.w(TAG, "getInstanceId failed", task.getException());
                                                   return;
                                               }

                                               // Get new Instance ID token
                                               String token = task.getResult().getToken();

                                               // Log and toast
                                               String msg = token;
                                               Log.d("TAG", msg);
                                               Log.d("TAG", token);
                                               Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                           }
                                       });

        Txt1 = findViewById(R.id.text);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);

        Txt1.setMovementMethod(new ScrollingMovementMethod());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData process = new fetchData();
                process.execute();
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData2 process = new fetchData2();
                process.execute();
            }

        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = findViewById(R.id.imageview1);
                if (counter == 0) {
                    String imageURL = "http://157.245.185.143/images/chinese-evergreen.jpg";
                    Picasso.get().load(imageURL).into(imageView);
                    counter = 1;
                }
                else {
                    imageView.setImageResource(0);
                    counter = 0;
                }
            }

        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Txt1.setText("");
            }

        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData3 process = new fetchData3();
                process.execute();
            }

        });

    }
}
