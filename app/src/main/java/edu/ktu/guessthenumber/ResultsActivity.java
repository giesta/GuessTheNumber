package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import edu.ktu.guessthenumber.Adapters.CustomAdapter;
import edu.ktu.guessthenumber.ViewModels.PlayerActivityViewModel;

public class ResultsActivity extends AppCompatActivity implements WebTask.WebRequestFinished {

    private List<User> data;
    private PlayerActivityViewModel pViewmodel;
    private CustomAdapter resultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        data = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.results_recycler_view);
        pViewmodel = new ViewModelProvider(this).get(PlayerActivityViewModel.class);
        resultsAdapter = new CustomAdapter(this, data);
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        pViewmodel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                resultsAdapter.setData(users);
                resultsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void webRequestResult(String s){
        try {
            JSONArray jsonArray = new JSONArray(s);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                int number = jsonObject.getInt("number");
                int result = jsonObject.getInt("result");
                Log.d("WebTas", name + " " + number + " " + result);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
