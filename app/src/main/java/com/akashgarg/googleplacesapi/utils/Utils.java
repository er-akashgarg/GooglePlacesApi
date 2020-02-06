package com.akashgarg.googleplacesapi.utils;

import android.content.Context;
import android.graphics.Typeface;

import androidx.core.content.res.ResourcesCompat;

import com.akashgarg.googleplacesapi.R;


/**
 * Created by Akash Garg on 10/29/19.
 */


public class Utils {

    public static Typeface getFont(Context context) {
        return ResourcesCompat.getFont(context, R.font.zawgyione);
    }

}
