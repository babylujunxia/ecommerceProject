package com.example.ecommerce.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ecommerce.R;

public class Message extends Activity {

    private TextView msg;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        msg = (TextView)findViewById(R.id.message);

        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("account");
        msg.setText(data);



    }
}
