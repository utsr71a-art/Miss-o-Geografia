package br.com.missaogeografia;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        web = new WebView(this);
        web.setBackgroundColor(Color.parseColor("#10284a"));
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        web.setWebViewClient(new WebViewClient());
        setContentView(web);
        web.loadUrl("file:///android_asset/index.html");
    }

    // Botão voltar do Android: volta para a tela anterior do jogo; na tela inicial, fecha o app
    @Override
    public void onBackPressed() {
        String js = "(function(){var on=function(i){return document.getElementById(i).classList.contains('on')};"
            + "if(on('game')){document.getElementById('home').click();return 1}"
            + "if(on('end')){document.getElementById('again').click();return 1}"
            + "if(on('sum')){document.getElementById('sback').click();return 1}"
            + "return 0})()";
        web.evaluateJavascript(js, value -> {
            if (!"1".equals(value)) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        web.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        web.onResume();
    }
}
