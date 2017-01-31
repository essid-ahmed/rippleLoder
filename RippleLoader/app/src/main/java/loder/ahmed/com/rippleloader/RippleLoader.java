package loder.ahmed.com.rippleloader;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;


/**
 * Created by ahmed on 19/01/17.
 */
public class RippleLoader extends FrameLayout {
    /**
     * circle stroke width
     */
    private float mStrokeWidth;
    /**
     * loader width
     */
    private int mWidth;
    /**
     * loader height
     */
    private int mHeight;
    /**
     * context
     */
    private Context mContext;
    /**
     * first circle
     */
    private RippleLoaderCircle mFirstCircle;
    /**
     * second circle
     */
    private RippleLoaderCircle mSecondCircle;
    /**
     * handler
     */
    private Handler mHandler;
    /**
     * full time animation
     */
    private int mFullTimeAnimation;
    /**
     * circle color
     */
    private int mCircleColor;
    /**
     * default animation time
     */
    private final int DEFAULT_ANIMATION_TIME=1800;
    /**
     * default circles stroke width
     */
    private final int DEFAULT_STROKE=3;
    /**
     * default circles width and height
     */
    private final int DEFAULT_DIM=100;
    /**
     * default circles color
     */
    private final int DEFAULT_COLOR=Color.BLUE;

    public RippleLoader(Context context, int width_dp, int height_dp) {
        this(context);
        mWidth = (int) convertDpToPixel(width_dp, context);
        mHeight = (int) convertDpToPixel(height_dp, context);
    }


    public RippleLoader(Context context) {
        this(context, null);
    }

    public RippleLoader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleLoader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mHandler = new Handler(mContext.getMainLooper());
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.waveLoader, defStyleAttr, 0);
        initByAttributes(attributes);
        attributes.recycle();
    }
    private void initByAttributes(TypedArray attributes)
    {
        setCircleColor(attributes.getColor(R.styleable.waveLoader_circle_color, DEFAULT_COLOR));
        setFullTimeAnimation(attributes.getInt(R.styleable.waveLoader_full_time_animation, DEFAULT_ANIMATION_TIME));
        setStrokeWidth(attributes.getInt(R.styleable.waveLoader_circle_stroke, DEFAULT_STROKE));
        setCircleDim(attributes.getInt(R.styleable.waveLoader_circle_dim,DEFAULT_DIM));
    }




    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        create();
        startAnimation();
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void create() {
        initialize();
    }

    private void initialize() {
        mFirstCircle = new RippleLoaderCircle(mContext, mWidth, mHeight, mStrokeWidth, mFullTimeAnimation, mCircleColor);
        mSecondCircle = new RippleLoaderCircle(mContext, mWidth, mHeight, mStrokeWidth, mFullTimeAnimation, mCircleColor);
        addView(mFirstCircle);
        addView(mSecondCircle);

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
     * start first circle animation and in mFullTimeAnimation/2 start second animation
     */
    private void startAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mFirstCircle.startResizeAnim();

            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSecondCircle.startResizeAnim();
            }
        }, mFullTimeAnimation / 2);

    }

    /**
     * set full time animation
     *
     * @param fullTimeAnimation
     */
    public void setFullTimeAnimation(int fullTimeAnimation) {
        mFullTimeAnimation = fullTimeAnimation;
    }

    /**
     * set circles color
     *
     * @param color
     */
    public void setCircleColor(int color) {
        mCircleColor = color;
    }

    /**
     * set stroke width of circle
     * @param strokeWidth
     */
    public void setStrokeWidth(int strokeWidth) {
        mStrokeWidth = strokeWidth;
    }

    /**
     * set width and height of circles
     * @param dim
     */
    public void setCircleDim(int dim) {
        mHeight = dim;
        mWidth = dim;
    }
}
