package com.jiromo5.donerhome.main.profile.listeners;

import android.view.View;
import android.widget.EditText;

public class SendFeedBackListener implements View.OnClickListener {

    private EditText subject;
    private EditText message;

    public SendFeedBackListener(EditText subject, EditText message){
        this.subject = subject;
        this.message = message;
    }

    @Override
    public void onClick(View view) {
        System.out.println(subject.getText().toString());
        System.out.println(message.getText().toString());
    }

    private void sendRequest(){

    }
}