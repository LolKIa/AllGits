package com.example.allgits;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //private TextView login;
    private TextView contributors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //login = findViewById(R.id.loginX);
        contributors = findViewById(R.id.contributors);
    }

    public void onClick(View view) {
        GitHubService1 gitHubService = GitHubService1.retrofit.create(GitHubService1.class);
        final Call<List<Contributor>> call =
                gitHubService.repoContributors("square", "picasso");

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                StringBuilder contributorsText = new StringBuilder();
                for (Contributor contributor : response.body()) {
                    contributorsText.append(contributor.toString()).append("\n");
                }
                contributors.setText(contributorsText.toString());
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable throwable) {
                contributors.setText("Что-то пошло не так: " + throwable.getMessage());
            }
        });
    }
}