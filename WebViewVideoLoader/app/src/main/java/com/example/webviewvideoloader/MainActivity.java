package com.example.webviewvideoloader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    ImageButton check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check = (ImageButton) findViewById(R.id.check);

        webView = (WebView) findViewById(R.id.loadArea);


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()) {
                    if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
                        Toast.makeText(getApplicationContext(), "Connected to Wi-Fi", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Connected to Mobile Network", Toast.LENGTH_SHORT).show();
                    webView.loadUrl("https://www.youtube.com/");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Not connected to internet", Toast.LENGTH_SHORT).show();
                    webView.loadUrl("file:///android_asset/myFile.html");
                }
            }
        });

    }
}