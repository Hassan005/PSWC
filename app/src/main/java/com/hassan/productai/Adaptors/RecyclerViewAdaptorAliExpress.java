package com.hassan.productai.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hassan.productai.R;
import com.hassan.productai.Models.Products;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdaptorAliExpress extends RecyclerView.Adapter<RecyclerViewAdaptorAliExpress.MyViewHolderAliExpress>
{
    private Context ali_express_context;
    private List<Products> ali_express_products;
RequestOptions option;
    public RecyclerViewAdaptorAliExpress(Context ali_express_context, List<Products> ali_express_products) {
        this.ali_express_context = ali_express_context;
        this.ali_express_products = ali_express_products;
        option=new RequestOptions().centerCrop().placeholder(R.drawable.aliexpress).error(R.drawable.aliexpress);
    }

    @NonNull
    @Override
    public MyViewHolderAliExpress onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view;
        LayoutInflater inflater_ali_express=LayoutInflater.from(ali_express_context);

        view=inflater_ali_express.inflate(R.layout.product_row_item,parent,false);


        return  new MyViewHolderAliExpress(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderAliExpress holder, int position) {
        holder.tv_title_ali_express.setText(ali_express_products.get(position).getProduct_name());
        holder.tv_price_ali_express.setText(ali_express_products.get(position).getPrice());
        holder.tv_link_ali_express.setText(ali_express_products.get(position).getPrfile_link());


        Glide.with(ali_express_context).load(ali_express_products.get(position).getImage_link()).apply(option).into(holder.imageViewAliExpress);

    }

    @Override
    public int getItemCount() {
        return ali_express_products.size();
    }

    public static class MyViewHolderAliExpress extends RecyclerView.ViewHolder
    {
        TextView tv_title_ali_express;
        TextView tv_price_ali_express;
        TextView tv_link_ali_express;
        TextView tv_id_ali_express;
        ImageView imageViewAliExpress;
        public MyViewHolderAliExpress(@NonNull View itemView) {
            super(itemView);
            tv_title_ali_express=itemView.findViewById(R.id.product_nameae);
            tv_price_ali_express=itemView.findViewById(R.id.priceae);
            tv_link_ali_express=itemView.findViewById(R.id.product_linkae);
            imageViewAliExpress=itemView.findViewById(R.id.thumbnailae);
        }
    }
}
