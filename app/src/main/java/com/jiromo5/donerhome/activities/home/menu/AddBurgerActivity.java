package com.jiromo5.donerhome.activities.home.menu;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.jiromo5.donerhome.R;
import com.jiromo5.donerhome.data.state.paths.BurgerResources;
import com.jiromo5.donerhome.viewmodel.ViewHandler;
import com.jiromo5.donerhome.viewmodel.menu.CheeseBurgerOrderState;
import com.jiromo5.donerhome.viewmodel.menu.ItemsBurgerManager;
import com.jiromo5.donerhome.viewmodel.menu.listeners.AddOrderListener;
import com.jiromo5.donerhome.viewmodel.menu.listeners.BackToBurgerListener;
import com.jiromo5.donerhome.viewmodel.menu.listeners.CloseOrderMessageListener;
import com.jiromo5.donerhome.viewmodel.menu.listeners.MinusItemListener;
import com.jiromo5.donerhome.viewmodel.menu.listeners.PlusItemListener;

public class AddBurgerActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TextView nameOfItem;
    private ImageButton plusCount;
    private ImageButton minusCount;
    private EditText countOfItem;
    private ImageButton addToOrderButton;
    private ImageView completeOrderMessage;
    private ConstraintLayout currentLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CheeseBurgerOrderState.clearState();

        if (ItemsBurgerManager.isCheeseBurgerButtonClicked) {
            setContentView(R.layout.burger_order_activity);
            currentLayout = findViewById(R.id.burger_order);
        }
        overridePendingTransition(0, 0);

        backButton = findViewById(R.id.back_button);
        nameOfItem = findViewById(R.id.name_of_item);

        plusCount = findViewById(R.id.plus_item);
        minusCount = findViewById(R.id.minus_item);
        countOfItem = findViewById(R.id.count_items);
        addToOrderButton = findViewById(R.id.add_to_order);
        completeOrderMessage = findViewById(R.id.complete_message);

        setView();

        setButtonClickListeners();

    }

    private void setButtonClickListeners(){
        backButton.setOnClickListener(new BackToBurgerListener(this));

        minusCount.setOnClickListener(new MinusItemListener(countOfItem));
        plusCount.setOnClickListener(new PlusItemListener(countOfItem));
        addToOrderButton.setOnTouchListener(new AddOrderListener(this,completeOrderMessage, addToOrderButton));
        currentLayout.setOnTouchListener(new CloseOrderMessageListener(completeOrderMessage));


    }

    private void setView(){
        ViewHandler viewHandler = new ViewHandler(this);
        viewHandler.setImageOnScreen(findViewById(R.id.back_button), BurgerResources.BACK_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.cheeseburger_image), BurgerResources.CHEESEBURGER_IMAGE);
        viewHandler.setImageOnScreen(findViewById(R.id.plus_item), BurgerResources.PLUS_QUANTITY_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.minus_item), BurgerResources.MINUS_QUANTITY_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.add_to_order), BurgerResources.ADD_ORDER_BUTTON);
        viewHandler.setImageOnScreen(findViewById(R.id.complete_message), BurgerResources.ORDER_MESSAGE_IMAGE);
    }
}