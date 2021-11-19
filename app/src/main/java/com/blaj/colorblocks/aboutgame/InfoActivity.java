package com.blaj.colorblocks.aboutgame;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blaj.colorblocks.databinding.ActivityInfoBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityInfoBinding binding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
    }
}