package edu.ktu.guessthenumber;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = User.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabaseInstance;
    public static synchronized AppDatabase getInstance(Context context){
        if(appDatabaseInstance==null) {
            appDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"appDb").build();
        }
        return appDatabaseInstance;
    }
    public abstract DaoAccess daoAccess();
}