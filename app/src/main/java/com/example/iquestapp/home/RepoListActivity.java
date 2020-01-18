package com.example.iquestapp.home;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iquestapp.IQuestAppActivity;
import com.example.iquestapp.R;
import com.example.iquestapp.model.GithubRepo;

import java.util.List;

public class RepoListActivity extends IQuestAppActivity implements RepoListView {

  @BindView(R.id.search_btn) Button searchBt;
  @BindView(R.id.language_spinner) Spinner languageSpinner;
  @BindView(R.id.search_bar) EditText searchBar;
  @BindView(R.id.repo_list) RecyclerView repoRecyclerView;
  @BindView(R.id.search_repos_text) TextView searchHint;
  @BindView(R.id.list_load_progress) ProgressBar listLoadProgress;

  private RepoListPresenter presenter;
  private RepoListAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repo_list);
  }

  @Override
  protected void onStart() {
    super.onStart();
    setup();
    searchBt.setOnClickListener(v -> {
      searchHint.setVisibility(View.GONE);
      listLoadProgress.setVisibility(View.VISIBLE);
      final String query = searchBar.getText().toString();
      final String language = languageSpinner.getSelectedItem().toString();
      presenter.search(query, language);
    });
  }

  public void setup() {
    presenter = new RepoListPresenter(this);
    RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
    repoRecyclerView.setLayoutManager(manager);
  }

  @Override
  public void onResponse(List<GithubRepo> items) {
    adapter = new RepoListAdapter(items, url -> {
      Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
      startActivity(browserIntent);
    });
    repoRecyclerView.setAdapter(adapter);
    listLoadProgress.setVisibility(View.GONE);
    repoRecyclerView.setVisibility(View.VISIBLE);
  }

  @Override
  public void onFailure(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }
}
