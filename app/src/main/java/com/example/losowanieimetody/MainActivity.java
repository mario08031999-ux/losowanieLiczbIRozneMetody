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
import androidx.lifecycle.viewmodel.CreationExtras;

import java.util.ArrayList;
import java.util.Arrays;
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


                        int ilosc = Integer.parseInt(ileLiczb.getText().toString().trim());
                        int min = Integer.parseInt(odJakiej.getText().toString().trim());
                        int max = Integer.parseInt(doJakiej.getText().toString().trim());

                        if (ilosc <= 0) {
                            Toast.makeText(MainActivity.this, "Podaj ilość większą od 0!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (min > max) {
                            Toast.makeText(MainActivity.this, "Wartość OD nie może być większa niż DO!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        tablica = new int[ilosc];
                        ArrayList<Integer> listaDoWyswietlenia = new ArrayList<>();
                        Random random = new Random();

                        for (int i = 0; i < ilosc; i++) {
                            int wylosowana = random.nextInt(max - min + 1) + min;
                            tablica[i] = wylosowana;
                            listaDoWyswietlenia.add(wylosowana);
                        }

                        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(
                                MainActivity.this,
                                android.R.layout.simple_list_item_1,
                                listaDoWyswietlenia
                        );

                        listView.setAdapter(adapter);
                    }
                }
        );

        mediana.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (tablica == null || tablica.length == 0) {
                            return;
                        }
                        int ilosc = Integer.parseInt(ileLiczb.getText().toString().trim());
                        wynik.setText("");

                        double mediana = 0;
                        Arrays.sort(tablica);
                        if (ilosc % 2 == 0) {
                            mediana = (tablica[ilosc / 2 - 1] + tablica[ilosc / 2]) / 2.0;
                        } else {
                            mediana = tablica[ilosc / 2];
                        }
                        wynik.setText(mediana + "");

                    }
                }
        );

        dominanta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tablica == null || tablica.length == 0) {
                            return;
                        }
                        int ilosc = Integer.parseInt(ileLiczb.getText().toString().trim());
                        wynik.setText("");

                        Arrays.sort((tablica));

                        int maksWystapien = 1;
                        int minWystapien = tablica.length;
                        int aktualnyLicznik = 1;
                        int unikalneElementy = 1;

                        for (int i = 1; i < tablica.length; i++) {
                            if (tablica[i] == tablica[i - 1]) {
                                aktualnyLicznik++;
                            } else {
                                if (aktualnyLicznik > maksWystapien) maksWystapien = aktualnyLicznik;
                                if (aktualnyLicznik < minWystapien) minWystapien = aktualnyLicznik;
                                aktualnyLicznik = 1;
                                unikalneElementy++;
                            }
                        }
                        if (aktualnyLicznik > maksWystapien) maksWystapien = aktualnyLicznik;
                        if (aktualnyLicznik < minWystapien) minWystapien = aktualnyLicznik;

                        if (maksWystapien == minWystapien && unikalneElementy > 1) {
                            wynik.setText("Brak dominanty.");
                            return;
                        }

                        String wynikStr = "Dominanta: ";
                        aktualnyLicznik = 1;

                        for (int i = 1; i < tablica.length; i++) {
                            if (tablica[i] == tablica[i - 1]) {
                                aktualnyLicznik++;
                            } else {
                                if (aktualnyLicznik == maksWystapien) {
                                    wynikStr += tablica[i - 1] + " "; // += zamiast =
                                }
                                aktualnyLicznik = 1;
                            }
                        }

                        if (aktualnyLicznik == maksWystapien) {
                            wynikStr += tablica[tablica.length - 1];
                        }

                        wynik.setText(wynikStr);
                    }
                }
        );

        suma.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tablica == null || tablica.length == 0) {
                            return;
                        }
                        int ilosc = Integer.parseInt(ileLiczb.getText().toString().trim());
                        wynik.setText("");

                        int sumaLiczb = 0;
                        for (int i = 0; i < tablica.length; i++) {
                            sumaLiczb += tablica[i];
                        }
                        wynik.setText(sumaLiczb + "");
                    }
                }
        );

        maxymalna.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int maks = 0;
                        Arrays.sort(tablica);
                        maks = tablica[tablica.length-1];
                        wynik.setText(maks + "");
                    }
                }
        );

        minimalna.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int mini = 0;
                        Arrays.sort(tablica);
                        mini = tablica[0];
                        wynik.setText(mini + "");
                    }
                }
        );

        srednia.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );




    }
}