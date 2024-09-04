package com.jiromo5.donerhome.utils;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class LinkClickHandler {

    private SpannableString spannableString;
    private TextView signUpTextView;
    private Context context;
    private String text;
    private int startLink;
    private Class<?> nextActivity;

    public LinkClickHandler(TextView signUpTextView, Context context, String text){
        this.signUpTextView = signUpTextView;
        this.context = context;
        this.text = text;

        spannableString = new SpannableString(text);
    }

    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(@NonNull View view) {
            Intent intent = new Intent(context, nextActivity);
            context.startActivity(intent);
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(true);
        }
    };

    public void configure(){
        spannableString.setSpan(clickableSpan, startLink, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpTextView.setText(spannableString);
        signUpTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setNextActivity(Class<?> activity){
        nextActivity = activity;
    }

    public void setStartLink(int start){
        startLink = start;
    }
}
