package com.nhattpam.productdetailrecyler3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //nhan du lieu tu ben main
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        Product product = (Product) bundle.get("object_product");

        ImageView imageView = findViewById(R.id.profile_image_product);
        imageView.setImageResource(product.getImageId());
        TextView tvNameProduct = findViewById(R.id.tv_name_product);
        tvNameProduct.setText(product.getName());
        TextView tvPriceProduct = findViewById(R.id.tv_price_product);
        tvPriceProduct.setText(product.getPrice());
        TextView tvQuantityProduct = findViewById(R.id.tv_quantity_product);
        tvQuantityProduct.setText(product.getQuantity());
        TextView tvDescriptionProduct = findViewById(R.id.tv_description_product);
        tvDescriptionProduct.setText(product.getDescription());
    }
}