package com.example.simplechatapp;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    Context context;
    ArrayList<TextClass> chats;
    Boolean person1;

    //the passed values get initialised here
    public RecyclerAdapter(Context context , ArrayList<TextClass> chats , Boolean person) {

        this.context = context;
        this.chats = chats;
        this.person1 = person;

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chat , parent , false);
        return new RecyclerViewHolder(view);
    }

    //here
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        TextClass temp;
        temp = chats.get(position);

        if(this.person1 == true)
            if(temp.getType() == true)
            {
                holder.sent.setText(temp.getText());
                holder.sent.setVisibility(View.VISIBLE);

            }
            else
            {
                holder.received.setText(temp.getText());
                holder.received.setVisibility(View.VISIBLE);
            }
        else
        if(temp.getType() == false)
        {
            holder.sent.setText(temp.getText());
            holder.sent.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.received.setText(temp.getText());
            holder.received.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    //here

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView sent , received;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            sent = (TextView) itemView.findViewById(R.id.sent);
            received = (TextView) itemView.findViewById(R.id.received);
        }
    }

}