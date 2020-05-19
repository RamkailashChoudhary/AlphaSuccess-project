package com.app.alphasucess.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alphasucess.BaseActivity;
import com.app.alphasucess.R;

import androidx.annotation.Nullable;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class PDFViewActivity extends BaseActivity implements DownloadFile.Listener{

    private FrameLayout rootPdfView;
    private PDFPagerAdapter adapter;
    private RemotePDFViewPager remotePDFViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_view);
        TextView header=findViewById(R.id.middleTitle);
        header.setText("Online PDF");
        final ImageView backBtnView = findViewById(R.id.backBtnView);
        rootPdfView = findViewById(R.id.rootPdfView);
        remotePDFViewPager = new RemotePDFViewPager(this, "http://demo1.stsm.co.in/Content/Uploads/PDF/PDF.pdf", this);
        backBtnView.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        Toast.makeText(PDFViewActivity.this,"DOWNLOADED DONE",Toast.LENGTH_LONG).show();
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(adapter);
        updateLayout();
    }

    @Override
    public void onFailure(Exception e) {
        Toast.makeText(PDFViewActivity.this,"Failed :"+e.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProgressUpdate(int progress, int total) {
        Log.d("PDFView","Progress :"+progress);
    }

    public void updateLayout() {
        rootPdfView.removeAllViewsInLayout();
        rootPdfView.addView(remotePDFViewPager,
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.close();
        }
    }
}
