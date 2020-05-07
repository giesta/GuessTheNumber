package edu.ktu.guessthenumber;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoAccess {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM `User` ORDER BY `numberOfWins` desc")
    LiveData<List<User>>getAllUsers();

    @Query("SELECT * FROM `User` WHERE `id` =:id")
    LiveData<User> getUser(int id);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}
