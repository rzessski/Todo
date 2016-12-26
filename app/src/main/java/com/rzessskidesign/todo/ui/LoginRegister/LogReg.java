package com.rzessskidesign.todo.ui.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.rzessskidesign.todo.App;
import com.rzessskidesign.todo.MainActivity;
import com.rzessskidesign.todo.R;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogReg extends AppCompatActivity {

    @Inject
    LogRegManager logRegManager;
    @BindView(R.id.RegisterEmail)
    EditText RegisterEmail;
    @BindView(R.id.RegisterPassword)
    EditText RegisterPassword;
    @BindView(R.id.LoginPassword)
    EditText LoginPassword;
    @BindView(R.id.LoginEmail)
    EditText LoginEmail;
    @BindView(R.id.LoginButton)
    Button LoginButton;
    @BindView(R.id.RegisterName)
    EditText RegisterName;
    @BindView(R.id.RegisterButton)
    Button RegisterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);
        ButterKnife.bind(this);
        App.component.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logRegManager.onAttach(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        logRegManager.onStop();
    }


    @OnClick({R.id.LoginButton, R.id.RegisterButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.LoginButton:
                boolean loginError = false;
                String logLogin = LoginEmail.getText().toString();
                String logPass = LoginPassword.getText().toString();
                if (logLogin.isEmpty()){
                    LoginEmail.setError("Uzupełnij");
                    loginError=true;
                }
                else if (logPass.isEmpty()){
                    LoginPassword.setError("Uzupełnij");
                    loginError=true;
                }
                if (!loginError) logRegManager.login(logLogin,logPass);
                break;
            case R.id.RegisterButton:
                boolean RegError= false;
                String regLogin = RegisterEmail.getText().toString();
                String regPass = RegisterPassword.getText().toString();
                String regName = RegisterName.getText().toString();
                if (regLogin.isEmpty()){
                    RegisterEmail.setError("Uzupełnij");
                    RegError=true;
                } else
                if (regPass.isEmpty()){
                    RegisterPassword.setError("Uzupełnij");
                    RegError=true;
                } else
                if (regName.isEmpty()){
                    RegisterName.setError("Uzupełnij");
                    RegError=true;
                }
                if (!RegError) logRegManager.register(regName,regLogin,regPass);
                break;
        }
    }
    public void success() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}

