package com.example.iquestapp;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public class IQuestAppActivity extends AppCompatActivity {

  @Override
  protected void onStart() {
    super.onStart();
    ButterKnife.bind(this);
  }
}
