/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.laychv.swiperefreshlayout.source;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.laychv.swiperefreshlayout.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Drawable that renders the animated indeterminate progress indicator in the Material design style
 * without depending on API level 11.
 *
 * <p>While this may be used to draw an indeterminate spinner using {@link #start()} and {@link
 * #stop()} methods, this may also be used to draw a progress arc using {@link
 * #setStartEndTrim(float, float)} method. CircularProgressDrawable also supports adding an arrow
 * at the end of the arc by {@link #setArrowEnabled(boolean)} and {@link #setArrowDimensions(float,
 * float)} methods.
 *
 * <p>To use one of the pre-defined sizes instead of using your own, {@link #setStyle(int)} should
 * be called with one of the {@link #DEFAULT} or {@link #LARGE} styles as its parameter. Doing it
 * so will update the arrow dimensions, ring size and stroke width to fit the one specified.
 *
 * <p>If no center radius is set via {@link #setCenterRadius(float)} or {@link #setStyle(int)}
 * methods, CircularProgressDrawable will fill the bounds set via {@link #setBounds(Rect)}.
 */
public class CircularProgressDrawable extends Drawable implements Animatable {
    /**
     * Maps to ProgressBar.Large style.
     */
    public static final int LARGE = 0;
    /**
     * Maps to ProgressBar default style.
     */
    public static final int DEFAULT = 1;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final float CENTER_RADIUS_LARGE = 11f;
    private static final float STROKE_WIDTH_LARGE = 3f;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final int ARROW_HEIGHT_LARGE = 6;
    private static final float CENTER_RADIUS = 7.5f;
    private static final float STROKE_WIDTH = 2.5f;
    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_HEIGHT = 5;
    /**
     * This is the default set of colors that's used in spinner. {@link
     * #setColorSchemeColors(int...)} allows modifying colors.
     */
    private static final int[] COLORS = new int[]{
            Color.BLACK
    };
    /**
     * The value in the linear interpolator for animating the drawable at which
     * the color transition should start
     */
    private static final float COLOR_CHANGE_OFFSET = 0.75f;
    private static final float SHRINK_OFFSET = 0.5f;
    /**
     * The duration of a single progress spin in milliseconds.
     */
    private static final int ANIMATION_DURATION = 1332;
    /**
     * Full rotation that's done for the animation duration in degrees.
     */
    private static final float GROUP_FULL_ROTATION = 1080f / 5f;
    /**
     * Maximum length of the progress arc during the animation.
     */
    private static final float MAX_PROGRESS_ARC = .8f;

    /**
     * The indicator ring, used to manage animation state.
     */
//    private final Ring mRing;
    /**
     * Minimum length of the progress arc during the animation.
     */
    private static final float MIN_PROGRESS_ARC = .01f;
    /**
     * Rotation applied to ring during the animation, to complete it to a full circle.
     */
    private static final float RING_ROTATION = 1f - (MAX_PROGRESS_ARC - MIN_PROGRESS_ARC);
    private final Bitmap mBitmap;
    @SuppressWarnings("WeakerAccess") /* synthetic access */
            float mRotationCount;
    @SuppressWarnings("WeakerAccess") /* synthetic access */
            boolean mFinishing;
    float startAngle = 0f;
    float endAngle = 0f;
    float mStartTrim = 0f;
    float mEndTrim = 0f;
    /**
     * Canvas rotation in degrees.
     */
    private float mRotation;
    private Resources mResources;
    private Animator mAnimator;

    /**
     * @param context application context
     */
    public CircularProgressDrawable(@NonNull Context context) {
        mResources = context.getResources();

//        mRing = new Ring();
//        mRing.setColors(COLORS);

        mBitmap = BitmapFactory.decodeResource(mResources, R.drawable.icon_loading_bg);

//        setStrokeWidth(STROKE_WIDTH);
        setupAnimators();
    }

    /**
     * Sets all parameters at once in dp.
     */
    private void setSizeParameters(float centerRadius, float strokeWidth, float arrowWidth, float arrowHeight) {
//        final Ring ring = mRing;
//        final DisplayMetrics metrics = mResources.getDisplayMetrics();
//        final float screenDensity = metrics.density;

//        ring.setStrokeWidth(strokeWidth * screenDensity);
//        ring.setCenterRadius(centerRadius * screenDensity);
//        ring.setColorIndex(0);
//        ring.setArrowDimensions(arrowWidth * screenDensity, arrowHeight * screenDensity);
    }

    /**
     * Sets the overall size for the progress spinner. This updates the radius
     * and stroke width of the ring, and arrow dimensions.
     *
     * @param size one of {@link #LARGE} or {@link #DEFAULT}
     */
    public void setStyle(@ProgressDrawableSize int size) {
//        if (size == LARGE) {
//            setSizeParameters(CENTER_RADIUS_LARGE, STROKE_WIDTH_LARGE, ARROW_WIDTH_LARGE, ARROW_HEIGHT_LARGE);
//        } else {
//            setSizeParameters(CENTER_RADIUS, STROKE_WIDTH, ARROW_WIDTH, ARROW_HEIGHT);
//        }
//        invalidateSelf();
    }

    /**
     * Sets the stroke width for the progress spinner in pixels.
     *
     * @param strokeWidth stroke width in pixels
     */
    public void setStrokeWidth(float strokeWidth) {
//        mRing.setStrokeWidth(strokeWidth);
//        invalidateSelf();
    }

    /**
     * Returns the stroke width for the progress spinner in pixels.
     *
     * @return stroke width in pixels
     */
//    public float getStrokeWidth() {
//        return mRing.getStrokeWidth();
//    }

    /**
     * Sets if the arrow at the end of the spinner should be shown.
     *
     * @param show {@code true} if the arrow should be drawn, {@code false} otherwise
     */
    public void setArrowEnabled(boolean show) {
//        mRing.setShowArrow(show);
//        invalidateSelf();
    }

    /**
     * Returns the center radius for the progress spinner in pixels.
     *
     * @return center radius in pixels
     */
//    public float getCenterRadius() {
//        return mRing.getCenterRadius();
//    }

    /**
     * Sets the center radius for the progress spinner in pixels. If set to 0, this drawable will
     * fill the bounds when drawn.
     *
     * @param centerRadius center radius in pixels
     */
//    public void setCenterRadius(float centerRadius) {
//        mRing.setCenterRadius(centerRadius);
//        invalidateSelf();
//    }

    /**
     * Sets the stroke cap of the progress spinner. Default stroke cap is {@link Paint.Cap#SQUARE}.
     *
     * @param strokeCap stroke cap
     */
//    public void setStrokeCap(@NonNull Paint.Cap strokeCap) {
//        mRing.setStrokeCap(strokeCap);
//        invalidateSelf();
//    }

    /**
     * Returns the stroke cap of the progress spinner.
     *
     * @return stroke cap
     */
//    @NonNull
//    public Paint.Cap getStrokeCap() {
//        return mRing.getStrokeCap();
//    }

    /**
     * Returns the arrow width in pixels.
     *
     * @return arrow width in pixels
     */
//    public float getArrowWidth() {
//        return mRing.getArrowWidth();
//    }

    /**
     * Returns the arrow height in pixels.
     *
     * @return arrow height in pixels
     */
//    public float getArrowHeight() {
//        return mRing.getArrowHeight();
//    }

    /**
     * Sets the dimensions of the arrow at the end of the spinner in pixels.
     *
     * @param width width of the baseline of the arrow in pixels
     * @param height distance from tip of the arrow to its baseline in pixels
     */
//    public void setArrowDimensions(float width, float height) {
//        mRing.setArrowDimensions(width, height);
//        invalidateSelf();
//    }

    /**
     * Returns {@code true} if the arrow at the end of the spinner is shown.
     *
     * @return {@code true} if the arrow is shown, {@code false} otherwise.
     */
//    public boolean getArrowEnabled() {
//        return mRing.getShowArrow();
//    }

    /**
     * Sets the scale of the arrow at the end of the spinner.
     *
     * @param scale scaling that will be applied to the arrow's both width and height when drawing.
     */
    public void setArrowScale(float scale) {
//        mRing.setArrowScale(scale);
//        invalidateSelf();
    }

    /**
     * Returns the scale of the arrow at the end of the spinner.
     *
     * @return scale of the arrow
     */
//    public float getArrowScale() {
//        return mRing.getArrowScale();
//    }

    /**
     * Sets the start and end trim for the progress spinner arc. 0 corresponds to the geometric
     * angle of 0 degrees (3 o'clock on a watch) and it increases clockwise, coming to a full circle
     * at 1.
     *
     * @param start starting position of the arc from [0..1]
     * @param end   ending position of the arc from [0..1]
     */
    public void setStartEndTrim(float start, float end) {
//        mRing.setStartTrim(start);
//        mRing.setEndTrim(end);
//        invalidateSelf();
    }

    /**
     * Returns the start trim for the progress spinner arc
     *
     * @return start trim from [0..1]
     */
//    public float getStartTrim() {
//        return mRing.getStartTrim();
//    }

    /**
     * Returns the end trim for the progress spinner arc
     *
     * @return end trim from [0..1]
     */
//    public float getEndTrim() {
//        return mRing.getEndTrim();
//    }

    /**
     * Sets the amount of rotation to apply to the progress spinner.
     *
     * @param rotation rotation from [0..1]
     */
    public void setProgressRotation(float rotation) {
//        mRing.setRotation(rotation);
        setRotation(rotation);
        invalidateSelf();
    }

    /**
     * Returns the amount of rotation applied to the progress spinner.
     *
     * @return amount of rotation from [0..1]
     */
//    public float getProgressRotation() {
//        return mRing.getRotation();
//    }

    /**
     * Sets the background color of the circle inside the drawable. Calling {@link
     * #setAlpha(int)} does not affect the visibility background color, so it should be set
     * separately if it needs to be hidden or visible.
     *
     * @param color an ARGB color
     */
    public void setBackgroundColor(int color) {
//        mRing.setBackgroundColor(color);
//        invalidateSelf();
    }

    /**
     * Returns the background color of the circle drawn inside the drawable.
     *
     * @return an ARGB color
     */
//    public int getBackgroundColor() {
//        return mRing.getBackgroundColor();
//    }

    /**
     * Sets the colors used in the progress animation from a color list. The first color will also
     * be the color to be used if animation is not started yet.
     *
     * @param colors list of ARGB colors to be used in the spinner
     */
    public void setColorSchemeColors(@NonNull int... colors) {
//        mRing.setColors(colors);
//        mRing.setColorIndex(0);
//        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {
        final Rect bounds = getBounds();
        canvas.save();
//        canvas.rotate(mRotation, bounds.exactCenterX(), bounds.exactCenterY());
//        mRing.draw(canvas, bounds);

        startAngle = (mStartTrim + mRotation) * 360;
        endAngle = (mEndTrim + mRotation) * 360;
        float sweepAngle = endAngle - startAngle;

        // 抗锯齿
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        canvas.rotate(startAngle + sweepAngle + 45f, bounds.exactCenterX(), bounds.exactCenterY());// 确保图标是垂直的
        canvas.drawBitmap(mBitmap, 10, 10, paint);

        canvas.restore();
    }

    @Override
    public int getAlpha() {
//        return mRing.getAlpha();
        return 0;
    }

    @Override
    public void setAlpha(int alpha) {
//        mRing.setAlpha(alpha);
//        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
//        mRing.setColorFilter(colorFilter);
//        invalidateSelf();
    }

    private void setRotation(float rotation) {
        mRotation = rotation;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public boolean isRunning() {
        return mAnimator.isRunning();
    }

    /**
     * Starts the animation for the spinner.
     */
    @Override
    public void start() {
        mAnimator.cancel();
//        mRing.storeOriginals();
//        // Already showing some part of the ring
//        if (mRing.getEndTrim() != mRing.getStartTrim()) {
        if (endAngle != startAngle) {
            mFinishing = true;
            mAnimator.setDuration(ANIMATION_DURATION / 2);
            mAnimator.start();
        } else {
//            mRing.setColorIndex(0);
//            mRing.resetOriginals();
            mAnimator.setDuration(ANIMATION_DURATION);
            mAnimator.start();
        }
    }

    /**
     * Stops the animation for the spinner.
     */
    @Override
    public void stop() {
        mAnimator.cancel();
        setRotation(0);
//        mRing.setShowArrow(false);
//        mRing.setColorIndex(0);
//        mRing.resetOriginals();
        invalidateSelf();
    }


    /**
     * Update the ring start and end trim if the animation is finishing (i.e. it started with
     * already visible progress, so needs to shrink back down before starting the spinner).
     */
//    private void applyFinishTranslation(float interpolatedTime, Ring ring) {
    private void applyFinishTranslation(float interpolatedTime) {
//     shrink back down and complete a full rotation before
//     starting other circles
//     Rotation goes between [0..1].
//        updateRingColor(interpolatedTime, ring);
//        float targetRotation = (float) (Math.floor(ring.getStartingRotation() / MAX_PROGRESS_ARC) + 1f);
        float targetRotation = (float) (Math.floor(mRotation / MAX_PROGRESS_ARC) + 1f);
//        final float startTrim = ring.getStartingStartTrim() + (ring.getStartingEndTrim() - MIN_PROGRESS_ARC - ring.getStartingStartTrim()) * interpolatedTime;
//        ring.setStartTrim(startTrim);
//        ring.setEndTrim(ring.getStartingEndTrim());
//        final float rotation = ring.getStartingRotation() + ((targetRotation - ring.getStartingRotation()) * interpolatedTime);
        final float rotation = mRotation + ((targetRotation - mRotation) * interpolatedTime);
//        ring.setRotation(rotation);
        setRotation(rotation);
//        setRotation(0);
    }

    /**
     * Update the ring start and end trim according to current time of the animation.
     */
    @SuppressWarnings("WeakerAccess") /* synthetic access */
//    void applyTransformation(float interpolatedTime, Ring ring, boolean lastFrame) {
    void applyTransformation(float interpolatedTime, boolean lastFrame) {
        if (mFinishing) {
            applyFinishTranslation(interpolatedTime);
            // Below condition is to work around a ValueAnimator issue where onAnimationRepeat is
            // called before last frame (1f).
        } else if (interpolatedTime != 1f || lastFrame) {
//            final float startingRotation = ring.getStartingRotation();
            final float startingRotation = mRotation;
//            float startTrim, endTrim;

//            if (interpolatedTime < SHRINK_OFFSET) { // Expansion occurs on first half of animation
//                final float scaledTime = interpolatedTime / SHRINK_OFFSET;
////                startTrim = ring.getStartingStartTrim();
//                endTrim = startTrim + ((MAX_PROGRESS_ARC - MIN_PROGRESS_ARC)
//                        * MATERIAL_INTERPOLATOR.getInterpolation(scaledTime) + MIN_PROGRESS_ARC);
//            } else { // Shrinking occurs on second half of animation
//                float scaledTime = (interpolatedTime - SHRINK_OFFSET) / (1f - SHRINK_OFFSET);
////                endTrim = ring.getStartingStartTrim() + (MAX_PROGRESS_ARC - MIN_PROGRESS_ARC);
//                startTrim = endTrim - ((MAX_PROGRESS_ARC - MIN_PROGRESS_ARC)
//                        * (1f - MATERIAL_INTERPOLATOR.getInterpolation(scaledTime))
//                        + MIN_PROGRESS_ARC);
//            }

            final float rotation = startingRotation + (RING_ROTATION * interpolatedTime);
            float groupRotation = GROUP_FULL_ROTATION * (interpolatedTime + mRotationCount);
            float rot = 2.5f * (interpolatedTime + mRotationCount); // 旋转速度

//            ring.setStartTrim(startTrim);
//            ring.setEndTrim(endTrim);
//            ring.setRotation(rotation);
            setRotation(rot);
        }
    }

    private void setupAnimators() {
//        final Ring ring = mRing;
        final ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float interpolatedTime = (float) animation.getAnimatedValue();
//                updateRingColor(interpolatedTime, ring);
                applyTransformation(interpolatedTime, false);
                invalidateSelf();
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(LINEAR_INTERPOLATOR);
        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animator) {
                mRotationCount = 0;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                // do nothing
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // do nothing
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                applyTransformation(1f, true);
//                ring.storeOriginals();
//                ring.goToNextColor();
                if (mFinishing) {
                    // finished closing the last ring from the swipe gesture; go
                    // into progress mode
                    mFinishing = false;
                    animator.cancel();
                    animator.setDuration(ANIMATION_DURATION);
                    animator.start();
//                    ring.setShowArrow(false);
                } else {
                    mRotationCount = mRotationCount + 1;
                }
            }
        });
        mAnimator = animator;
    }

    @RestrictTo(LIBRARY_GROUP)
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LARGE, DEFAULT})
    public @interface ProgressDrawableSize {
    }
}
