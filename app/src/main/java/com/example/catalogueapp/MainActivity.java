package com.example.catalogueapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.catalogueapp.database.CatalogueDatabase;
import com.example.catalogueapp.database.DatabaseReceiver;
import com.example.catalogueapp.database.DatabaseTask;
import com.example.catalogueapp.database.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DatabaseReceiver {

    public static String MESSAGE = "com.example.catalogueApp.MainActivity";
    ProductViewModel products;
    ProductCatalogueAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = ViewModelProviders.of(this).get(ProductViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter= new ProductCatalogueAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*
        products.getProducts(getApplicationContext()).observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d("CHANGED HERE!","ENTERED " + products);
                getAll(products);
            }
        });*/
    }

    public void actionFromButton(View view){
        Log.d("CLICKED" , "FROM VIEW " + view);
    }

    public void getAll(List<Product> products){
/*
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup table = findViewById(R.id.catalogueList);
        table.removeAllViews();

        for (Product actual:products) {z<
            Log.d("Hello","PRODUCTS NAME HERE! " + actual.name);
            View row = inflater.inflate(R.layout.row_layout,table, false );
            TextView vw = row.findViewById(R.id.productName);
            vw.setText(actual.name);
            table.addView(row);
        }

 */
    }
    public void doAction(View view){

        String src = "%"+((EditText)findViewById(R.id.searchText)).getText()+"%";
        products.searchProducts(getApplicationContext(),src).observe(this,
                new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                Log.d("CHANGED HERE!","ENTERED " + products);
                //getAll(products);
                adapter.setProducts(products);
            }
        });

        /*
        // version 2 to obtain products from database!
        DatabaseTask task = new DatabaseTask(getApplicationContext(), this);
        Product p = new Product();
        p.name = "%"+((EditText)findViewById(R.id.searchText)).getText()+"%";
        task.execute(p);


        /*
        Log.d("CUSTOM","CLICK ON  ME!");
        TextView v = findViewById(R.id.editText);
        TextView vt = findViewById(R.id.viewTitleText);
        vt.setText(v.getText());

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(MESSAGE,""+v.getText());
        Log.d("TEXT " ,"" + v.getText());
        startActivity(intent);*/
    }

    public void goToWeb(View view){
        Intent intent = new Intent(this, WebCallActivity.class);
        startActivity(intent);
    }
    public void goToService(View view){
        Intent intent = new Intent(this, DoServiceActivity.class);
        startActivity(intent);
    }
}
