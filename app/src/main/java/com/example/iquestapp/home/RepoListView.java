package com.example.iquestapp.home;

import com.example.iquestapp.model.GithubRepo;

import java.util.List;

public interface RepoListView {
  void onResponse(List<GithubRepo> items);
  void onFailure(String message);
}
