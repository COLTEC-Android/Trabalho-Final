package br.ufmg.coltec.trabalhofinal.models.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.trabalhofinal.R;
import br.ufmg.coltec.trabalhofinal.models.User;

public class UserAdapter extends BaseAdapter {

    private ArrayList<User> users;
    private Context context;

    public UserAdapter(Context context, List<User> users){
        this.context = context;
        this.users = (ArrayList<User>) users;
    }

    @Override
    public int getCount() {
        return this.users.size();
    }

    @Override
    public Object getItem(int position) {
        return this.users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View newView = LayoutInflater.from(this.context).inflate(R.layout.adapter_user, parent, false);

        TextView lblName = newView.findViewById(R.id.adapter_user_name_in);
        TextView lblEmail = newView.findViewById(R.id.adapter_user_email_in);
        lblName.setText(lblName.getText() + users.get(position).getName());
        lblEmail.setText(lblEmail.getText()  + users.get(position).getEmail());
        return newView;
    }
}
