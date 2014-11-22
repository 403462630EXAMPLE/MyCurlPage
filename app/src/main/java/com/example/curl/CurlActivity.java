/*
   Copyright 2012 Harri Smatt

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.example.curl;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

/**
 * Simple Activity for curl testing.
 * 
 * @author harism
 */
public class CurlActivity extends Activity {
    private TextPageProviderAdapter textPageProviderAdapter;
    private ImagePageProviderAdapter imagePageProviderAdapter;
	private CurlView mCurlView;
    private ArrayList<String> contents = new ArrayList<String>();
    private ArrayList<String> backContents = new ArrayList<String>();
    private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    private ArrayList<Bitmap> backBitmaps = new ArrayList<Bitmap>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        contents.add("默认不给TextView设置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根据文字大小适应，或者谁能告诉我，TextView是在哪个地方将文本画到canvas上的。默认不给TextView设置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根据文字大小适应，或者谁能告诉我，TextView是在哪个地方将文本画到canvas上的。默认不给TextView设置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根据文字大小适应，或者谁能告诉我，TextView是在哪个地方将文本画到canvas上的。");
        contents.add("第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页");
        contents.add("第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页");
        contents.add("第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页");
        contents.add("11111111111111111111那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根");
        contents.add("22222222222222222边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根");
        contents.add("33333333333333333置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根");

        backContents.add("默认不给TextView设置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根据文字大小适应，或者谁能告诉我，TextView是在哪个地方将文本画到canvas上的。默认不给TextView设置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根据文字大小适应，或者谁能告诉我，TextView是在哪个地方将文本画到canvas上的。默认不给TextView设置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根据文字大小适应，或者谁能告诉我，TextView是在哪个地方将文本画到canvas上的。");
        backContents.add("第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页");
        backContents.add("第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页");
        backContents.add("第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页");
        backContents.add("默认不给TextView设置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根");

        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.obama));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.road_rage));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.taipei_101));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.world));

        backBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.world));
        backBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.taipei_101));
        backBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.road_rage));
        backBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.obama));
        backBitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.icon));

        int index = 0;
		if (getLastNonConfigurationInstance() != null) {
			index = (Integer) getLastNonConfigurationInstance();
		}
        textPageProviderAdapter = new TextPageProviderAdapter(contents);
        imagePageProviderAdapter = new ImagePageProviderAdapter(bitmaps);
        imagePageProviderAdapter.setBackDatas(backBitmaps);
        textPageProviderAdapter.setBackDatas(backContents);
		mCurlView = (CurlView) findViewById(R.id.curl);
		mCurlView.setPageProvider(textPageProviderAdapter);
