package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static com.example.myapplication2.DBmain.TABLENAME;

public class MainActivity2 extends AppCompatActivity {
DBmain dBmain;
SQLiteDatabase sqLiteDatabase;
ListView lv;
ArrayList<Model>modelArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dBmain = new DBmain(this);
        findid();
        displayData();
    }

    private void displayData() {
        sqLiteDatabase=dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLENAME + "", null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            byte[]image=cursor.getBlob(2);

            modelArrayList.add(new Model(id, name,image));

        }
        //custom adapter
        Custom adapter=new Custom(this,R.layout.singledata,modelArrayList);
        lv.setAdapter(adapter);
    }

    private void findid() {
        lv=findViewById(R.id.lv);
    }

    private class Custom extends BaseAdapter {

        private Context context;
        private int layout;
        private ArrayList<Model>modelArrayList=new ArrayList<>();
        //constructer

        public Custom(Context context, int layout, ArrayList<Model> modelArrayList) {
            this.context = context;
            this.layout = layout;
            this.modelArrayList = modelArrayList;
        }
        private class ViewHolder{
            TextView txtname;
            Button edit,delete;
            ImageView imageView;
            CardView cardView;
        }

        @Override
        public int getCount() {
            return modelArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return modelArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout, null);

            //find id
            holder.txtname=convertView.findViewById(R.id.txtname);
            holder.imageView=convertView.findViewById(R.id.imageview);
            holder.edit=convertView.findViewById(R.id.btn_edit);
            holder.delete=convertView.findViewById(R.id.btn_delete);
            holder.cardView=convertView.findViewById(R.id.cardview);
            convertView.setTag(holder);



            Model model=modelArrayList.get(position);
            //set text in textView
            holder.txtname.setText(model.getName());
            //set image in image view
            byte[]image=model.getImage();
            Bitmap bitmap= BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.imageView.setImageBitmap(bitmap);

            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle data=new Bundle();
                    data.putInt("id", model.getId());
                    data.putString("name",model.getName());
                    data.putByteArray("avatar", model.getImage());
                    //send data in main activity
                    Intent intent=new Intent(MainActivity2.this, MainActivity.class);
                    intent.putExtra("userdata", data);
                    startActivity(intent);
                }
            });

            // delete data
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqLiteDatabase=dBmain.getReadableDatabase();
                    long recdelete=sqLiteDatabase.delete(TABLENAME, "id="+model.getId(),null);
                    if(recdelete!=-1){
                        Snackbar.make(v,"record deleted"+model.getId()+"\n"+model.getName(), Snackbar.LENGTH_LONG).show();
                        //for remove from current position
                        modelArrayList.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            return convertView;
        }
    }
}