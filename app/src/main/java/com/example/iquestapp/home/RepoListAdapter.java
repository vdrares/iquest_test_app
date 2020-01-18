package com.example.iquestapp.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iquestapp.R;
import com.example.iquestapp.model.GithubRepo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoListViewHolder> {

  private List<GithubRepo> repos;
  private RepoItemClickListener repoItemClickListener;

  public RepoListAdapter(List<GithubRepo> repos, RepoItemClickListener repoItemClickListener) {
    this.repos = repos;
    this.repoItemClickListener = repoItemClickListener;
  }

  @NonNull
  @Override
  public RepoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item_view, parent, false);
    return new RepoListViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RepoListViewHolder holder, int position) {
    holder.name.setText(repos.get(position).getName());
    holder.author.setText(repos.get(position).getAuthor().getName());
    holder.rating.setText(String.valueOf(repos.get(position).getStars()));

    holder.itemView.setOnClickListener(v -> repoItemClickListener.onItemClicked(repos.get(position).getUrl()));
  }

  @Override
  public int getItemCount() {
    return repos.size();
  }

  public class RepoListViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.repo_name) TextView name;
    @BindView(R.id.repo_author) TextView author;
    @BindView(R.id.rating) TextView rating;

    public RepoListViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public interface RepoItemClickListener {
    void onItemClicked(String url);
  }

}
