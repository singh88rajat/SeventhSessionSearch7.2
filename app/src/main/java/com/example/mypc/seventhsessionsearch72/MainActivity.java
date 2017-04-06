package com.example.mypc.seventhsessionsearch72;

import android.content.ContentValues;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mypc.seventhsessionsearch72.model.ProductList;
import com.example.mypc.seventhsessionsearch72.utils.CommonUtilities;
import com.example.mypc.seventhsessionsearch72.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ProductList> dataList;

    DBHelper dbHelper;
    ListView list;
    String[] product_names= new String[]{
            "Product 1", "Product 2", "Product 3", "Product 4", "Product 5", "Product 6", "Product 7"};
    String[] ids= new String[]{"1234", "2345", "3456", "4567", "5678", "6789", "7890"};
    private static final int reqCode=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper= CommonUtilities.getDBObject(this);
       // dbHelper = DBHelper.getInstance(this);
        list= (ListView)findViewById(R.id.list);
        int count= dbHelper.getFullCount(Constants.PRODUCT_TABLE, null);
        if(count==0) {
            insertProductRecord();
        }
        dataList = dbHelper.getAllProducts();

        List<String> listTitle = new ArrayList<String>();


        for (int i = 0; i < dataList.size(); i++) {
            listTitle.add(i, dataList.get(i).getProductname());
        }

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,R.layout.row_layout, R.id.listText, listTitle);
        myAdapter.notifyDataSetChanged();
        //list.setOnItemClickListener(this);
        list.setAdapter(myAdapter);
        AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(myAdapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);
        //Toast.makeText(this,"Hello", Toast.LENGTH_SHORT ).show();
    }

    private void insertProductRecord(){
        for(int i=0; i<product_names.length; i++) {
            ContentValues vals = new ContentValues();
            //vals.put(Constants.ID, ids[i]);
            vals.put(Constants.PRODUCT_NAME, product_names[i]);
            dbHelper.insertContentVals(Constants.PRODUCT_TABLE, vals);
        }
    }

}
