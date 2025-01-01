package com.jiromo5.donerhome.activities.main.menu;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.main.menu.listeners.BackToMenuListener;

import java.util.HashSet;

public class BurgerActivity extends AppCompatActivity {

    private ImageButton backButton;

    private HashSet<String> listOf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);
        overridePendingTransition(0, 0);

        backButton = findViewById(R.id.back_button);

        setButtonClickListeners();
    }

    private void setButtonClickListeners(){
        backButton.setOnClickListener(new BackToMenuListener(this));
    }
}