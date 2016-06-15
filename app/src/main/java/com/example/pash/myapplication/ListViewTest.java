package com.example.pash.myapplication;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * Created by PASH on 2016/6/11.
 */
public class ListViewTest extends Activity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private ArrayAdapter<String> arr_adaptter;
    private SimpleAdapter simp_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);
        listView = (ListView) findViewById(R.id.listView);
        String[] arr_data = {"data1", "data2", "data3", "data4",
                "data5", "data6", "data7", "data8", "data9", "data10" };
        arr_adaptter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr_data);
        listView.setAdapter(arr_adaptter);

        LayoutAnimationController lac=new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.zoom_in));
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        listView.setLayoutAnimation(lac);
        listView.startLayoutAnimation();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "position = " + position, Toast.LENGTH_SHORT).show();
    }
}
