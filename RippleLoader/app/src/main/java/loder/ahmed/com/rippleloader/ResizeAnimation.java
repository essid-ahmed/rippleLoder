package loder.ahmed.com.rippleloader;

import android.view.animation.Animation;
import android.view.animation.Transformation;


/**
 * Created by ahmed essid on 20/01/2017.
 */

/**
 * an animation for resizing the view.
 */
public class ResizeAnimation extends Animation {
    private RippleLoaderCircle mCircle;
    private float mFromRadius;
    private float mToRadius;

    public ResizeAnimation(RippleLoaderCircle v, float fromRadius, float toRadius) {
        mCircle = v;
        mFromRadius = fromRadius;
        mToRadius = toRadius;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float radius = (mToRadius - mFromRadius) * interpolatedTime + mFromRadius;
        //alpha animation should be start after fullTime animation / 2
        if (interpolatedTime <= 0.5) {
            mCircle.setAlpha(1);

        } else {
            mCircle.setAlpha(-2 * interpolatedTime + 2);
        }
        // set radius of circle and redraw
        mCircle.setRadius(radius);
        mCircle.requestLayout();
    }

}