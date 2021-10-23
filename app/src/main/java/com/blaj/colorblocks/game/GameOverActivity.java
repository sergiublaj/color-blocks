package com.blaj.colorblocks.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blaj.colorblocks.R;

public class GameOverActivity extends AppCompatActivity {
    private String userName;
    private int userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        getUsername();
    }

    private void getUsername() {
        EditText userInput = new EditText(this);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.username_text))
                .setCancelable(false)
                .setView(userInput)
                .setPositiveButton("Submit", (DialogInterface.OnClickListener) (dialog, which) -> {
                    userName = userInput.getText().toString();

                    showPodium();
                });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    private void showPodium() {
        userScore = getIntent().getIntExtra("score", userScore);

        TextView firstPodium = findViewById(R.id.podium1);
        String firstPlace = String.format(getResources().getString(R.string.podium1), userName, userScore);
        firstPodium.setText(firstPlace);

        TextView secondPodium = findViewById(R.id.podium2);
        String secondPlace = String.format(getResources().getString(R.string.podium2), userScore / 2);
        secondPodium.setText(secondPlace);

        TextView thirdPodium = findViewById(R.id.podium3);
        String thirdPlace = String.format(getResources().getString(R.string.podium3), userScore / 3);
        thirdPodium.setText(thirdPlace);

        Handler handler = new Handler();
        handler.postDelayed(() -> setContentView(R.layout.activity_main), 10000);
    }
}