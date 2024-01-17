package uz.bahrom.a4pics1word.data.sources;

import android.content.Context;
import android.content.SharedPreferences;

import uz.bahrom.a4pics1word.app.App;

public class MyPref {
    private static MyPref myPref;
    private final SharedPreferences preferences;

    private MyPref() {
        preferences = App.context.getSharedPreferences("4 pics 1 word", Context.MODE_PRIVATE);
    }

    public static MyPref getInstance() {
        if (myPref == null) {
            myPref = new MyPref();
        }
        return myPref;
    }
    public void saveQuestionPos(int pos) {
        preferences.edit().putInt("QUESTION_POS", pos).apply();
    }

    public int getQuestionPos() {
        return preferences.getInt("QUESTION_POS", 0);
    }
}
