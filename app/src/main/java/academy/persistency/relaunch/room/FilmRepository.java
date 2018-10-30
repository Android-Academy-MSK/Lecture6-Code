package academy.persistency.relaunch.room;

import android.content.Context;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;
import java.util.concurrent.Callable;

public class FilmRepository {

    private final Context mContext;

    public FilmRepository(Context mContext) {
        this.mContext = mContext;
    }

    Completable saveData(final List<Film> filmList) {
        return Completable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                AppDatabase db = AppDatabase.getAppDatabase(mContext);

                //db.filmDao().deleteAll();

                Film[] films = filmList.toArray(new Film[filmList.size()]);

                db.filmDao().insertAll(films);

                return null;
            }
        });
    }

    Single<List<Film>> getData() {

        return Single.fromCallable(new Callable<List<Film>>() {
            @Override
            public List<Film> call() throws Exception {
                AppDatabase db = AppDatabase.getAppDatabase(mContext);

                return db.filmDao().getAll();
            }
        });
    }


    Observable<List<Film>> getDataObservable() {
        AppDatabase db = AppDatabase.getAppDatabase(mContext);

        return db.filmAsyncDao().getAll();
    }
}
