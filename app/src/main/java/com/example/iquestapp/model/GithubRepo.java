package com.example.iquestapp.model;

import com.google.gson.annotations.SerializedName;

public class GithubRepo {

  public int id;

  public String name;

  @SerializedName("owner")
  public RepoOwner author;

  @SerializedName("stargazers_count")
  public int stars;

  @SerializedName("html_url")
  public String url;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public RepoOwner getAuthor() {
    return author;
  }

  public int getStars() {
    return stars;
  }

  public String getUrl() {
    return url;
  }
}
