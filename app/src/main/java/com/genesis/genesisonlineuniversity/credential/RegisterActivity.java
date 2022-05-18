package com.genesis.genesisonlineuniversity.credential;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.genesis.genesisonlineuniversity.R;

public class RegisterActivity extends AppCompatActivity {

    boolean passwordVissible;
    private TextView tv_Terms_conditions, tv_sign_up, tv_sign_in;
    private EditText etFullName, etEmail, etPhoneNumber, etPassword;
    private boolean nameIsEmpty = true;
    private boolean emailIsEmpty = true;
    private boolean phoneIsEmpty = true;
    private boolean passwordIsEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initStatusBar();
        initComponents();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initComponents() {

        tv_sign_up = findViewById(R.id.tv_sign_up);
        tv_sign_in = findViewById(R.id.tv_sign_in);
        tv_Terms_conditions = findViewById(R.id.tv_Terms_conditions);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);

        /////terms conditions starts
        String text = "By continuing you agree to our Terms & Conditions and Privacy Policy";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(RegisterActivity.this, "Terms & Conditions", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorPrimary2));
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(RegisterActivity.this, "Privacy Policy", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.colorPrimary2));
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan1, 31, 50, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan2, 54, 68, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_Terms_conditions.setText(ss);
        tv_Terms_conditions.setMovementMethod(LinkMovementMethod.getInstance());
        /////terms conditions ends

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


        etFullName.addTextChangedListener(new TextWatcher() {

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
                    etFullName.setBackground(getResources().getDrawable(R.drawable.rounded_corner4));
                    etFullName.setPadding(45, 45, 45, 45);
                    nameIsEmpty = false;
                    checkSignupButtonActive();

                } else {
                    etFullName.setBackground(getResources().getDrawable(R.drawable.rounded_corner3));
                    etFullName.setPadding(45, 45, 45, 45);
                    nameIsEmpty = true;
                    checkSignupButtonActive();
                }

            }
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
                    etEmail.setPadding(45, 45, 45, 45);
                    emailIsEmpty = false;
                    checkSignupButtonActive();
                } else {
                    etEmail.setBackground(getResources().getDrawable(R.drawable.rounded_corner3));
                    etEmail.setPadding(45, 45, 45, 45);
                    emailIsEmpty = true;
                    checkSignupButtonActive();
                }

            }
        });


        etPhoneNumber.addTextChangedListener(new TextWatcher() {
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
                    etPhoneNumber.setBackground(getResources().getDrawable(R.drawable.rounded_corner4));
                    etPhoneNumber.setPadding(45, 45, 45, 45);
                    phoneIsEmpty = false;
                    checkSignupButtonActive();
                } else {
                    etPhoneNumber.setBackground(getResources().getDrawable(R.drawable.rounded_corner3));
                    etPhoneNumber.setPadding(45, 45, 45, 45);
                    phoneIsEmpty = true;
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
                    etPassword.setPadding(45, 45, 45, 45);
                    passwordIsEmpty = false;
                    checkSignupButtonActive();
                } else {
                    etPassword.setBackground(getResources().getDrawable(R.drawable.rounded_corner3));
                    etPassword.setPadding(45, 45, 45, 45);
                    passwordIsEmpty = true;
                    checkSignupButtonActive();
                }

            }
        });


        tv_sign_up.setOnClickListener(view -> {

        });

        tv_sign_in.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


    }

    private void checkSignupButtonActive() {
        if (!nameIsEmpty && !emailIsEmpty && !phoneIsEmpty && !passwordIsEmpty) {
            tv_sign_up.setBackground(getResources().getDrawable(R.drawable.rounded_corner1));
            tv_sign_up.setPadding(45, 40, 45, 40);
        } else {
            tv_sign_up.setBackground(getResources().getDrawable(R.drawable.rounded_corner5));
            tv_sign_up.setPadding(45, 40, 45, 40);
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