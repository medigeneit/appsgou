package com.genesis.genesisonlineuniversity.credential;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.genesis.genesisonlineuniversity.R;
import com.genesis.genesisonlineuniversity.favourite_topic_pick.FavouriteTopicPickActivity;

public class LoginActivity extends AppCompatActivity {

    boolean passwordVissible;
    private ImageView iv_hi;
    private TextView tv_Terms_conditions, tv_sign_up, tv_sign_in, tv_forgot_password;
    private EditText etFullName, etEmail, etPhoneNumber, etPassword;
    private boolean nameIsEmpty = true;
    private boolean emailIsEmpty = true;
    private boolean phoneIsEmpty = true;
    private boolean passwordIsEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initStatusBar();
        initComponents();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initComponents() {

        tv_sign_up = findViewById(R.id.tv_sign_up);
        tv_sign_in = findViewById(R.id.tv_sign_in);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        tv_Terms_conditions = findViewById(R.id.tv_Terms_conditions);

        iv_hi = findViewById(R.id.iv_hi);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);

//        /////terms conditions starts
//        String text = "By continuing you agree to our Terms & Conditions and Privacy Policy";
//        SpannableString ss = new SpannableString(text);
//
//        ClickableSpan clickableSpan1 = new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(LoginActivity.this, "Terms & Conditions", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setColor(getResources().getColor(R.color.colorPrimary2));
//                ds.setUnderlineText(false);
//            }
//        };
//
//        ClickableSpan clickableSpan2 = new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(LoginActivity.this, "Privacy Policy", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//                ds.setColor(getResources().getColor(R.color.colorPrimary2));
//                ds.setUnderlineText(false);
//            }
//        };
//
//        ss.setSpan(clickableSpan1, 31, 50, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ss.setSpan(clickableSpan2, 54, 68, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        tv_Terms_conditions.setText(ss);
//        tv_Terms_conditions.setMovementMethod(LinkMovementMethod.getInstance());
//        /////terms conditions ends

        etPassword.setOnTouchListener((view, motionEvent) -> {
            final int DRAWABLE_RIGHT = 2;
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (motionEvent.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    int selection = etPassword.getSelectionEnd();
                    if (passwordVissible) {
//                        etPassword.setInputType(InputType.TYPE_CLASS_TEXT |
//                                InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(this.getResources().getDrawable(R.drawable.ic_lock), null, this.getResources().getDrawable(R.drawable.ic_show), null);
//                        etPassword.setSelection(etPassword.getText().length());
                        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passwordVissible = false;
                    } else {
                        etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(this.getResources().getDrawable(R.drawable.ic_lock), null, this.getResources().getDrawable(R.drawable.ic_show2), null);
                        etPassword.setSelection(etPassword.getText().length());
                        passwordVissible = true;
                    }
                    etPassword.setSelection(selection);

                    return true;
                }
            }
            return false;
        });



        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    etEmail.setBackground(getResources().getDrawable(R.drawable.rounded_corner4));
                    etEmail.setPadding(45, 40, 45, 40);
                    emailIsEmpty = false;
                    checkSignupButtonActive();
                } else {
                    etEmail.setBackground(getResources().getDrawable(R.drawable.rounded_corner3));
                    etEmail.setPadding(45, 40, 45, 40);
                    emailIsEmpty = true;
                    checkSignupButtonActive();
                }

            }
        });


        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    etPassword.setBackground(getResources().getDrawable(R.drawable.rounded_corner4));
                    etPassword.setPadding(45, 40, 45, 40);
                    passwordIsEmpty = false;
                    checkSignupButtonActive();
                } else {
                    etPassword.setBackground(getResources().getDrawable(R.drawable.rounded_corner3));
                    etPassword.setPadding(45, 40, 45, 40);
                    passwordIsEmpty = true;
                    checkSignupButtonActive();
                }

            }
        });


        tv_sign_up.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });


        tv_forgot_password.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        });

        iv_hi.setOnClickListener(view -> {

        });
        iv_hi.startAnimation(AnimationUtils.loadAnimation(this,R.anim.anim_shake));


    }

    private void checkSignupButtonActive() {
        if (!emailIsEmpty && !passwordIsEmpty) {
            tv_sign_in.setBackground(getResources().getDrawable(R.drawable.rounded_corner1));
            tv_sign_in.setPadding(45, 45, 45, 45);

            tv_sign_in.setOnClickListener(view -> {
                Intent intent = new Intent(LoginActivity.this, FavouriteTopicPickActivity.class);
                startActivity(intent);
            });

        } else {
            tv_sign_in.setBackground(getResources().getDrawable(R.drawable.rounded_corner5));
            tv_sign_in.setPadding(45, 45, 45, 45);
        }
    }

    


    private void initStatusBar() {
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.background, this.getTheme()));
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.background));
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }



}