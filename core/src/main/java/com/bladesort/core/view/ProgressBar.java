package com.bladesort.core.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.bladesort.core.R;
import com.bladesort.core.manager.ColorManager;


/**
 * Created by leocx on 2016/10/31.
 */

public class ProgressBar extends BaseView {

    private Paint filledPaint;
    private Paint unfilledPaint;
    private TextPaint textPaint;
    private int textSize = 20;
    private int filledColor = 0xDEADBEFF;
    private int unfilledColor = 0xDEADBEFF;
    private int textColor = 0xDEADBEFF;
    private float rate = 0;

    private String textBeforeRate = "";
    private String textAfterRate = "";

    private String rateFormat = "%.0f";

    public ProgressBar(Context context) {
        super(context);
    }

    public ProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init() {
        filledPaint = new Paint();
        unfilledPaint = new Paint();
        textPaint = new TextPaint();
        textPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, getResources().getDisplayMetrics()));
        if (textColor == 0xDEADBEEF) {
            textPaint.setColor(ColorManager.getColor(R.color.colorWhite));
        } else {
            textPaint.setColor(textColor);
        }
        if (filledColor == 0xDEADBEEF) {
            filledPaint.setColor(ColorManager.getColor(R.color.colorPrimary));
        } else {
            filledPaint.setColor(filledColor);
        }
        if (unfilledColor == 0xDEADBEEF) {
            unfilledPaint.setColor(ColorManager.getColor(R.color.colorPrimaryLight));
        } else {
            unfilledPaint.setColor(unfilledColor);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, width, height, unfilledPaint);
        canvas.drawRect(0, 0, width * rate, height, filledPaint);
        drawText(textBeforeRate + String.format(rateFormat, getRate() * 100) + textAfterRate, width / 2, height / 2, canvas, textPaint);
    }

    public void setTextBeforeRate(String textBeforeRate) {
        this.textBeforeRate = textBeforeRate;
        postInvalidate();
    }

    public void setTextAfterRate(String textAfterRate) {
        this.textAfterRate = textAfterRate;
        postInvalidate();
    }

    public void setTextSize(int size) {
        textSize = size;
        if (textPaint != null) {
            textPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, getResources().getDisplayMetrics()));
            postInvalidate();
        }
    }

    public void setFilledColor(int colorResourceId) {
        filledColor = ColorManager.getColor(colorResourceId);
        if (filledPaint != null) {
            filledPaint.setColor(filledColor);
            postInvalidate();
        }
    }

    public void setUnfilledColor(int colorResourceId) {
        unfilledColor = ColorManager.getColor(colorResourceId);
        if (unfilledPaint != null) {
            unfilledPaint.setColor(unfilledColor);
            postInvalidate();
        }
    }

    public void setTextColor(int colorResourceId) {
        textColor = ColorManager.getColor(colorResourceId);
        if (textPaint != null) {
            textPaint.setColor(textColor);
            postInvalidate();
        }
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        if (rate > 1) {
            this.rate = 1;
        } else if (rate < 0) {
            this.rate = 0;
        } else {
            this.rate = rate;
        }
        postInvalidate();
    }

    public void setRateByAnim(float rate) {
        AnimRunnable animRunnable = new AnimRunnable(this.rate, rate);
        postDelayed(animRunnable, 0);

    }

    public void setRateFormat(String format) {

        this.rateFormat = format;
    }


    private class AnimRunnable implements Runnable {
        float start;
        float end;
        int direction = 0;

        public AnimRunnable(float start, float end) {
            this.start = start;
            this.end = end;
            if (start > end) {
                direction = -1;
            } else if (start < end) {
                direction = 1;
            }
        }

        public void run() {
            float unit = Math.max(Math.abs(start - end) / 30, 0.006f);
            rate = start;
            if (direction > 0) {
                if (start < end) {
                    start += unit;
                    postDelayed(this, 10);
                } else {
                    rate = end;
                }
            } else if (direction < 0) {
                if (start > end) {
                    start -= unit;
                    postDelayed(this, 10);
                } else {
                    rate = end;
                }
            }
            invalidate();
        }
    }

}
