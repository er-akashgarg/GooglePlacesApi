package com.akashgarg.googleplacesapi.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import com.akashgarg.googleplacesapi.MMConverter.MMText;
import com.akashgarg.googleplacesapi.helper.TextHelper;

/**
 * Created by Akash Garg on 10/29/19.
 */


@SuppressLint("AppCompatCustomView")
public class MyanmarTextView extends TextView {

    public MyanmarTextView(Context context) {
        super(context);
        TextHelper.setTypeface(context, this);
    }

    public MyanmarTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TextHelper.setTypeface(context, this);
    }

    public MyanmarTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TextHelper.setTypeface(context, this);
    }

    public CharSequence getMyanmarText() {
        return MMText.getMMText(this);
    }

    public void setMyanmarText(String st) {
        setText(MMText.processText(st, MMText.TEXT_UNICODE, true, true));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
