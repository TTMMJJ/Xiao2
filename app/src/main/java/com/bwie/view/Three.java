package com.bwie.view;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bwie.bean.R;
import com.bwie.green.DownloadUtil;
import com.bwie.widget.media.IRenderView;
import com.bwie.widget.media.IjkVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Three extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.videoview)
    IjkVideoView videoView;
    @BindView(R.id.textView)
    TextView total;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.delete)
    Button pause;
    private int max;
    private DownloadUtil mDownloadUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        ButterKnife.bind(this);
        total = findViewById(R.id.textView);
        start = findViewById(R.id.start);
        pause = findViewById(R.id.delete);
        mProgressBar = findViewById(R.id.progressBar);
        final String urlString = getIntent().getStringExtra("path");
        final String localPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/local";
        mDownloadUtil = new DownloadUtil(2, localPath, "adc.mp4", urlString,
                this);
        mDownloadUtil.setOnDownloadListener(new DownloadUtil.OnDownloadListener() {
            @Override
            public void downloadStart(int fileSize) {
                max = fileSize;
                mProgressBar.setMax(fileSize);
            }

            @Override
            public void downloadProgress(int downloadedSize) {
                mProgressBar.setProgress(downloadedSize);
                total.setText((int) downloadedSize * 100 / max + "%");
            }
            @Override
            public void downloadEnd() {
                videoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
                videoView.setVideoURI(Uri.parse(localPath+"/adc.mp4"));
                videoView.start();
            }
        });
    }

    @OnClick({R.id.start, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                mDownloadUtil.start();
                break;
            case R.id.delete:
                mDownloadUtil.pause();
                break;
        }
    }
}
