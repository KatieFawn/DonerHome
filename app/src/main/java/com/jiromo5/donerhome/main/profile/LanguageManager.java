package com.jiromo5.donerhome.main.profile;

import android.widget.ImageButton;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.UserData;

public class LanguageManager {

    private ImageButton englishButton;
    private ImageButton germanyButton;
    private ImageButton polandButton;

    public LanguageManager(ImageButton englishButton, ImageButton germanyButton, ImageButton polandButton){
        this.englishButton = englishButton;
        this.germanyButton = germanyButton;
        this.polandButton = polandButton;
    }

    public void updateState(){
        if (UserData.language.equals("english")){
            englishButton.setImageResource(R.drawable.english_button_select);
            englishButton.setEnabled(false);
        } else {
            englishButton.setImageResource(R.drawable.english_button);
        }

        if (UserData.language.equals("germany")){
            germanyButton.setImageResource(R.drawable.german_button_select);
            germanyButton.setEnabled(false);
        } else {
            germanyButton.setImageResource(R.drawable.german_button);
        }

        if (UserData.language.equals("poland")){
            polandButton.setImageResource(R.drawable.poland_button_select);
            polandButton.setEnabled(false);
        } else {
            polandButton.setImageResource(R.drawable.poland_button);
        }
    }
}
