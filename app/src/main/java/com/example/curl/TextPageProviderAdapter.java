package com.example.curl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

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

    public String getItem(int index, boolean isBack) {
        if (isBack) {
            return backDatas.get(index);
        } else {
            return datas.get(index);
        }
    }

    @Override
    public boolean isDrawable(int index, boolean isBack) {
        if (isBack) {
            return (backDatas != null) && (backDatas.size() > index) && (backDatas.get(index) != null);
        } else {
            return (datas != null) && (datas.size() > index) && (datas.get(index) != null);
        }
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

    public boolean isShouldRotation(boolean isBack) {
        return isRotation && isBack && (viewMode == CurlRenderer.SHOW_TWO_PAGES);
    }

    public void drawBitmap(Canvas c, Rect r, int index, boolean isBack) {
        String data = getItem(index, isBack);

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

    public void drawBorder(Canvas c, Rect r, int index, boolean isBack) {
        Rect leftR = new Rect();
        leftR.left = r.left;
        leftR.right = r.left + getBorder();
        leftR.top = r.top;
        leftR.bottom = r.bottom;

        Rect topR = new Rect();
        topR.left = r.left;
        topR.right = r.right;
        topR.top = r.top;
        topR.bottom = r.top + getBorder();

        Rect rightR = new Rect();
        rightR.left = r.right - getBorder();
        rightR.right = r.right;
        rightR.top = r.top;
        rightR.bottom = r.bottom;

        Rect bottomR = new Rect();
        bottomR.left = r.left;
        bottomR.right = r.right;
        bottomR.top = r.bottom - getBorder();
        bottomR.bottom = r.bottom;

        Paint p = new Paint();
        p.setColor(getBorderColor());
        c.drawRect(leftR, p);
        c.drawRect(topR, p);
        c.drawRect(rightR, p);
        c.drawRect(bottomR, p);
    }

    @Override
    public int getPageCount() {
//        return datas == null ? 0 : datas.size();
        return 10;
    }
}
