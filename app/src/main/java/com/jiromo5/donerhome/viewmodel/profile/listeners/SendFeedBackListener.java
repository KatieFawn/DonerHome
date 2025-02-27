package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

public class SendFeedBackListener implements View.OnTouchListener {

    private Context context;
    private EditText subject;
    private EditText message;

    public SendFeedBackListener(EditText subject, EditText message, Context context){
        this.context = context;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewHandler viewHandler = new ViewHandler(context);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Когда нажали на изображение, показываем pressed состояние
                viewHandler.setImageOnScreen(v.findViewById(R.id.send), ProfileResources.SEND_FEEDBACK_BUTTON_CLICK);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Когда отпустили или отменили нажатие, возвращаем нормальное состояние
                viewHandler.setImageOnScreen(v.findViewById(R.id.send), ProfileResources.SEND_FEEDBACK_BUTTON);
                break;
        }
        System.out.println(subject.getText().toString());
        System.out.println(message.getText().toString());

        return false;
    }

    private void sendRequest(){

    }
}