package com.example.iquestapp.networking;

import com.example.iquestapp.model.AccessToken;
import com.example.iquestapp.model.RepoListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.example.iquestapp.networking.RetrofitService.CLIENT_ID_FIELD;
import static com.example.iquestapp.networking.RetrofitService.CLIENT_SECRET_FIELD;
import static com.example.iquestapp.networking.RetrofitService.CODE_FIELD;
import static com.example.iquestapp.networking.RetrofitService.LOGIN_POST_URL;
import static com.example.iquestapp.networking.RetrofitService.ORDER_FIELD;
import static com.example.iquestapp.networking.RetrofitService.REQUEST_JSON_HEADER;
import static com.example.iquestapp.networking.RetrofitService.SEARCH_QUERY_FIELD;
import static com.example.iquestapp.networking.RetrofitService.SORT_FIELD;

public interface GithubClient {

  @Headers(REQUEST_JSON_HEADER)
  @POST(LOGIN_POST_URL)
  @FormUrlEncoded
  Call<AccessToken> login(
      @Field(CLIENT_ID_FIELD) String clientId,
      @Field(CLIENT_SECRET_FIELD) String clientSecret,
      @Field(CODE_FIELD) String code
  );

  @Headers(REQUEST_JSON_HEADER)
  @GET("/search/repositories")
  Call<RepoListResponse> getRepos(
      @Query(SEARCH_QUERY_FIELD) String query,
      @Query(SORT_FIELD) String sortType,
      @Query(ORDER_FIELD) String orderType
  );

}
