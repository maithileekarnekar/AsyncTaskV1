package com.androidwavelength.asynctaskv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
    }

    private void setupListeners() {
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String localPath = Util.download("https://bitcode.in/file1.zip");

                String[] fileUrls = {
                        "https://bitcode.in/file1",
                        "https://bitcode.in/file2",
                        "https://bitcode.in/file3",
                };

                DownloadThread downloadThread =
                        new DownloadThread(MainActivity.this);
                downloadThread.setOnProgressListener(new DownloadProgressListener());
                downloadThread.execute(fileUrls);
            }
        });
    }
    private void initViews() {
        btnDownload = findViewById(R.id.btnDownload);
    }

    private class DownloadProgressListener implements DownloadThread.OnProgressListener {
        @Override
        public void onProgress(int progress) {
            btnDownload.setText(progress + "%");
        }
    }
}