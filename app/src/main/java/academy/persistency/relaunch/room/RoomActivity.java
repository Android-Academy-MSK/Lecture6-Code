package academy.persistency.relaunch.room;

import academy.persistency.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private FilmRepository filmRepository;
    private static final String TAG = "RoomActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room);

        filmRepository = new FilmRepository(this.getApplicationContext());
        initViews();

        subscribeToData();
    }

    private void subscribeToData() {
        Disposable disposable = filmRepository.getDataObservable()
                //.count()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Film>>() {
                    @Override
                    public void accept(List<Film> films) throws Exception {
                        Log.d(TAG, films.toString());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, throwable.toString());
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void initViews() {

        findViewById(R.id.activity_room_button_get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Disposable disposable = filmRepository.getData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Film>>() {
                            @Override
                            public void accept(List<Film> films) throws Exception {
                                Log.d(TAG, films.toString());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG, throwable.toString());
                            }
                        });
                compositeDisposable.add(disposable);

            }
        });

        findViewById(R.id.activity_room_button_store_sync).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ArrayList<Film> films = getGeneratedFilms();


                Disposable disposable = filmRepository.saveData(films)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action() {

                            @Override
                            public void run() throws Exception {
                                Log.d(TAG, films.toString());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG, throwable.toString());
                            }
                        });
                compositeDisposable.add(disposable);

            }

        });

    }

    private ArrayList<Film> getGeneratedFilms() {

        int count = new Random().nextInt(10);
        ArrayList<Film> films = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Film film = new Film();
            film.setTitle(generateRandomString());
            film.setDescription(generateRandomString());
            film.setUrl(generateRandomString());
            films.add(film);
        }

        return films;
    }

    private String generateRandomString() {

        return String.valueOf(new Random().nextInt(1456));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
