package com.akashgarg.googleplacesapi.MMConverter;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.akashgarg.googleplacesapi.utils.Utils;


/**
 * Created by Akash Garg on 10/29/19.
 */

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
