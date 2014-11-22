package com.example.curl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/11/22.
 */
public abstract class BasePageProviderAdapter implements CurlView.PageProvider {

    public int margin = 10;
    public int padding = 20;
    public int border = 5;
    public int borderColor = 0xFF000000;
    public int background = 0xFFC0C0C0;

    public BasePageProviderAdapter() {

    }

    public BasePageProviderAdapter(int margin, int padding, int border, int borderColor, int background) {
        this.margin = margin;
        this.padding = padding;
        this.border = border;
        this.borderColor = borderColor;
        this.background = background;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int viewMode = CurlRenderer.SHOW_ONE_PAGE;

    public abstract Object getItem(int index, boolean isBack);

    public abstract void drawBitmap(Canvas c, Rect r, int index, boolean isBack);

    public abstract void drawBorder(Canvas c, Rect r, int index, boolean isBack);

    public abstract boolean isDrawable(int index, boolean isBack);

    public Bitmap loadBitmap(int width, int height, int index, boolean isBack) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        b.eraseColor(0xFFFFFFFF);
        Canvas c = new Canvas(b);

        int margin = getMargin();
        int border = getBorder();
        Rect r = new Rect(margin, margin, width - margin, height - margin);

        Paint p = new Paint();
        p.setColor(getBackground());
        c.drawRect(r, p);

        if (!isDrawable(index, isBack)) {
            return b;
        }

        if (border != 0) {
            drawBorder(c , r, index, isBack);
        }

        r.left += (border + getPadding());
        r.right -= (border + getPadding());
        r.top += (border + getPadding());
        r.bottom -= (border + getPadding());

        if (isBack) {
            drawBitmap(c, r, index, true);
        } else {
            drawBitmap(c, r, index, false);
        }
        return b;
    }

    public void updateFrontPage(CurlPage page, int width, int height, int index) {
        Bitmap front = loadBitmap(width, height, index, false);
        if (viewMode == CurlRenderer.SHOW_TWO_PAGES) {
            page.setTexture(front, CurlPage.SIDE_FRONT);
        } else {
            page.setTexture(front, CurlPage.SIDE_BOTH);
        }
    }

    public void updateBackPage(CurlPage page, int width, int height, int index) {
        if (viewMode == CurlRenderer.SHOW_TWO_PAGES) {
            Bitmap back = loadBitmap(width, height, index, true);
            page.setTexture(back, CurlPage.SIDE_BACK);
        } else {
            page.setColor(Color.argb(100, 255, 255, 255), CurlPage.SIDE_BACK);
        }
    }

    @Override
    public void updateViewMode(int viewMode) {
        this.viewMode = viewMode;
    }

    public int getViewMode() {
        return viewMode;
    }

    @Override
    public void updatePage(CurlPage page, int width, int height, int index) {
        if (index >= 0 && index < getPageCount()) {
            updateFrontPage(page, width, height, index);
            updateBackPage(page, width, height, index);
        }
    }

    protected final void updateCanvasLRSymmetry(Canvas c) {
        Matrix matrix = new Matrix();
        float matrixValues[] = {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
        matrix.setValues(matrixValues);
        matrix.postTranslate(c.getWidth(), 0);
        c.setMatrix(matrix);
    }
}
