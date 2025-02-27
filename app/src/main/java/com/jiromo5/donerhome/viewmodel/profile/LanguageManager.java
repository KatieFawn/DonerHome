package com.jiromo5.donerhome.viewmodel.profile;

import android.content.Context;
import android.widget.ImageButton;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.UserData;
import com.jiromo5.donerhome.data.state.paths.ProfileResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;

public class LanguageManager {

    private Context context;
    private ImageButton englishButton;
    private ImageButton germanyButton;
    private ImageButton polandButton;

    public LanguageManager(Context context, ImageButton englishButton, ImageButton germanyButton, ImageButton polandButton){
        this.context = context;
        this.englishButton = englishButton;
        this.germanyButton = germanyButton;
        this.polandButton = polandButton;
    }

    public void updateState(){
        ViewHandler viewHandler = new ViewHandler(context);
        if (UserData.language.equals("english")){
            viewHandler.setImageOnScreen(englishButton, ProfileResources.ENGLISH_BUTTON_SELECT);
            englishButton.setEnabled(false);
        } else {
            viewHandler.setImageOnScreen(englishButton, ProfileResources.ENGLISH_BUTTON);
        }

        if (UserData.language.equals("germany")){
            viewHandler.setImageOnScreen(germanyButton, ProfileResources.GERMAN_BUTTON_SELECT);
            germanyButton.setEnabled(false);
        } else {
            viewHandler.setImageOnScreen(germanyButton, ProfileResources.GERMAN_BUTTON);
        }

        if (UserData.language.equals("poland")){
            viewHandler.setImageOnScreen(polandButton, ProfileResources.POLAND_BUTTON_SELECT);
            polandButton.setEnabled(false);
        } else {
            viewHandler.setImageOnScreen(polandButton, ProfileResources.POLAND_BUTTON);
        }
    }
}
