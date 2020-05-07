package edu.ktu.guessthenumber.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import edu.ktu.guessthenumber.PlayerActivity;
import edu.ktu.guessthenumber.R;
import edu.ktu.guessthenumber.User;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>  {
    private List<User> users;
    private PlayerActivity playerActivity = new PlayerActivity();
    private Context mContext;

    public CustomAdapter(Context context, List<User> users) {
        this.mContext = context;
        this.users = users;
    }

    public void setData(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.players_results_layout, parent, false);
        ViewHolder viewHolder = new CustomAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.txtUserName.setText(String.format(mContext.getResources().getString(R.string.result_content),user.getName(), user.getNumberOfWins(), user.getNumberOfLose()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    //todo leave click listener until ready
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtUserName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUserName = itemView.findViewById(R.id.txt_player_name);
        }
    }

}
