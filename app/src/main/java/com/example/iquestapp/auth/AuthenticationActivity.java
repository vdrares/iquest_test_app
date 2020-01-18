package com.example.iquestapp.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iquestapp.IQuestAppActivity;
import com.example.iquestapp.R;
import com.example.iquestapp.home.RepoListActivity;
import com.example.iquestapp.model.AccessToken;

import butterknife.BindView;

import static com.example.iquestapp.networking.RetrofitService.AUTH_URI;
import static com.example.iquestapp.networking.RetrofitService.REDIRECT_URI;

public class AuthenticationActivity extends IQuestAppActivity implements AuthenticationView {

  @BindView(R.id.github_login_btn) Button loginBtn;
  @BindView(R.id.auth_progress) ProgressBar progressBar;

  private AuthenticationPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_authentication);
    presenter = new AuthenticationPresenter(this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    loginBtn.setOnClickListener(v -> {
      Intent githubLoginIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(AUTH_URI));
      startActivity(githubLoginIntent);
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    Uri uri = getIntent().getData();

    if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
      progressBar.setVisibility(android.view.View.VISIBLE);
      loginBtn.setVisibility(android.view.View.GONE);
      presenter.onLoginCallbackReceived(uri);
    }
  }

  @Override
  public void onLoginFailed(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void onLoginSuccess() {
    Intent listIntent = new Intent(AuthenticationActivity.this, RepoListActivity.class);
    startActivity(listIntent);
    finish();
  }
}
