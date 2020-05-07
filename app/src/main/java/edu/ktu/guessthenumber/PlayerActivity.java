package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import edu.ktu.guessthenumber.Adapters.UserAdapter;
import edu.ktu.guessthenumber.ViewModels.PlayerActivityViewModel;

public class PlayerActivity extends AppCompatActivity {

    private PlayerActivityViewModel pViewmodel;
    private EditText etxtItem;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        etxtItem = findViewById(R.id.playerName);
        Button button = findViewById(R.id.createButton);

        pViewmodel = new ViewModelProvider(this).get(PlayerActivityViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etxtItem.getText().toString().trim().isEmpty()){
                    Toast.makeText(v.getContext(), getResources().getString(R.string.empty_Field), Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = etxtItem.getText().toString();
                User user = new User();
                user.setName(name);
                user.setNumberOfWins(0);
                user.setNumberOfLose(0);
                user.setLevel(1);
                pViewmodel.insertNewUser(user);
                etxtItem.getText().clear();
            }
        });

        List<User> users = new ArrayList<>();

        userAdapter = new UserAdapter(this, users);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        pViewmodel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdapter.setData(users);
                userAdapter.notifyDataSetChanged();
            }
        });
    }
}
