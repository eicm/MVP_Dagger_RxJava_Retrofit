package rasras.feisal.instacart_test.utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import timber.log.Timber;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public class HelperMethods {

    public static JSONObject readJsonFromAssets(Context context, String fileName) {
        JSONObject jsonObject = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int fileSize = is.available();
            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();
            jsonObject = new JSONObject(new String(buffer, "UTF-8"));

        } catch (IOException | JSONException e) {
            Timber.e(e.getMessage());
        }

        return jsonObject;
    }
}
