package com.rahuldeewan.smartlearning;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pallav
 * on 11/24/2017.
 */

public class ArticlesFragment extends Fragment {
    WebView wv1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_articles, container, false);
        TextView title = v.findViewById(R.id.articleTitle);
        EditText content = v.findViewById(R.id.articleContent);
        title.setText(getArguments().getString("title"));
        content.setText(getArguments().getString("content"));
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getArguments().getString("link"));
                getActivity().setContentView(R.layout.activity_web_view);
                wv1 = getActivity().findViewById(R.id.webView);
                wv1.setWebViewClient(new MyBrowser());
                wv1.getSettings().setLoadsImagesAutomatically(true);
                wv1.getSettings().setJavaScriptEnabled(true);
                wv1.setWebViewClient(new WebViewClient());
                wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv1.loadUrl(getArguments().getString("link"));
            }

            public boolean onKeyDown(int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && wv1.canGoBack()) {
                    wv1.goBack();
                    return true;
                }
                return true;
            }

            class MyBrowser extends WebViewClient {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            }
        });
        return v;
    }

    public static ArticlesFragment newInstance(int pos, List<Article> articleList) {

        ArticlesFragment f = new ArticlesFragment();
        Bundle b = new Bundle();
        b.putString("title", articleList.get(pos).getTitle());
        b.putString("content", articleList.get(pos).getContent());
        b.putString("link", articleList.get(pos).getLink());
        f.setArguments(b);
        return f;
    }
}

