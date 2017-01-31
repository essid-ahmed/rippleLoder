package loder.ahmed.com.rippleloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 * Created by ahmed on 19/01/17.
 */
public class RippleLoaderCircle extends View {
    /**
     * circle paint
     */
    private Paint mCirclePaint;
    /**
     * circle color
     */
    private int mCircleColor;
    /**
     * circle stroke width
     */
    private float mStrokeWidth;
    private RectF mCircleRect = new RectF();
    /**
     * circle width
     */
    private int mWidth;
    /**
     * circle height
     */
    private int mHeight;
    /**
     * circle radius
     */
    private float mCircleRadius = 0;
    /**
     * full time animation in second
     */
    private int mFullTimeAnimation;

    public RippleLoaderCircle(Context context) {
        super(context);
    }

    public RippleLoaderCircle(Context context, int width_dp, int height_dp, float strokeWidth, int fullTimeAnimation, int circleColor) {
        super(context);
        mWidth = (int) convertDpToPixel(width_dp, context);
        mHeight = (int) convertDpToPixel(height_dp, context);
        mCircleColor = circleColor;
        mStrokeWidth = (int) convertDpToPixel(strokeWidth, context);
        mFullTimeAnimation = fullTimeAnimation;
        initialize();
    }


    private void initialize() {
        setBackgroundColor(Color.TRANSPARENT);
        setLayoutParams(new ViewGroup.LayoutParams(mWidth, mHeight));
        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(mStrokeWidth);


    }

    // draw circle with radius = 0
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCircleRect.set(mStrokeWidth, mStrokeWidth, getWidth() - mStrokeWidth, getHeight() - mStrokeWidth);
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, mCircleRadius, mCirclePaint);
    }

    /**
     * start resize and alpha animation
     */
    public void startResizeAnim() {

        final ResizeAnimation anim = new ResizeAnimation(this, 0, (getWidth() - mStrokeWidth) / 2);
        anim.setDuration(mFullTimeAnimation);
        anim.setRepeatCount(Animation.INFINITE);
        startAnimation(anim);

    }

    public static float convertDpToPixel(float dp, Context context) {
        if (context == null)
            return 0;
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    /**
     * set the radius of circle relative to animation time
     *
     * @param radius
     */
    public void setRadius(float radius) {
        mCircleRadius = radius;
    }

}
