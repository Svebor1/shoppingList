package com.example.shoppinglista;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> { //nije
    private final String TAG = "ProductAdapter";
    public ProductAdapter(@NonNull Context context, ArrayList<Product> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.product_adapter_item, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Product product = getItem(position);

        // initializing our UI components of list view item.
        TextView nameTV = listitemView.findViewById(R.id.idTVtext);
        ImageView kanta = listitemView.findViewById(R.id.idIVimage);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        nameTV.setText(product.getKolicina() + " " + product.getNaziv());
        //toast ima onaj dugacak tekst

        // below line is use to add item click listener
        // for our item of list view.
        kanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection(firebaseUser.getUid()).document("data")
                        .collection("products").document(product.getFirebaseId()).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "DocumentSnapshot deleted with ID: " + product.getFirebaseId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                            }
                        });
                remove(product);
            } //
        });
        return listitemView;
    }
}
