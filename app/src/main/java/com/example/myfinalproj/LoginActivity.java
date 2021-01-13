package com.example.myfinalproj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfinalproj.Network.ApiClient;
import com.example.myfinalproj.Network.ApiService;
import com.example.myfinalproj.model.authorization.LoginData;
import com.example.myfinalproj.model.authorization.LoginRequest;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button createButton;
    private Button loginButton;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email_text);
        password = findViewById(R.id.password_text);
        createButton = findViewById(R.id.button_create);
        loginButton = findViewById(R.id.button_login);
        ApiClient apiClient = new ApiClient();
        apiService = apiClient.getApiClient(this);
        createButton.setOnClickListener(v -> {
            Intent regIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(regIntent);
        });

        loginButton.setOnClickListener(v -> {
                    String userEmail = email.getText().toString();
                    String userPassword = password.getText().toString();
                    if (!userEmail.equals("") && !userPassword.equals("")) {
                        login(email.getText().toString(), password.getText().toString());
                    } else Toast.makeText(this, "The field is required", Toast.LENGTH_LONG).show();
                }
        );
    }


    public void login(String email, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        Single<Response<LoginData>> responseSingle = apiService.login(loginRequest);
        responseSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response<LoginData>>() {
                    @Override
                    public void onSuccess(@NonNull Response<LoginData> loginDataResponse) {
                        if (loginDataResponse.isSuccessful()) {
                            SessionManager sessionManager = new SessionManager(LoginActivity.this);
                            sessionManager.saveAuthToken(loginDataResponse.body().getUser().getAuthKey());
                            sessionManager.saveUser(loginDataResponse.body().getUser().getId());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else dispError(loginDataResponse.code());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });

    }

    public void dispError(int code) {
        if (code == 422)
            Toast.makeText(LoginActivity.this, "Password doesn't match with this account", Toast.LENGTH_LONG).show();
    }
}