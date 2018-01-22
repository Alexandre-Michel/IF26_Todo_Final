package fr.utt.if26.if26_projet_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alexandre on 21/01/2018.
 */

public class MyCustomAdapter extends ArrayAdapter{
    private Context context;
    private ArrayList<ToDo> todo;

    public MyCustomAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context,textViewResourceId, objects);
        this.context= context;
        todo=objects;

    }

    private class ViewHolder
    {
        TextView todoName;
        TextView todoDetail;
        TextView todoDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder=null;
        if (convertView == null)
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.liste_detail, null);

            holder = new ViewHolder();
            holder.todoName = (TextView) convertView.findViewById(R.id.todoName);
            holder.todoDetail = (TextView) convertView.findViewById(R.id.todoDetail);
            holder.todoDate =(TextView)convertView.findViewById(R.id.todoDate);
            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ToDo element_ajoute= todo.get(position);
        holder.todoName.setText("Quoi : " +  element_ajoute.getName() + "");
        holder.todoDetail.setText("Détails : "+ element_ajoute.getDetail()+"");
        holder.todoDate.setText("Quand : "+element_ajoute.getDate());
        return convertView;


    }
}