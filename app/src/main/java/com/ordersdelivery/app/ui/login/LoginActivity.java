package com.ordersdelivery.app.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ordersdelivery.R;
import com.ordersdelivery.app.Common;
import com.ordersdelivery.app.ui.base.BaseActivity;
import com.ordersdelivery.app.ui.main.MainActivity;
import com.ordersdelivery.app.utils.Constants;
import com.ordersdelivery.app.utils.language.Language;
import com.ordersdelivery.databinding.ActivityLoginBinding;
import com.ordersdelivery.domain.model.request.LoginValueBody;

import dagger.hilt.android.AndroidEntryPoint;
import io.paperdb.Paper;

@AndroidEntryPoint
public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private LoginValueBody.Value valueBody;
    private String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
        setUpObservers();
        setUpListeners();

    }

    private void initView() {
        valueBody = new LoginValueBody.Value();
        binding.setLang(getLang());
        if (getLang().equals("en")) {
            valueBody.setP_LANG_NO("2");
            binding.constraintEnglish.setBackground(getDrawable(R.drawable.rounded_green2_stroke_green3));
            binding.constraintArabic.setBackground(getDrawable(R.drawable.rounded_white_stroke_blue));
        } else {
            valueBody.setP_LANG_NO("1");
            binding.constraintArabic.setBackground(getDrawable(R.drawable.rounded_green2_stroke_green3));
            binding.constraintEnglish.setBackground(getDrawable(R.drawable.rounded_white_stroke_blue));
        }

        binding.setModel(valueBody);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    private void setUpObservers() {

        viewModel.getOnLoginSuccess().observe(this, user -> {
            if (user.getResult().getErrNo() == 0) {
                setUserModel(user);
                Toast.makeText(this, user.getResult().getErrMsg(), Toast.LENGTH_LONG).show();
                navigateToMainActivity();
            } else {
                Toast.makeText(this, user.getResult().getErrMsg(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constants.user_id, binding.getModel().getP_DLVRY_NO());
        startActivity(intent);
        finish();
    }

    private void setUpListeners() {
        binding.userNameEditText.requestFocus();
        binding.btnLogin.setOnClickListener(view -> {
            Common.CloseKeyBoard(this, binding.userNameEditText);
            Common.CloseKeyBoard(this, binding.passwordEditText);
            if (valueBody.isDataValid(this)) {
                viewModel.login(this, new LoginValueBody(valueBody));
            } else {
                Toast.makeText(this, R.string.data_uncompleted, Toast.LENGTH_SHORT).show();
            }
        });

        binding.icLang.setOnClickListener(v -> binding.flLang.setVisibility(View.VISIBLE));
        binding.constraintArabic.setOnClickListener(view -> {
            binding.constraintArabic.setBackground(getDrawable(R.drawable.rounded_green2_stroke_green3));
            binding.constraintEnglish.setBackground(getDrawable(R.drawable.rounded_white_stroke_blue));
            lang = "ar";
        });
        binding.constraintEnglish.setOnClickListener(view -> {
            binding.constraintEnglish.setBackground(getDrawable(R.drawable.rounded_green2_stroke_green3));
            binding.constraintArabic.setBackground(getDrawable(R.drawable.rounded_white_stroke_blue));
            lang = "en";
        });

        binding.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.flLang.setVisibility(View.GONE);
                refreshActivity(lang);
            }
        });
    }

    public void refreshActivity(String lang) {
        Paper.book().write("lang", lang);
        Language.setNewLocale(this, lang);
        new Handler()
                .postDelayed(() -> {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }, 500);
    }
}