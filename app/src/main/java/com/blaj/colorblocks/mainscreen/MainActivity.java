package com.blaj.colorblocks.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blaj.colorblocks.game.BlocksActivity;
import com.blaj.colorblocks.aboutgame.InfoActivity;
import com.blaj.colorblocks.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPlayPane(View view) {
        Intent playActivity = new Intent(this.getApplicationContext(), BlocksActivity.class);

        startActivity(playActivity);
    }

    public void showInfoPane(View view) {
        Intent infoActivity = new Intent(this.getApplicationContext(), InfoActivity.class);

        startActivity(infoActivity);
    }
}