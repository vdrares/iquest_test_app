package com.example.iquestapp.networking;

import com.example.iquestapp.model.AccessToken;
import com.example.iquestapp.model.RepoListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

  static final String REQUEST_JSON_HEADER = "Accept: application/json";
  static final String LOGIN_POST_URL = "login/oauth/access_token";

  public static final String CLIENT_ID = "c75d4c419a2dff54c410";
  public static final String CLIENT_SECRET = "5f8d452e8aa5ab92c8b895bcbe857881bddfe5fb";
  public static final String REDIRECT_URI = "iquestapp://callback";
  private static final String REPO_ACCESS_PARAM = "&scope=repo";
  private static final String REDIRECT_URI_PARAM = "&redirect_uri=";

  public static final String CODE_FIELD = "code";
  static final String CLIENT_ID_FIELD = "client_id";
  static final String CLIENT_SECRET_FIELD = "client_secret";
  static final String SEARCH_QUERY_FIELD = "q";
  static final String SORT_FIELD = "sort";
  static final String ORDER_FIELD = "order";
  private static final String ORDER_DESC = "desc";
  private static final String SORT_BY_STARS = "stars";
  private static final String LANGUAGE_FIELD = "+language:";
  private static final String ALL = "all";

  private static final String BASE_URL = "https://github.com/";
  private static final String BASE_API_URL = "https://api.github.com";
  public static final String AUTH_URI = "https://github.com/login/oauth/authorize?client_id="
      + CLIENT_ID
      + REPO_ACCESS_PARAM
      + REDIRECT_URI_PARAM
      + REDIRECT_URI;

  public void getAccessToken(String clientId, String clientSecret, String code, final Callback<AccessToken> callback) {
    Retrofit.Builder builder = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create());

    GithubClient client = builder.build().create(GithubClient.class);
    Call<AccessToken> accessTokenCall = client.login(clientId, clientSecret, code);

    accessTokenCall.enqueue(new Callback<AccessToken>() {
      @Override
      public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
        if (response.body() != null) {
          callback.onResponse(call, response);
        }
      }

      @Override
      public void onFailure(Call<AccessToken> call, Throwable throwable) {
        callback.onFailure(call, throwable);
      }
    });
  }

  public void searchRepos(String query, String language, final Callback<RepoListResponse> callback) {
    Retrofit.Builder builder = new Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create());

    GithubClient client = builder.build().create(GithubClient.class);
    final String requestQuery = language.equalsIgnoreCase(ALL) ? query : query + LANGUAGE_FIELD + language;
    Call<RepoListResponse> reposListCall = client.getRepos(requestQuery, SORT_BY_STARS, ORDER_DESC);

    reposListCall.enqueue(new Callback<RepoListResponse>() {
      @Override
      public void onResponse(Call<RepoListResponse> call, Response<RepoListResponse> response) {
        callback.onResponse(call, response);
      }

      @Override
      public void onFailure(Call<RepoListResponse> call, Throwable t) {
        callback.onFailure(call, t);
      }
    });
  }
}
