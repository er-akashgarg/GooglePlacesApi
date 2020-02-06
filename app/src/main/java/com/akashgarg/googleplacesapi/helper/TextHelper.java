package com.akashgarg.googleplacesapi.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.akashgarg.googleplacesapi.MMConverter.MMText;
import com.akashgarg.googleplacesapi.utils.Utils;


public class TextHelper {

    private static Typeface typeface = null;

    public static void setTypeface(Context context, TextView textview) {
        if (TextHelper.typeface == null) {
            TextHelper.typeface = Utils.getFont(context);
        }
        textview.setText(MMText.processText(textview.getText().toString(), MMText.TEXT_UNICODE, true, true));
        textview.setTypeface(typeface);
    }
}
