package com.example.myfinalproj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfinalproj.Network.ApiClient;
import com.example.myfinalproj.Network.ApiService;
import com.example.myfinalproj.db.DbService;
import com.example.myfinalproj.model.authorization.LogoutRequest;
import com.example.myfinalproj.model.user.User;
import com.example.myfinalproj.model.user.UserData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    private ApiService apiService;
    private TextView userName;
    private TextView userEmail;
    private TextView countFavVideo;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        SessionManager sessionManager = new SessionManager(this);
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        countFavVideo = findViewById(R.id.count_favorite_video);
        logoutButton = findViewById(R.id.logout_button);
        ApiClient apiClient = new ApiClient();
        apiService = apiClient.getApiClient(this);
        getUser(sessionManager.getUser());
        logoutButton.setOnClickListener(v -> {
            logout();
            sessionManager.logoutClear();
            DbService dbService = new DbService(this);
            dbService.delete();
            Intent regIntent = new Intent(this, LoginActivity.class);
            regIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(regIntent);
        });
    }

    public void getUser(int id) {
        apiService.getUser(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSingleObserver<Response<UserData>>() {
                    @Override
                    public void onSuccess(@NonNull Response<UserData> userDataResponse) {
                        if (userDataResponse.isSuccessful()) {
                            User user = userDataResponse.body().getData().getUser();
                            userName.setText(user.getUsername());
                            userEmail.setText(user.getEmail());
                            countFavVideo.setText(user.getFavouriteVideosCount());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }


    public void logout() {
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setDeviceToken("dfshjgsehjgeshjjse");
        logoutRequest.setOsType("android");
        apiService.logout(logoutRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        dispError(e.getLocalizedMessage());
                    }
                });
    }

    public void dispError(String code) {
        if (code.equals("401"))
            Toast.makeText(this, "Unauthorized", Toast.LENGTH_LONG).show();
    }

}