package com.example.iquestapp.model;

import com.google.gson.annotations.SerializedName;

public class RepoOwner {

  @SerializedName("login")
  private String name;

  public String getName() {
    return name;
  }
}
