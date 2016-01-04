package com.master.normtester.app.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by lpalonek on 18/11/15.
 */
public final class BitmapConverter {

    private BitmapConverter(){}

    public static String convertBitmapToBase64String(Bitmap bitmap) {
        byte[] b = convertBitmapToByteArray(bitmap);
        String temp = Base64.encodeToString(b, Base64.NO_WRAP);
        return temp;
    }

    public static byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap convertByteArrayToBitmap(byte[] bitmap){
        return BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length);
    }
}
