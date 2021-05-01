package com.teamblunder.endgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class RaribleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rarible);

        WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadData("<nft-card\n" +
                "    contractAddress=\"0xd07dc4262bcdbf85190c01c996b4c06a461d2430\"\n" +
                "    tokenId=\"526016\">\n" +
                "    </nft-card>\n" +
                "    <script src=\"https://unpkg.com/embeddable-nfts/dist/nft-card.min.js\"></script>", "text/html", "UTF-8");

        TextView timeTextView = findViewById(R.id.timeTextView);
        new CountDownTimer(5200, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeTextView.setText((int) millisUntilFinished / 1000 + "s");

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(RaribleActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        };
    }
}