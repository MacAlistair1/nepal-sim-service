package com.mis.community.nepalsimservices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class feedBack extends Activity {

    EditText msg;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        send = (Button) findViewById(R.id.send);
        msg = (EditText) findViewById(R.id.msg);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = "replymisclive@gmail.com";
                String message = msg.getText().toString();

                msg.setError("Please write something first!!!");
                if (!message.isEmpty() || !message.equals("")){
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.setType("message/rfc822");
                    email.putExtra(Intent.EXTRA_EMAIL, mail);
                    email.putExtra(Intent.EXTRA_SUBJECT, "FeedBack To MIS Community");
                    email.putExtra(Intent.EXTRA_TEXT, message);
                    startActivity(Intent.createChooser(email, "Send FeedBack using..."));
                }

            }
        });
    }
}
