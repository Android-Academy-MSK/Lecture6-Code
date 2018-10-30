package academy.persistency.lifecycle.logging;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseLoggingActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String text = savedInstanceState == null ? "null" : "savedInstanceState";
        this.log("onCreate(" + text + ')');
    }

    protected void onRestart() {
        super.onRestart();
        this.log("onRestart()");
    }

    protected void onStart() {
        super.onStart();
        this.log("onStart()");
    }

    protected void onResume() {
        super.onResume();
        this.log("onResume()");
    }

    protected void onPause() {
        super.onPause();
        this.log("onPause()");
    }

    protected void onStop() {
        super.onStop();
        this.log("onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        this.log("onDestroy()");
    }

    protected void onSaveInstanceState(@Nullable Bundle outState) {
        super.onSaveInstanceState(outState);
        this.log("onSaveInstanceState()");
    }

    protected void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void log(String message) {
        Log.d("LifeCycle", this.getActivityTag() + ": " + message);
    }

    @NonNull
    public abstract String getActivityTag();
}