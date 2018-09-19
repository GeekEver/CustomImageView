package me.geekever.customimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

/**
 * CustomImageView
 *
 * @author ever
 * @date 2018/9/9
 */
public class CustomImageView extends android.support.v7.widget.AppCompatImageView {

    private final static String LOG_TAG = CustomImageView.class.getSimpleName();
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;
    private Drawable mShapeDrawble;

    public CustomImageView(Context context) {
        this(context, null, 0);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);
        mShapeDrawble = typedArray.getDrawable(R.styleable.CustomImageView_shape_drawable);
        typedArray.recycle();
        mPaint = new Paint();
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        if (mShapeDrawble!=null){
            int saved = canvas.saveLayer(null, null);
            super.onDraw(canvas);
            mPaint.setXfermode(mXfermode);
            canvas.saveLayer(null, mPaint);
            mShapeDrawble.draw(canvas);
            mPaint.setXfermode(null);
            canvas.restoreToCount(saved);
        }
        else{
            Log.w(LOG_TAG, "No Shape ,draw as a image view.");
            super.onDraw(canvas);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mShapeDrawble.setBounds(0,0,getMeasuredWidth(),getMeasuredHeight());
    }

    public void setShapeDrawble(Drawable shapeDrawble){
        if (mShapeDrawble!=shapeDrawble){
            mShapeDrawble = shapeDrawble;
            invalidate();
        }
    }

}
