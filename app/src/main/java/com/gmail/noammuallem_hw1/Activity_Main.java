package com.gmail.noammuallem_hw1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Main extends AppCompatActivity {
    private Button main_BTN_start;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        main_BTN_start = findViewById(R.id.main_BTN_start);

        if(main_BTN_start==null){
            Log.d("pttt","null pointer for button");
        }else

        main_BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Activity_Main.this, Activity_Game.class);
                    Activity_Main.this.startActivity(myIntent);
                }catch(Exception e){
                    Log.d("pttt", e.toString());
                }
            }
        });
    }
}
