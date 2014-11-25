package com.example.curl.adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import com.example.curl.CurlPage;
import com.example.curl.CurlRenderer;

import java.util.ArrayList;

/**
 * Created by fc on 14-11-21.
 */
public class TextPageProviderAdapter extends BasePageProviderAdapter {

    private boolean isRotation = true;
    private ArrayList<String> datas;
    private ArrayList<String> backDatas;

    public TextPageProviderAdapter(int margin, int padding, int border, int borderColor, int background, ArrayList<String> datas, ArrayList<String> backDatas) {
        super(margin, padding, border, borderColor, background);
        this.datas = datas;
        this.backDatas = backDatas;
    }

    public void add(String data) {
        datas.add(data);
    }

    public void addBackData(String backData) {
        backDatas.add(backData);
    }

    public void setRotation(boolean isRotation) {
        this.isRotation = isRotation;
    }

    public String getItem(final int index, boolean isBack) {
        if (isBack) {
            return backDatas.get(index);
        } else {
            return datas.get(index);
        }
    }

    @Override
    public boolean isEnaleDrawed(int index, boolean isBack) {
//        if (isBack) {
//            return (backDatas != null) && (backDatas.size() > index) && (backDatas.get(index) != null);
//        } else {
//            return (datas != null) && (datas.size() > index) && (datas.get(index) != null);
//        }
        return super.isEnaleDrawed(index, isBack);
    }

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
    }

    public void setBackDatas(ArrayList<String> backDatas) {
        this.backDatas = backDatas;
    }

    public TextPageProviderAdapter(ArrayList<String> data) {
        this.datas = data;
    }
    public TextPageProviderAdapter() {

    }

    public boolean isShouldRotation(boolean isBack) {
        return isRotation && isBack && (viewMode == CurlRenderer.SHOW_TWO_PAGES);
    }

    public void drawBitmap(final Canvas c, Rect r, final int index, boolean isBack) {
        Log.i("CURLVIEW", "drawBitmap--" + index);
        String data = getItem(index, isBack);
        if (data == null) {
            return ;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(28f);
        if (isShouldRotation(isBack)) {
            updateCanvasLRSymmetry(c);
        }
        StaticLayout staticLayout = new StaticLayout(data, 0, data.length(), textPaint, r.width(), Layout.Alignment.ALIGN_NORMAL, 1.3f, 0.0f, false);
        c.translate(r.left, r.top);
        staticLayout.draw(c);
    }

    @Override
    public void updatePage(CurlPage page, int width, int height, int index) {
//        Log.i("CURLVIEW", "updatePage--index:" + index + "--currentIndex:" + curlView.getCurrentIndex());
        super.updatePage(page, width, height, index);
    }

    @Override
    public int getPageCount() {
        return datas == null ? 0 : datas.size();
//        return 10;
    }
}
