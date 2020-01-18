package com.example.iquestapp.auth;

public interface AuthenticationView {
  void onLoginFailed(String message);
  void onLoginSuccess();
}
