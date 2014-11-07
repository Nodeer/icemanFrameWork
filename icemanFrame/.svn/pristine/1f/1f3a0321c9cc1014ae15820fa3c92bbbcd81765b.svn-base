package com.iceman.yangtze.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;

public class GreyBitmapPool {

	private static GreyBitmapPool sInstance;
	// Use SparseArray<T> for better performance
	//private HashMap<Integer, Bitmap> mBitmapCache;
	private SparseArray<Bitmap> mBitmapCache;

	private GreyBitmapPool() {
		mBitmapCache = new SparseArray<Bitmap>();
	}

	public static GreyBitmapPool getInstance() {
		if (sInstance == null) {
			sInstance = new GreyBitmapPool();
		}
		return sInstance;
	}

	public static void clean() {
		sInstance.recycleImageCache();
	}

	@SuppressWarnings("deprecation")
	protected Drawable getGaryBitmap(Bitmap srcBitmap, boolean isHighLight) {
		int hashcode = srcBitmap.hashCode();

		if (mBitmapCache.get(hashcode) != null) {
			return new BitmapDrawable(mBitmapCache.get(hashcode));
		} else {
			return createGrayBitmap(hashcode, srcBitmap, isHighLight);
		}
	}

	@SuppressWarnings("deprecation")
	private Drawable createGrayBitmap(int hashcode, Bitmap srcBitmap, boolean isHighLight) {
		BitmapDrawable drawable = null;
		Bitmap.Config config = srcBitmap.getConfig();
//		config = Bitmap.Config.ARGB_4444;
		config = Bitmap.Config.ARGB_8888;

		Paint paint = new Paint();
		ColorMatrix colorMatrix = new ColorMatrix();
		if (isHighLight) {
			colorMatrix.setScale(2.5f, 2.5f, 2.5f, 1);
		} else {
			colorMatrix.setScale(0.8f, 0.8f, 0.8f, 1);
		}
		paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

		if (srcBitmap.getWidth() > 0 && srcBitmap.getHeight() > 0) {
			Bitmap tmpBmp = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), config);
			Canvas canvas = new Canvas(tmpBmp);
			canvas.drawBitmap(srcBitmap, 0, 0, paint);
			mBitmapCache.put(hashcode, tmpBmp);

			drawable = new BitmapDrawable(tmpBmp);
		} else {
			mBitmapCache.put(hashcode, srcBitmap);

			drawable = new BitmapDrawable(srcBitmap);
		}

		return drawable;
	}

	public void recycleImageCache() {
		for (int i = 0; i < mBitmapCache.size(); i++) {
			Bitmap bmp = mBitmapCache.get(i);
			if (bmp != null && !bmp.isRecycled()) {
				bmp.recycle();
				bmp = null;
			}
		}
		mBitmapCache.clear();
		sInstance = null;
	}
}