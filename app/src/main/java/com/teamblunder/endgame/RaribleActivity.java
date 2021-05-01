package com.teamblunder.endgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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

    }
}