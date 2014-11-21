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
public class TextPageProviderAdapter implements CurlView.PageProvider {
    private int viewMode = CurlRenderer.SHOW_ONE_PAGE;
    private int margin = 10;
    private int padding = 20;
    private int border = 5;
    private int borderColor = 0xFF000000;
    private int background = 0xFFC0C0C0;
    private boolean isRotation = true;
    private ArrayList<String> datas;
    private ArrayList<String> backDatas;

    public TextPageProviderAdapter(int margin, int padding, int border, int borderColor, int background, ArrayList<String> datas, ArrayList<String> backDatas) {
        this.margin = margin;
        this.padding = padding;
        this.border = border;
        this.borderColor = borderColor;
        this.background = background;
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

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public void setBackground(int background) {
        this.background = background;
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

    public int getMargin() {
        return margin;
    }

    public int getPadding() {
        return padding;
    }

    public int getBorder() {
        return border;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public int getBackground() {
        return background;
    }

    public void drawTextBitmap(Canvas c, Rect r, int index) {
        drawTextBitmap(c, r, index, false);
    }

    public boolean isShouldRotation(boolean isBack) {
        return isRotation && isBack && (viewMode == CurlRenderer.SHOW_TWO_PAGES);
    }

    public void drawTextBitmap(Canvas c, Rect r, int index, boolean isBack) {
        String data = null;
        if (isBack) {
            data = backDatas.get(index);
        } else {
            data = datas.get(index);
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(28f);
        if (isShouldRotation(isBack)) {
            Matrix matrix = new Matrix();
            float matrixValues[] = {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
            matrix.setValues(matrixValues);
            matrix.postTranslate(c.getWidth(), 0);
            c.setMatrix(matrix);
        }
        StaticLayout staticLayout = new StaticLayout(data, 0, data.length(), textPaint, r.width(), Layout.Alignment.ALIGN_NORMAL, 1.3f, 0.0f, false);
        c.translate(r.left, r.top);
        staticLayout.draw(c);

    }

    public void drawBorder(Canvas c, Rect r, int index) {
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
    private Bitmap loadTextBitmap(int width, int height, int index, boolean isBack){
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        b.eraseColor(0xFFFFFFFF);
        Canvas c = new Canvas(b);

        int margin = getMargin();
        int border = getBorder();
        Rect r = new Rect(margin, margin, width - margin, height - margin);

        Paint p = new Paint();
        p.setColor(getBackground());
        c.drawRect(r, p);

        if (border != 0) {
            drawBorder(c , r, index);
        }

        r.left += (border + getPadding());
        r.right -= (border + getPadding());
        r.top += (border + getPadding());
        r.bottom -= (border - getPadding());
        if (isBack) {
            drawTextBitmap(c, r, index, true);
        } else {
            drawTextBitmap(c, r, index);
        }
        return b;
    }

    private Bitmap loadTextBitmap(int width, int height, int index) {
        return loadTextBitmap(width, height, index, false);
    }

    @Override
    public int getPageCount() {
        return datas == null ? 0 : datas.size();
    }

    public void updateBackPage(CurlPage page, int width, int height, int index) {
        if (viewMode == CurlRenderer.SHOW_TWO_PAGES) {
            if (backDatas != null && index < backDatas.size()) {
                Bitmap back = loadTextBitmap(width, height, index, true);
                page.setTexture(back, CurlPage.SIDE_BACK);
            } else {
                page.setColor(Color.rgb(255, 255, 255), CurlPage.SIDE_BACK);
            }
        } else {
            page.setColor(Color.argb(100, 255, 255, 255), CurlPage.SIDE_BACK);
        }
    }

    public void updateFrontPage(CurlPage page, int width, int height, int index) {
        Bitmap front = loadTextBitmap(width, height, index);
        if (viewMode == CurlRenderer.SHOW_TWO_PAGES) {
            page.setTexture(front, CurlPage.SIDE_FRONT);
        } else {
            page.setTexture(front, CurlPage.SIDE_BOTH);
        }
    }

    @Override
    public void updatePage(CurlPage page, int width, int height, int index) {
        if (index >= 0 && index < getPageCount()) {
            updateFrontPage(page, width, height, index);
            updateBackPage(page, width, height, index);
        }
    }

    @Override
    public void updateViewMode(int viewMode) {
        this.viewMode = viewMode;
    }
}
