package ru.systempla.test_web.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import timber.log.Timber;

public class appLinkHandlerActivity extends AppCompatActivity {

    String actCode="", pCode="", key="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        String code = null;
        try {
            code = getIntent().getData().getLastPathSegment();
        } catch (Exception e) {

        }

        if (code == null) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        List<String> params=appLinkData.getPathSegments();


        if (params.size()>0)
            actCode=params.get(0);

        if (params.size()>=2)
            pCode=params.get(1);


        Timber.e(actCode);
        Timber.e(pCode);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        /* assume that user is clicked http://example.com/v/my-user-id actCode is "v", pCode is "my-user-id"  Do now whatever you need. */
    }
}
