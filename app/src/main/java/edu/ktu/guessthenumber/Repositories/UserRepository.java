package edu.ktu.guessthenumber.Repositories;

import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

import edu.ktu.guessthenumber.AppDatabase;
import edu.ktu.guessthenumber.User;

public class UserRepository {
    private Context context;
    public UserRepository(Context context){
        this.context = context.getApplicationContext();
    }
    public void insertUser(String name, int numberOfWins, int numberOfLose){
        final User user = new User();
        user.setName(name);
        user.setNumberOfLose(numberOfLose);
        user.setNumberOfWins(numberOfWins);
        insertUser(user);
    }
    public void updateUser(final User user){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(context).daoAccess().updateUser(user);
            }
        });
    }
    public void deleteUser(final int id){
        final User user = getUser(id).getValue();
        if(user != null){
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    AppDatabase.getInstance(context).daoAccess().deleteUser(user);
                }
            });
        }
    }
    public void deleteUser(User user){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(context).daoAccess().deleteUser(user);
            }
        });
    }
    public LiveData<User> getUser(int id){
        return AppDatabase.getInstance(context).daoAccess().getUser(id);
    }
    public LiveData<List<User>> getUsers(){
        return AppDatabase.getInstance(context).daoAccess().getAllUsers();
    }

    public void insertUser(final User user) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(context).daoAccess().insertUser(user);
            }
        });
    }
}
