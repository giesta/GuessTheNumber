package edu.ktu.guessthenumber.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import edu.ktu.guessthenumber.GameActivity;
import edu.ktu.guessthenumber.R;
import edu.ktu.guessthenumber.User;
import edu.ktu.guessthenumber.Repositories.UserRepository;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private List<User> users;
    private Context mContext;

    public UserAdapter(Context context, List<User> users) {
        this.mContext = context;
        this.users = users;
    }

    public void setData(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.txtUserName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    //todo leave click listener until ready
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtUserName;
        ImageView imageView;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtUserName = view.findViewById(R.id.txt_item_name);
            imageView = view.findViewById(R.id.img_delete);
            imageView.setOnClickListener(this::onClick);
            txtUserName.setOnClickListener(this::startGame);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            User user = users.get(position);
            UserRepository userRepository = new UserRepository(v.getContext());
            userRepository.deleteUser(user);
            Toast.makeText(v.getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
        }


        public void startGame(View v) {
            int position = getAdapterPosition();
            User user = users.get(position);
            Intent intent = new Intent(mContext, GameActivity.class);
            intent.putExtra("user", user);
            mContext.startActivity(intent);
        }
    }
}
