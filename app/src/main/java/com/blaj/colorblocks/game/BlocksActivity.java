package com.blaj.colorblocks.game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blaj.colorblocks.R;

import java.util.ArrayList;
import java.util.List;

public class BlocksActivity extends AppCompatActivity {
    private boolean isPlayerTurn = false;
    private int crtTurn = 0;
    private int crtLevel = 1;
    private int crtScore = 0;
    private int crtClick = 0;
    private final List<Button> buttonList = new ArrayList<>();
    private final List<Integer> randomButtons = new ArrayList<>();
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocks);

        setupGame();

        continueGame();
    }

    private void setupGame() {
        buttonList.add(findViewById(R.id.button_1));
        buttonList.add(findViewById(R.id.button_2));
        buttonList.add(findViewById(R.id.button_3));
        buttonList.add(findViewById(R.id.button_4));
        buttonList.add(findViewById(R.id.button_5));
        buttonList.add(findViewById(R.id.button_6));
        buttonList.add(findViewById(R.id.button_7));
        buttonList.add(findViewById(R.id.button_8));
        buttonList.add(findViewById(R.id.button_9));
        buttonList.add(findViewById(R.id.button_10));
        buttonList.add(findViewById(R.id.button_11));
        buttonList.add(findViewById(R.id.button_12));
        buttonList.add(findViewById(R.id.button_13));
        buttonList.add(findViewById(R.id.button_14));
        buttonList.add(findViewById(R.id.button_15));
        buttonList.add(findViewById(R.id.button_16));

        colorButtons(-1, Color.GRAY, Color.GRAY);

        updateLevelUI();
        updateScoreUI();
    }

    private void colorButtons(int crtButton, int crtColor, int defaultColor) {
        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).setBackgroundColor(defaultColor);
        }

        if (crtButton != -1) {
            buttonList.get(crtButton).setBackgroundColor(crtColor);
        }
    }

    private void updateLevelUI() {
        TextView levelUI = findViewById(R.id.level_value);

        levelUI.setText(String.format(getResources().getString(R.string.current_stage), crtLevel));
    }

    private void updateScoreUI() {
        TextView scoreUI = findViewById(R.id.score_value);

        scoreUI.setText(String.format(getResources().getString(R.string.current_stage), crtScore));
    }

    private void enableButtons(boolean isEnabled) {
        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).setClickable(isEnabled);
        }
    }

    private void continueGame() {
        if (isPlayerTurn) {
            playerTurn();
        } else {
            computerTurn();
        }
    }

    private final Runnable editTiles = new Runnable() {
        @Override
        public void run() {
            int randomBtn = (int) (Math.random() * 1000) % buttonList.size();
            randomButtons.add(buttonList.get(randomBtn).getId());
            buttonList.get(randomBtn).setBackgroundColor(Color.GREEN);

            if (crtTurn++ < crtLevel) {
                handler.postDelayed(() -> colorButtons(-1, Color.GRAY, Color.GRAY), 1000);
                handler.postDelayed(this, 1500);
            } else {
                colorButtons(-1, Color.GRAY, Color.GRAY);
                handler.removeCallbacks(this);
                crtTurn = 0;
                isPlayerTurn = true;
                continueGame();
            }
        }
    };

    private void computerTurn() {
        randomButtons.clear();

        handler.postDelayed(() -> colorButtons(-1, Color.GRAY, Color.GRAY), 500);

        enableButtons(false);

        handler.postDelayed(editTiles, 1000);
    }

    private void playerTurn() {
        enableButtons(true);
    }

    public void handleClick(View view) {
        int btnId = view.getId();
        if (randomButtons.indexOf(btnId) == crtClick) {
            colorButtons(findButtonId(btnId), Color.BLUE, Color.GRAY);
            handler.postDelayed(() -> colorButtons(-1, Color.GRAY, Color.GRAY), 500);
            crtScore += 15 + crtClick + crtLevel;
            updateScoreUI();
        } else {
            endGame();
            return;
        }

        if (++crtClick >= crtLevel) {
            isPlayerTurn = false;
            crtClick = 0;
            crtLevel++;
            updateLevelUI();
            continueGame();
        }
    }

    private int findButtonId(int btnId) {
        int foundId = -1;

        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i).getId() == btnId) {
                return i;
            }
        }

        return foundId;
    }

    private void endGame() {
        colorButtons(-1, Color.GRAY, Color.RED);

        enableButtons(false);

        Intent endGame = new Intent(this.getApplicationContext(), GameOverActivity.class);
        endGame.putExtra("score", crtScore);

        handler.postDelayed(() -> startActivity(endGame), 2000);
    }
}