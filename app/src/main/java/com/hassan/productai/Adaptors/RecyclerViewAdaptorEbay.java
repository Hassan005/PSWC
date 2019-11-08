package com.hassan.productai.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hassan.productai.Models.Products;
import java.util.List;
import com.hassan.productai.R;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdaptorEbay extends RecyclerView.Adapter<RecyclerViewAdaptorEbay.MyViewHolder>
{
    private Context ebay_context;
    private List<Products> products_ebay;
    RequestOptions option;

    public RecyclerViewAdaptorEbay(Context ebay_context, List<Products> products_ebay) {
        this.ebay_context = ebay_context;
        this.products_ebay = products_ebay;
        option=new RequestOptions().centerCrop().placeholder(R.drawable.products).error(R.drawable.aliexpress);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(ebay_context);
        view=layoutInflater.inflate(R.layout.product_row_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_title_ebay.setText(products_ebay.get(position).getProduct_name());
        holder.tv_price_ebay.setText(products_ebay.get(position).getPrice());
        holder.tv_link_ebay.setText(products_ebay.get(position).getPrfile_link());


        Glide.with(ebay_context).load(products_ebay.get(position).getImage_link()).apply(option).into(holder.imageViewEbay);
    }

    @Override
    public int getItemCount() {
        return products_ebay.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
{

    TextView tv_title_ebay;
    TextView tv_price_ebay;
    TextView tv_link_ebay;
    TextView tv_id_ebay;
    ImageView imageViewEbay;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_title_ebay=itemView.findViewById(R.id.product_name);
        tv_price_ebay=itemView.findViewById(R.id.price);
        tv_link_ebay=itemView.findViewById(R.id.product_link);
        imageViewEbay=itemView.findViewById(R.id.thumbnail);
    }
}
}
