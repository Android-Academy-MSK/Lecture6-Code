package academy.persistency.relaunch.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Some methods can not return Rx operator. See [https://issuetracker.google.com/issues/63317956]
 */
@Dao
public interface FilmAsyncDao {

    @Query("SELECT * FROM film")
    Observable<List<Film>> getAll();

    @Insert(onConflict = REPLACE)
    void insertAll(Film... films);

    @Delete
    void delete(Film film);

    @Query("DELETE FROM film")
    void deleteAll();

    @Query("SELECT * FROM film WHERE title LIKE :title LIMIT 1")
    Single<Film> findByName(String title);


    @Query("SELECT * FROM film WHERE title IN (:titles)")
    Observable<List<Film>> loadAllByTitles(String[] titles);

}


