package edu.ktu.guessthenumber.ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import edu.ktu.guessthenumber.User;
import edu.ktu.guessthenumber.Repositories.UserRepository;

public class PlayerActivityViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<User> user;
    private LiveData<List<User>> users;
    public PlayerActivityViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application.getApplicationContext());
        users = userRepository.getUsers();
    }
    public void insertNewUser(User user){
        userRepository.insertUser(user);
    }
    public void updateUser(User user){
        userRepository.updateUser(user);
    }
    public void deleteNewUser(User user){
        userRepository.deleteUser(user);
    }
    public LiveData<List<User>> getUsers(){
        return users;
    }
    public LiveData<User> getUser(int id){
        return userRepository.getUser(id);
    }
}
