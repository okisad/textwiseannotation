package com.artiwise.textwiseannotation.util;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * Created by oktaysadoglu on 10/03/2017.
 */
public class ReturnJSONS {

    public static JSONObject getStatusOK() throws JSONException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("status","ok");

        return jsonObject;

    }

    public static JSONObject getStatusFailed() throws JSONException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("status","failed");

        return jsonObject;

    }

}
