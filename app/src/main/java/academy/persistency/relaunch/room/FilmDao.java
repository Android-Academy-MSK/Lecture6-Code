package academy.persistency.relaunch.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface FilmDao {

    @Query("SELECT * FROM film")
    List<Film> getAll();


    @Insert(onConflict = REPLACE)
    void insertAll(Film... films);

    @Delete
    void delete(Film film);

    @Query("DELETE FROM film")
    void deleteAll();

    @Query("SELECT * FROM film WHERE title LIKE :title LIMIT 1")
    Film findByName(String title);

    @Query("SELECT * FROM film WHERE title IN (:titles)")
    List<Film> loadAllByTitles(String[] titles);

}


