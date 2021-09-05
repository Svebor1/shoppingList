package com.example.shoppinglista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShoppingListaActivity extends AppCompatActivity {
    ProductAdapter arr;

    private Button dodavanje;
    private ListView prikazListe;
    ArrayList<Product> shoppingLista;
    private static final String TAG = "GoogleActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_lista);
        dodavanje = findViewById(R.id.Dodavanje);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajListu();
            }
        };
        dodavanje.setOnClickListener(listener);

    }
    private void ucitajPodatke() {
        shoppingLista = new ArrayList<Product>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        db.collection(firebaseUser.getUid()).document("data").collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                //            Product product = document.toObject(Product.class);
                                String ime = document.getData().get("naziv").toString();
                                Integer broj = Integer.parseInt(document.getData().get("kolicina").toString());
                                shoppingLista.add(new Product(ime, broj, document.getId()));

                            }
                            prikaziListu();

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    } //pokaze se toast
                });
    } //da i nije se dodalo

    private void prikaziListu() {
        prikazListe = findViewById(R.id.prikazListe);
        arr = new ProductAdapter(this, shoppingLista);
        prikazListe.setAdapter(arr);
    }
    private void dodajListu() {
        Intent intent = new Intent(this, ShoppingDodatiActivity.class);
        startActivity(intent);
    }
    @Override
    public void onResume(){
        super.onResume();
        Toast.makeText(this, "resume", Toast.LENGTH_LONG).show();
        ucitajPodatke();

//
    }
}
