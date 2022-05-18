package com.genesis.genesisonlineuniversity.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.genesis.genesisonlineuniversity.R;

public class WebViewActivity extends AppCompatActivity {

    String url = "";
    String for_whom = "";
    private WebView mWebview;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        url = getIntent().getStringExtra("url");
        for_whom = getIntent().getStringExtra("for_whom");

        initToolbar();

        loadWebPage(url);
    }

    private void loadWebPage(String url) {
        mWebview = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final WebViewActivity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

//        mWebview.loadUrl(url);
        setContentView(mWebview);

        startWebView(url);
    }

    private void startWebView(String url) {

        WebSettings settings = mWebview.getSettings();

        settings.setJavaScriptEnabled(true);
        mWebview.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.getSettings().setUseWideViewPort(true);
        mWebview.getSettings().setLoadWithOverviewMode(true);

        progressDialog = new ProgressDialog(WebViewActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebViewActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });
//        mWebview.loadUrl(url);
        mWebview.loadUrl(url + "?player_id=player&autoplay=1&title=0&byline=0&portrait=0&api=1&fullscreen&allowfullscreen");
    }


    private void initToolbar() {
        ActionBar ab = ((AppCompatActivity) this).getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        String title = this.getResources().getString(R.string.app_name);
        ab.setTitle(title);

        if (for_whom.equalsIgnoreCase("complain_box")) {
            ab.setTitle("Complain Box");
        } else if (for_whom.equalsIgnoreCase("blog")) {
            ab.setTitle("Blog");
        } else if (for_whom.equalsIgnoreCase("research_paper")) {
            ab.setTitle("Research Paper");
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    onBackPressed();
                }
//                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}