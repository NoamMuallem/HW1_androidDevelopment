package com.gmail.noammuallem_hw1;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Game extends AppCompatActivity {

    //progress bar for the 2 players
    private ProgressBar game_pb_p1;
    private ProgressBar game_pb_p2;
    private Button[][] attackButtons = new Button [2][3];
    private boolean player1turn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        viewsInitialize();
        //game initialize
        player1turn = true;
        //set turn of player 1
        enablePlayerButtons(1);
    }

    /**
     *
     * @param numPlayer - the player number that is attacking now
     *                  enable and disable attack buttons accordingly
     */
    private void enablePlayerButtons(int numPlayer){
        for (int i = 0 ; i < attackButtons.length ; i++){
            for(int j = 0 ; j < attackButtons[i].length ; j++ ){
                if(i == numPlayer-1){
                    attackButtons[i][j].setEnabled(true);
                }else{
                    attackButtons[i][j].setEnabled(false);
                }
            }
        }
    }

    /*
    setting the elements references and event listeners and setting up the game
     */
    private void viewsInitialize (){
        //progressbar initialize
        game_pb_p1 = findViewById(R.id.game_pb_p1);
        game_pb_p2 = findViewById(R.id.game_pb_p2);

        //attack buttons initialize
        //i+1 is the player
        //j+1 is the number AND the power of the attack
        for (int i = 0 ; i < attackButtons.length ; i++) {
            for (int j = 0; j < attackButtons[i].length; j++) {
                //constructing the string of the button tag
                String button_id = "game_btn_p" + (i + 1) + "a" + (j + 1);
                //obtaining the button actual ID
                int resID = getResources().getIdentifier(button_id, "id", getPackageName());
                attackButtons[i][j] = findViewById(resID);
                //setting variables for onClickListener
                final int power = j + 1;
                final int attackPlayer = i == 0 ? 2 : 1;
                attackButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (attackPlayer == 1) {
                            game_pb_p1.setProgress(game_pb_p1.getProgress() - power);
                            if(game_pb_p1.getProgress()<=0){
                                gameEnds(2);
                            }else{
                                enablePlayerButtons(1);
                            }
                        } else {
                            game_pb_p2.setProgress(game_pb_p2.getProgress() - power);
                            if(game_pb_p2.getProgress()<= 0 ){
                                gameEnds(1);
                            }else{
                                enablePlayerButtons(2);
                            }
                        }
                    }
                });
            }
        }
    }

    /**
     *
     * @param winner - the number of player that won the game
     *               presenting a winning massage
     */
    private void gameEnds(int winner){

        for (int i = 0 ; i < attackButtons.length ; i++){
            for(int j = 0 ; j < attackButtons[i].length ; j++ ){
                attackButtons[i][j].setEnabled(false);
            }
        }
        AlertDialog alertDialog = new AlertDialog.Builder(Activity_Game.this).create();
        alertDialog.setTitle("game over!");
        alertDialog.setMessage("congratulation player " +winner);
        alertDialog.show();
    }
}
