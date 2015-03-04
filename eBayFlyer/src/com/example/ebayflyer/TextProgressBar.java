/**
 * Individuelles ProgressBar mit Textanzeige fuer Hauptseite der App.
 * @author Li,Yuanyuan
 */


package com.example.ebayflyer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;
 
@SuppressLint("DrawAllocation") public class TextProgressBar extends ProgressBar {
 
    private String text = "";
    private int textColor = Color.BLACK;
    private float textSize = 28;
 
    public TextProgressBar(Context context) {
        super(context);
    }
 
    public TextProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public TextProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
 
    @SuppressLint("DrawAllocation") @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        Rect rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        int x = getWidth() / 2 - rect.centerX();
        int y = getHeight() / 2 - rect.centerY();
        canvas.drawText(text, x, y, textPaint);
    }
 
    public String getText() {
        return text;
    }
 
    public synchronized void setText(String text) {
        if (text != null) {
            this.text = text;
        } else {
            this.text = "";
        }
        postInvalidate();
    }
 
    public int getTextColor() {
        return textColor;
    }
 
    public synchronized void setTextColor(int textColor) {
        this.textColor = textColor;
        postInvalidate();
    }
 
    public float getTextSize() {
        return textSize;
    }
 
    public synchronized void setTextSize(float textSize) {
        this.textSize = textSize;
        postInvalidate();
    }
 
}