package com.example.shoppinglista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShoppingDodatiActivity extends AppCompatActivity {
    private static final String TAG = "ShoppingDodatiActivity";
    Button dodajButton;
    EditText imeProizvoda;
    EditText kolicinaProizvoda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_dodati);
        imeProizvoda = findViewById(R.id.ImeProizvoda);
        dodajButton = findViewById(R.id.DodajButton);
        kolicinaProizvoda = findViewById(R.id.KolicinaProizvoda);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spremi();

            }
        };
        dodajButton.setOnClickListener(listener);
    }
    private void spremi() {
        Toast.makeText(getApplicationContext(), imeProizvoda.getText().toString(), Toast.LENGTH_LONG).show();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseProduct newProduct = new FirebaseProduct(imeProizvoda.getText().toString(),Integer.parseInt(kolicinaProizvoda.getText().toString()));
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(firebaseUser.getUid()).document("data")
                .collection("products").add(newProduct)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override

                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        this.finish();
    }
}