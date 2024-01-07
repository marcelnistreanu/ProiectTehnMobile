package com.example.proiect;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static final String PREFERENCES_NAME = "MyAppPreferences";
    private static final String BOOLEAN_KEY = "IsUserAdmin";

    public static void SaveAdminRights(Context context, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(BOOLEAN_KEY, value);
        editor.apply();
    }

    public static boolean IsUserAdmin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(BOOLEAN_KEY, true);
    }
}
