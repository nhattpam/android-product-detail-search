package com.nhattpam.productdetailrecyler3;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvData;
    private ProductAdapter productAdapter;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvData = findViewById(R.id.rcv_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);

        productAdapter = new ProductAdapter(this, getListProduct());
        rcvData.setAdapter(productAdapter);

    }

    private List<Product> getListProduct() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(R.drawable.vina, "Vinaphone", "123", "2", "good"));
        list.add(new Product(R.drawable.viettel, "Vitel", "456", "1", "bad"));
        list.add(new Product(R.drawable.mobi, "Mobiphone", "345", "3", "well"));
        list.add(new Product(R.drawable.vnmobile, "VietnameMobile", "789", "3", "wort"));
        return list;
    }

    //giai phong bien moi truong COntext
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(productAdapter != null){
            productAdapter.release();
        }
    }

    //them menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        //search
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //custom khoang cach khung search
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        //function search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                productAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}