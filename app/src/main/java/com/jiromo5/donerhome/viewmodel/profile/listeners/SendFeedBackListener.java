package com.jiromo5.donerhome.viewmodel.profile.listeners;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * SendFeedBackListener is a listener that handles the sending of feedback.
 * It manages the visual feedback when a user touches the "send" button
 * and retrieves the subject and message entered by the user.
 */
public class SendFeedBackListener implements View.OnTouchListener {

    private Context context;
    private EditText subject;
    private EditText message;

    // Constructor to initialize the listener with context and EditText fields for subject and message
    public SendFeedBackListener(EditText subject, EditText message, Context context) {
        this.context = context;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewHandler viewHandler = new ViewHandler(context);

        // Handle the touch events to update the button's visual state
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // When the image is pressed, show the pressed state
                viewHandler.setImageOnScreen(v.findViewById(R.id.send), ProfileResources.SEND_FEEDBACK_BUTTON_CLICK);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // When the press is released or canceled, return to the normal state
                viewHandler.setImageOnScreen(v.findViewById(R.id.send), ProfileResources.SEND_FEEDBACK_BUTTON);
                break;
        }

        // Log the subject and message entered by the user (for debugging purposes)
        Log.d("SendFeedBackListener", "Subject: " + subject.getText().toString());
        Log.d("SendFeedBackListener", "Message: " + message.getText().toString());

        // Here, you can call methods to send the feedback to a server or process it further
        // For example:
        // sendFeedback(subject.getText().toString(), message.getText().toString());

        return false;
    }

    // Optionally, you can implement a method for sending the feedback to a server or saving it
    // private void sendFeedback(String subject, String message) {
    //     // Handle sending feedback logic here
    // }
}
