package academy.persistency.lifecycle.state;

import academy.persistency.R;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CounterWithLongOperationActivity extends AppCompatActivity {


    private static final String KEY_COUNTER = "KEY_COUNTER";
    private int mCounter = 0;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        if (savedInstanceState != null) {
            mCounter = savedInstanceState.getInt(KEY_COUNTER, 0);
        }

        findViewById(R.id.activity_counter_button_increase_count).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementCounter();
            }
        });

        updateCounterView();
    }

    private void incrementCounter() {
        Disposable disposable = Completable
                .fromCallable(new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        return null;
                    }
                })
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        mCounter++;
                        updateCounterView();
                    }
                });

        mCompositeDisposable.add(disposable);
    }

    private void updateCounterView() {
        TextView counter = findViewById(R.id.activity_counter_text_view_counter);
        counter.setText(String.valueOf(mCounter));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (outState != null) {
            outState.putInt(KEY_COUNTER, mCounter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }
}
