package com.example.iquestapp.auth;

import android.net.Uri;

import com.example.iquestapp.model.AccessToken;
import com.example.iquestapp.networking.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.iquestapp.networking.RetrofitService.CLIENT_ID;
import static com.example.iquestapp.networking.RetrofitService.CLIENT_SECRET;
import static com.example.iquestapp.networking.RetrofitService.CODE_FIELD;

public class AuthenticationPresenter {

  private AuthenticationView view;
  private RetrofitService retrofitService;

  public AuthenticationPresenter(AuthenticationView view) {
    this.view = view;
    this.retrofitService = new RetrofitService();
  }

  public void onLoginCallbackReceived(Uri uri) {
    String code = uri.getQueryParameter(CODE_FIELD);
      retrofitService.getAccessToken(CLIENT_ID, CLIENT_SECRET, code, new Callback<AccessToken>() {
      @Override
      public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
        view.onLoginSuccess();
      }

      @Override
      public void onFailure(Call<AccessToken> call, Throwable t) {
        view.onLoginFailed(t.getMessage());
      }
    });
  }
}
