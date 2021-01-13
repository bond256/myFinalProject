package com.example.myfinalproj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfinalproj.Network.ApiClient;
import com.example.myfinalproj.Network.ApiService;
import com.example.myfinalproj.model.authorization.CreateData;
import com.example.myfinalproj.model.authorization.CreateRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private Button registrationButton;
    private EditText userName;
    private EditText email;
    private EditText password;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registrationButton = findViewById(R.id.button_registrarion);
        userName = findViewById(R.id.userNameText);
        email = findViewById(R.id.registrationEmail);
        password = findViewById(R.id.registertionPassword);
        ApiClient apiClient = new ApiClient();
        apiService = apiClient.getApiClient(this);

        registrationButton.setOnClickListener(v -> {
            String name = userName.getText().toString();
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();
            if (!name.equals("") && !userEmail.equals("") && !userPassword.equals("")) {
                createNewUser(name, userEmail, userPassword);
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
            } else Toast.makeText(this, "The field is required", Toast.LENGTH_LONG).show();
        });

    }

    public void createNewUser(String userName, String email, String password) {
        CreateRequest createUserData = new CreateRequest();
        createUserData.setEmail(email);
        createUserData.setPassword(password);
        createUserData.setUsername(userName);
        createUserData.setDeviceToken("dfshjgsehjgeshjjse");
        createUserData.setOsType("android");
        apiService.createUser(createUserData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSingleObserver<Response<CreateData>>() {
                    @Override
                    public void onSuccess(@NonNull Response<CreateData> createDataResponse) {
                        if (createDataResponse.isSuccessful()) {
                            SessionManager sessionManager = new SessionManager(RegistrationActivity.this);
                            sessionManager.saveAuthToken(createDataResponse.body().getData().getAuthKey());
                            sessionManager.saveUser(createDataResponse.body().getData().getId());
                        } else dispError(createDataResponse.code());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }


    public void dispError(int code) {
        if (code == 422)
            Toast.makeText(this, "User with the following Email is already registered in the system", Toast.LENGTH_LONG).show();
    }
}