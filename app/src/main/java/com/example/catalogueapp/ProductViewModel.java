package com.example.catalogueapp;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.catalogueapp.database.CatalogueDatabase;
import com.example.catalogueapp.database.Product;

import java.util.List;

public class ProductViewModel extends ViewModel {

    private MutableLiveData<List<Product>> products;

    public ProductViewModel(){
        super();
    }
    public MutableLiveData<List<Product>> getProducts(Context ctx){
        if( products == null){
            products = new MutableLiveData<>();

            CatalogueDatabase db = Room.databaseBuilder(ctx,
                    CatalogueDatabase.class ,
                    "catalogue-database").build();
            List<Product> myProducts = db.productDao().getAll();
            products.setValue(myProducts);
        }

        return products;
    }
}
