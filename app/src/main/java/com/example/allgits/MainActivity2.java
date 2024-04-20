package com.example.allgits;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private TextView mTextView;
    private EditText te;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        mTextView = (TextView) findViewById(R.id.textView);
        te = (EditText) findViewById(R.id.editText);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
    }


    public void onClick(View view) {
        mProgressBar.setVisibility(View.VISIBLE);

        GitHubService2 gitHubService = GitHubService2.retrofit.create(GitHubService2.class);
        final Call<User> call =
                gitHubService.getUser(te.getText().toString());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // response.isSuccessfull() is true if the response code is 2xx
                if (response.isSuccessful()) {
                    User user = response.body();

                    // Получаем json из github-сервера и конвертируем его в удобный вид
                    mTextView.setText(
                            "Repos: " + user.getPublicRepos() +
                            "\nEmail: " + user.getAvatarUrl() +
                            "\nLogin: " + user.getLogin() +
                            "\nUrl: " + user.getReposUrl());

                    mProgressBar.setVisibility(View.INVISIBLE);
                } else {
                    int statusCode = response.code();

                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();
                    try {
                        mTextView.setText(errorBody.string());
                        mProgressBar.setVisibility(View.INVISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                mTextView.setText("Что-то пошло не так: " + throwable.getMessage());
            }
        });
    }
}