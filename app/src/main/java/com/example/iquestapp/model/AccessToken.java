package com.example.iquestapp.model;

import com.google.gson.annotations.SerializedName;

public class AccessToken {

  @SerializedName("access_token")
  public String accessToken;

  @SerializedName("token_type")
  public String tokenType;
}
