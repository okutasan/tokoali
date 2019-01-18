package com.example.d2a.tokoali;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String NamaHolder, HargaHolder, GambarHolder ;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "http://192.168.43.126/tokoali/tambahkeranjang.php";


    private final String URL_DATA = "http://192.168.43.126/tokoali/data.php";

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(MainActivity.this);

        progressDialog = new ProgressDialog(MainActivity.this);

        recyclerView = (RecyclerView)findViewById(R.id.konten);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();



        loadData();
    }

    private void loadData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Memuat Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("result");

                    for (int i = 0; i<array.length(); i++){
                        JSONObject data = array.getJSONObject(i);
                        ListItem item = new ListItem(
                                data.getString("url_file"),
                                data.getString("nama_barang"),
                                data.getString("harga_barang")
                        );
                        listItems.add(item);
                    }
                    adapter = new MyAdapter(listItems, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            ListItem listItem = listItems.get(position);
                            Toast.makeText(getApplicationContext(), listItem.getJudul() + " is selected!", Toast.LENGTH_SHORT).show();
                            // Showing progress dialog at user registration time.
                            progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                            progressDialog.show();


                            NamaHolder= listItem.getJudul().toString().trim();
                            HargaHolder = listItem.getHarga().toString().trim();
                            GambarHolder = listItem.getGambar().toString().trim();

                            // Creating string request with post method.
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String ServerResponse) {

                                            // Hiding the progress dialog after all task complete.
                                            progressDialog.dismiss();

                                            // Showing response message coming from server.
                                            Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError volleyError) {

                                            // Hiding the progress dialog after all task complete.
                                            progressDialog.dismiss();

                                            // Showing error message if something goes wrong.
                                            Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                                        }
                                    }) {
                                @Override
                                protected Map<String, String> getParams() {

                                    // Creating Map String Params.
                                    Map<String, String> params = new HashMap<String, String>();

                                    // Adding All values to Params.
                                    params.put("nama_barang",NamaHolder);
                                    params.put("harga_barang", HargaHolder);
                                    params.put("url_file",GambarHolder);

                                    return params;
                                }

                            };

                            // Creating RequestQueue.
                            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                            // Adding the StringRequest object into requestQueue.
                            requestQueue.add(stringRequest);

                        }

                        @Override
                        public void onLongClick(View view, int position) {

                        }
                    }));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue antrian = Volley.newRequestQueue(this);
        antrian.add(stringRequest);
    }

    // MENAMPILKAN MENU DAN ITEM MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // MEMBUAT ITEM MENU MENJADI AKTIF DAN BERPINDAH
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
                finish();
                return true;
            case R.id.keranjang:
                Intent keranjang = new Intent(getApplicationContext(), keranjangActivity.class);
                startActivity(keranjang);
//                finish();
                return true;
            case R.id.tambahBarang:
                Intent tambah = new Intent(getApplicationContext(), TambahData.class);
                startActivity(tambah);
//                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // MENAHAN KEMBALI
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Dua Kali Untuk Keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
