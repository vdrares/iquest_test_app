package com.example.iquestapp.home;

import com.example.iquestapp.model.RepoListResponse;
import com.example.iquestapp.networking.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoListPresenter {

  private RepoListView view;
  private RetrofitService retrofitService;

  public RepoListPresenter(RepoListView view) {
    this.view = view;
    retrofitService = new RetrofitService();
  }

  public void search(String query, String language) {
    retrofitService.searchRepos(query, language, new Callback<RepoListResponse>() {
      @Override
      public void onResponse(Call<RepoListResponse> call, Response<RepoListResponse> response) {
        if (response.body() != null) {
          view.onResponse(response.body().getItems());
        }
      }

      @Override
      public void onFailure(Call<RepoListResponse> call, Throwable t) {
        view.onFailure(t.getMessage());
      }
    });
  }
}
