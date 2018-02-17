package flynn.tim.ciphersolver;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tim on 3/4/2015.
 */

public class MyListAdapter extends ArrayAdapter<Result> {

    private int mLayout;
    private ArrayList<Result> mArr;
    private LayoutInflater mInflater;

    //Constructor
    public MyListAdapter(Context context, int layout, ArrayList<Result> arr) {
        super(context, layout, arr);
        mLayout = layout;
        mArr = arr;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mArr.size();
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(mLayout, parent, false);
        }

        if (mArr.get(position).getChecked()) {
            ImageView imageview = convertView.findViewById(R.id.imageView1);
            imageview.setImageResource(R.drawable.check_mark);
        } else if (mArr.get(position).getEx()) {
            ImageView imageview = convertView.findViewById(R.id.imageView1);
            imageview.setImageResource(R.drawable.red_ex);
        } else {
            ImageView imageview = convertView.findViewById(R.id.imageView1);
            imageview.setImageResource(R.drawable.question_mark);
        }

        //Set the text view
        TextView name = convertView.findViewById(R.id.textView1);
        name.setText(mArr.get(position).getResult());
        //desc.setText("UUID: " + mArr.get(position).getString("UUID"));

        //Return the view
        return convertView;
    }

    public void updateList(ArrayList<Result> newItems) {
        mArr = newItems;
        notifyDataSetChanged();
    }
}