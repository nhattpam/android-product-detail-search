package com.nhattpam.productdetailrecyler3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {

    private List<Product> mListProduct;
    //tao 1 list i chang listProduct de ktra du lieu
    private List<Product> mListProductOld;

    private Context mContext;

    public ProductAdapter(Context context, List<Product> mListProduct) {
        this.mContext = context;
        this.mListProduct = mListProduct;
        this.mListProductOld = mListProduct;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product product = mListProduct.get(position);
        if (product == null) {
            return;
        }

        holder.imgAvatar.setImageResource(product.getImageId());
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(product.getPrice());

        //click
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //truyen object product qua detail
                onClickGoToDetailProduct(product);
            }
        });
    }

    private void onClickGoToDetailProduct(Product product) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product", product);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    //giai phong bien moi truong Context
    public void release() {
        mContext = null;
    }

    @Override
    public int getItemCount() {
        if (mListProduct != null) {
            return mListProduct.size();
        }
        return 0;
    }



    public class ProductViewHolder extends RecyclerView.ViewHolder {

        //click
        private RelativeLayout layoutItem;
        private ImageView imgAvatar;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvQuantity;
        private TextView tvDescription;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            //click
            layoutItem = itemView.findViewById(R.id.layout_item);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvName = itemView.findViewById(R.id.tv_name);
        }

    }
    //ham search
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    mListProduct = mListProductOld;
                }else{
                    List<Product> list = new ArrayList<>();
                    for (Product product: mListProductOld){
                        if(product.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(product);
                        }
                    }
                    mListProduct = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListProduct;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListProduct = (List<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}