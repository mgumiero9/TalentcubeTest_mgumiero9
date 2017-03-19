package mgumiero9.com.talentcubetest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mgumiero9.com.talentcubetest.util.SharedPrefStore;
import mgumiero9.com.talentcubetest.view.MainFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private SharedPreferences.Editor editor;
    private SharedPreferences mSharedPreferences;
    private SharedPrefStore mSharedPrefStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        hideSystemUI(this);

        /* Reset counters */
        mSharedPreferences = getSharedPreferences("myPrefs",0);
        editor = mSharedPreferences.edit();
        mSharedPrefStore = new SharedPrefStore();
        mSharedPrefStore.StorePair(mSharedPreferences, editor, "questionFinished", "");
        mSharedPrefStore.StorePair(mSharedPreferences, editor, "SPwhichTry", "");

        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit();
        }
    }

    private static void hideSystemUI(Activity activity) {
        View view = activity.getWindow().getDecorView();
        hideSystemUI(view);
    }

    private static void hideSystemUI(View view) {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
