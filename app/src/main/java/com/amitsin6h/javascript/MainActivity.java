package com.amitsin6h.javascript;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private WebView Wv;
    private TextView myTextView;
    final Handler myHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Wv = (WebView)findViewById(R.id.webView1);
        myTextView = (TextView)findViewById(R.id.textView1);
        //final JavaScriptInterface myJavaScriptInterface = new JavaScriptInterface(this);



         class WebAppInterface {
            Context mContext;

            /** Instantiate the interface and set the context */
            WebAppInterface(Context c) {
                mContext = c;
            }

            /** Show a toast from the web page */
            @JavascriptInterface
            public void showToast(String webmsg) {
                final String msgeToast = webmsg;
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // This gets executed on the UI thread so it can safely modify Views
                        myTextView.setText(msgeToast);
                    }
                });

                Toast.makeText(mContext, webmsg, Toast.LENGTH_SHORT).show();
            }
        }

        Wv.getSettings().setLightTouchEnabled(true);
        Wv.getSettings().setJavaScriptEnabled(true);
        Wv.addJavascriptInterface(new WebAppInterface(this), "AndroidFunction");
        Wv.loadUrl("file:///assets/index.html");




    }
}