//        mCurlView.setPageProvider(imagePageProviderAdapter);

		mCurlView.setSizeChangedObserver(new SizeChangedObserver());
		mCurlView.setCurrentIndex(index);
		mCurlView.setBackgroundColor(0xFF202830);

		// This is something somewhat experimental. Before uncommenting next
		// line, please see method comments in CurlView.
		// mCurlView.setEnableTouchPressure(true);
	}

	@Override
	public void onPause() {
		super.onPause();
		mCurlView.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		mCurlView.onResume();
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return mCurlView.getCurrentIndex();
	}

	/**
	 * Bitmap provider.
	 */
	private class PageProvider implements CurlView.PageProvider {

		// Bitmap resources.
		private int[] mBitmapIds = { R.drawable.obama, R.drawable.road_rage,
				R.drawable.taipei_101, R.drawable.world };
		// Bitmap resources.
		private String[] contents = new String[]{
				"默认不给TextView设置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根据文字大小适应，或者谁能告诉我，TextView是在哪个地方将文本画到canvas上的。默认不给TextView设置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根据文字大小适应，或者谁能告诉我，TextView是在哪个地方将文本画到canvas上的。默认不给TextView设置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根据文字大小适应，或者谁能告诉我，TextView是在哪个地方将文本画到canvas上的。",
				"第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页第二页",
				"第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页第三页",
				"第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页第四页",
                "默认不给TextView设置内边距，那么像什么paddingTop的值就是0，通过getPaddingTop()获取到的值就是0，但是实际情况却是这是一个TextView，文本内容的顶部和TextView的顶部有一段距离，但是这时候通过getPaddingTop()获取到的值确实是0，而且这个距离会随着文字字体大小变化而变化，字体越大，距离越大，现在的问题是有没有办法去掉这个距离，我知道给paddingTop设为负数，但是，我想重写一个TextView，让它自动根"
		};

        public int getMargin() {
            return 10;
        }

        public int getPadding() {
            return 20;
        }

        public int getBorder() {
            return 0;
        }

        public int getBackground() {
            return 0xFFC0C0C0;
        }

        public void drawTextBitmap(Canvas c, Rect r, int index) {
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(28f);
            StaticLayout staticLayout = new StaticLayout(contents[index], 0, contents[index].length(), textPaint, r.width(), Layout.Alignment.ALIGN_NORMAL, 1.3f, 0.0f, false);
            c.translate(r.left, r.top);
            staticLayout.draw(c);
        }

		@Override
		public int getPageCount() {
			return 5;
		}
		
		private Bitmap loadTextBitmap(int width, int height, int index) {
			Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			b.eraseColor(0xFFFFFFFF);
			Canvas c = new Canvas(b);

			int margin = getMargin();//边距
			int border = getBorder();//边框
			Rect r = new Rect(margin, margin, width - margin, height - margin);

            Paint p = new Paint();
            p.setColor(getBackground());
            c.drawRect(r, p);

            Log.i("CURLVIEW", r.left + "***" + r.right + "***" + r.top + "***" + r.bottom);
            r.left += (border + getPadding());
			r.right -= (border + getPadding());
			r.top += (border + getPadding());
			r.bottom -= (border - getPadding());
            Log.i("CURLVIEW", r.left + "***" + r.right + "***" + r.top + "***" + r.bottom);
            drawTextBitmap(c, r, index);
			return b;
		}

		private Bitmap loadBitmap(int width, int height, int index) {
			Bitmap b = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			b.eraseColor(0xFFFFFFFF);
			Canvas c = new Canvas(b);
			Drawable d = getResources().getDrawable(mBitmapIds[index]);

			int margin = 70;//边距
			int border = 30;//边框
			Rect r = new Rect(margin, margin, width - margin, height - margin);

			int imageWidth = r.width() - (border * 2);
			int imageHeight = imageWidth * d.getIntrinsicHeight()
					/ d.getIntrinsicWidth();
			if (imageHeight > r.height() - (border * 2)) {
				imageHeight = r.height() - (border * 2);
				imageWidth = imageHeight * d.getIntrinsicWidth()
						/ d.getIntrinsicHeight();
			}

			r.left += ((r.width() - imageWidth) / 2) - border;
			r.right = r.left + imageWidth + border + border;
			r.top += ((r.height() - imageHeight) / 2) - border;
			r.bottom = r.top + imageHeight + border + border;

			Paint p = new Paint();
			p.setColor(0xFFC0C0C0);//边框颜色
			c.drawRect(r, p);
			r.left += border;
			r.right -= border;
			r.top += border;
			r.bottom -= border;

			d.setBounds(r);
			d.draw(c);

			return b;
		}

		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {

			switch (index) {
			// First case is image on front side, solid colored back.
			case 0: {
				Bitmap front = loadTextBitmap(width, height, 0);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
				break;
			}
			// Second case is image on back side, solid colored front.
			case 1: {
				Bitmap back = loadTextBitmap(width, height, 2);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.rgb(127, 140, 180), CurlPage.SIDE_FRONT);
				break;
			}
			// Third case is images on both sides.
			case 2: {
				Bitmap front = loadTextBitmap(width, height, 1);
				Bitmap back = loadBitmap(width, height, 3);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setTexture(back, CurlPage.SIDE_BACK);
				break;
			}
			// Fourth case is images on both sides - plus they are blend against
			// separate colors.
			case 3: {
				Bitmap front = loadTextBitmap(width, height, 2);
				Bitmap back = loadTextBitmap(width, height, 1);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.argb(127, 170, 130, 255),
						CurlPage.SIDE_FRONT);
				page.setColor(Color.rgb(255, 190, 150), CurlPage.SIDE_BACK);
				break;
			}
			// Fifth case is same image is assigned to front and back. In this
			// scenario only one texture is used and shared for both sides.
			case 4:
				Bitmap front = loadTextBitmap(width, height, 0);
				page.setTexture(front, CurlPage.SIDE_BOTH);
				page.setColor(Color.argb(100, 255, 255, 255),
						CurlPage.SIDE_BACK);
				break;
			}
		}

        @Override
        public void updateViewMode(int viewMode) {

        }

    }

	/**
	 * CurlView size changed observer.
	 */
	private class SizeChangedObserver implements CurlView.SizeChangedObserver {
		@Override
		public void onSizeChanged(int w, int h) {
			if (w > h) {
				mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
				mCurlView.setMargins(.1f, .05f, .1f, .05f);
			} else {
				mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
				mCurlView.setMargins(.0f, .0f, .0f, .0f);
			}
		}
	}

}