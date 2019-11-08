package com.hassan.productai.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hassan.productai.Adaptors.RecyclerViewAdaptorAliExpress;
import com.hassan.productai.Models.Products;
import com.hassan.productai.R;
import com.hassan.productai.R;
import com.hassan.productai.Adaptors.RecyclerViewAdaptorEbay;
import com.hassan.productai.Models.Products;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "https://gist.githubusercontent.com/Hassan005/b2dabba5c90929d574962d0800a44c41/raw/13db4879e2417b4fd4eba41fb4c1a78e315a0bf5/apisa.json" ;
    private JsonArrayRequest request ;

    private RequestQueue requestQueue ;
    private List<Products> istproductEbay ;
    private List<Products> istproductsAliExpress ;
    private RecyclerView recycler_view_Ebay ,recycler_view_ali_express;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        istproductEbay = new ArrayList<>() ;
        istproductsAliExpress=new ArrayList<>();
        recycler_view_Ebay = findViewById(R.id.ebayrecyclerview);
       recycler_view_ali_express=findViewById(R.id.recyclerviewidaliexpress);
        jsonrequest();



    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
//                JSONObject jsonObject  = null ;
                    JSONObject shirtObjEbay = response.getJSONObject(0);
                    JSONObject shirtObjAliExpress = response.getJSONObject(1);
//                    System.out.println("first elemrnt"+shirtObj);
//                    JSONObject shirtObjA=response.getJSONObject(1);
//                    System.out.println("Second elemrnt"+shirtObjA);
                    // Retrieves "colorArray" from the JSON object
                    JSONArray ShirtArrayEbay = shirtObjEbay.getJSONArray("blue shirt");
                    JSONArray ShirtArrayAliExpress = shirtObjAliExpress.getJSONArray("blue shirt");
//                    JSONArray shirtObjAraayali = shirtObjA.getJSONArray("blue shirt");
                    for (  int i = 0; i < ShirtArrayEbay.length(); i++) {


                        JSONObject jsonObject = ShirtArrayEbay.getJSONObject(i);

                        Products products = new Products();
                        products.setProduct_name(jsonObject.getString("Product name"));
                        System.out.println("mani"+products);
                        products.setPrice(jsonObject.getString("Price"));
                        products.setPrfile_link(jsonObject.getString("Profile Link"));
                        products.setImage_link(jsonObject.getString("Image Link"));

                        istproductEbay.add(products);



                    }

                    for ( int i=0;i<ShirtArrayAliExpress.length();i++)
                    {
                        JSONObject jsonObjecta = ShirtArrayAliExpress.getJSONObject(i);
                        System.out.println("Ali express: "+jsonObjecta);

                        Products product = new Products();

                        product.setProduct_name(jsonObjecta.getString("Product name"));

                        product.setPrice(jsonObjecta.getString("Price"));
                        product.setPrfile_link(jsonObjecta.getString("Profile Link"));
                        product.setImage_link(jsonObjecta.getString("Image Link"));

                        istproductsAliExpress.add(product);
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }




                setuprecyclerview(istproductEbay);
                setuprecyclerviewAliExpress(istproductsAliExpress);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request) ;


    }

    private void setuprecyclerview(List<Products> istproductsEbay) {


        RecyclerViewAdaptorEbay myadapter = new RecyclerViewAdaptorEbay(this,istproductsEbay) ;

        recycler_view_Ebay.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        recycler_view_Ebay.setAdapter(myadapter);


    }
    private void setuprecyclerviewAliExpress(List<Products> ali_express_products) {



        RecyclerViewAdaptorAliExpress myadapter=new RecyclerViewAdaptorAliExpress(this,ali_express_products);

        recycler_view_ali_express.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        recycler_view_ali_express.setAdapter(myadapter);

    }
}