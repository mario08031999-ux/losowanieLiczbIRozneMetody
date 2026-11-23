package com.example.losowanieimetody;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText ileLiczb, odJakiej, doJakiej;
    Button losuj, mediana, dominanta, suma, maxymalna, minimalna, srednia;
    TextView wynik;
    ListView listView;
    int[] tablica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ileLiczb = findViewById(R.id.iloscLiczb);
        odJakiej = findViewById(R.id.odJakiejLiczby);
        doJakiej = findViewById(R.id.doJakiejLiczby);
        losuj =findViewById(R.id.losuj);
        mediana = findViewById(R.id.mediana);
        dominanta = findViewById(R.id.dominanta);
        suma = findViewById(R.id.suma);
        maxymalna = findViewById(R.id.max);
        minimalna = findViewById(R.id.min);
        srednia = findViewById(R.id.srednia);
        wynik = findViewById(R.id.textView);
        listView = findViewById(R.id.wyswietlanie);

        losuj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String liczbaLiczbStr = ileLiczb.getText().toString().trim();
                        String aStr = odJakiej.getText().toString().trim();
                        String bStr = doJakiej.getText().toString().trim();


                        int ilosc = Integer.parseInt(liczbaLiczbStr);
                        int min = Integer.parseInt(aStr);
                        int max = Integer.parseInt(bStr);

                        if (min > max) {
                            Toast.makeText(MainActivity.this, "Wartość OD nie może być większa niż DO!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        tablica = new int[ilosc];
                        ArrayList<Integer> listaDoWyswietlenia = new ArrayList<>();
                        Random random = new Random();

                        for (int i = 0; i < ilosc; i++) {
                            int wylosowana = random.nextInt((max - min) + 1) + min;
                            tablica[i] = wylosowana;
                            listaDoWyswietlenia.add(wylosowana);
                        }

                        ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listaDoWyswietlenia);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
        );




    }
}