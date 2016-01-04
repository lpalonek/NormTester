package com.master.normtester.app.norm.compute;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.wifi.WifiManager;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.master.normtester.app.App;
import com.master.normtester.app.norm.NormHandler;
import com.master.normtester.app.rabbit.RabbitClient;
import com.master.normtester.app.util.BitmapConverter;

/**
 * Created by lpalonek on 28/10/15.
 */
public enum ComputingManager {
    INSTANCE;
    private TessBaseAPI tessBaseAPI = new TessBaseAPI();
    public String performComputations(Bitmap bitmap, int numberOfLines){

        NormHandler normHandler = new NormHandler();
        normHandler.applyNorms();
        String result;
        if(ProblemSize.calculateProblemSize(numberOfLines) == ProblemSize.SMALL || !((WifiManager) App.get().getSystemService(Context.WIFI_SERVICE)).isWifiEnabled()){
            result = tessBaseAPI.getUTF8Text();
        }else {
            RabbitClient rabbitClient = RabbitClient.INSTANCE;
            result = rabbitClient.call(BitmapConverter.convertBitmapToBase64String(bitmap));
        }
        return result;
    }
}
