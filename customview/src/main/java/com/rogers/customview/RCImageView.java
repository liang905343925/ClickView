/*
 * Copyright 2018 GcsSloop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Last modified 2018-04-15 22:59:54
 *
 * GitHub: https://github.com/GcsSloop
 * WeiBo: http://weibo.com/GcsSloop
 * WebSite: http://www.gcssloop.com
 */

package com.rogers.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;


/**
 * 作用：圆角图片
 * 作者：GcsSloop
 */
@SuppressLint("AppCompatCustomView")
public class RCImageView extends AppCompatImageView implements RCAttrs {

    RCHelper mRCHelper;

    public RCImageView(Context context) {
        this(context, null);
    }

    public RCImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRCHelper = new RCHelper();
        mRCHelper.initAttrs(context, attrs);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRCHelper.onSizeChanged(this, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        if (mRCHelper.mClipBackground) {
            canvas.save();
            canvas.clipPath(mRCHelper.mClipPath);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(mRCHelper.mLayer, null, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        mRCHelper.onClipDraw(canvas);
        canvas.restore();
    }


    //--- 公开接口 ----------------------------------------------------------------------------------

    public void setClipBackground(boolean clipBackground) {
        mRCHelper.mClipBackground = clipBackground;
        invalidate();
    }

    public void setRoundAsCircle(boolean roundAsCircle) {
        mRCHelper.mRoundAsCircle = roundAsCircle;
        invalidate();
    }

    public void setRadius(int radius) {
        for (int i = 0; i < mRCHelper.radii.length; i++) {
            mRCHelper.radii[i] = radius;
        }
        invalidate();
    }

    public void setTopLeftRadius(int topLeftRadius) {
        mRCHelper.radii[0] = topLeftRadius;
        mRCHelper.radii[1] = topLeftRadius;
        invalidate();
    }

    public void setTopRightRadius(int topRightRadius) {
        mRCHelper.radii[2] = topRightRadius;
        mRCHelper.radii[3] = topRightRadius;
        invalidate();
    }

    public void setBottomLeftRadius(int bottomLeftRadius) {
        mRCHelper.radii[6] = bottomLeftRadius;
        mRCHelper.radii[7] = bottomLeftRadius;
        invalidate();
    }

    public void setBottomRightRadius(int bottomRightRadius) {
        mRCHelper.radii[4] = bottomRightRadius;
        mRCHelper.radii[5] = bottomRightRadius;
        invalidate();
    }

    public void setStrokeWidth(int strokeWidth) {
        mRCHelper.mStrokeWidth = strokeWidth;
        invalidate();
    }

    public void setStrokeColor(int strokeColor) {
        mRCHelper.mStrokeColor = strokeColor;
        invalidate();
    }

    @Override
    public void invalidate() {
        if (null != mRCHelper)
            mRCHelper.refreshRegion(this);
        super.invalidate();
    }

    public boolean isClipBackground() {
        return mRCHelper.mClipBackground;
    }

    public boolean isRoundAsCircle() {
        return mRCHelper.mRoundAsCircle;
    }

    public float getTopLeftRadius() {
        return mRCHelper.radii[0];
    }

    public float getTopRightRadius() {
        return mRCHelper.radii[2];
    }

    public float getBottomLeftRadius() {
        return mRCHelper.radii[4];
    }

    public float getBottomRightRadius() {
        return mRCHelper.radii[6];
    }

    public int getStrokeWidth() {
        return mRCHelper.mStrokeWidth;
    }

    public int getStrokeColor() {
        return mRCHelper.mStrokeColor;
    }


    //--- Selector 支持 ----------------------------------------------------------------------------

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        mRCHelper.drawableStateChanged(this);
    }


}
