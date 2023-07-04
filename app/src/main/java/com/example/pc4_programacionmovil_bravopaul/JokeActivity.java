package com.example.pc4_programacionmovil_bravopaul;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pc4_programacionmovil_bravopaul.model.Joke;
import com.example.pc4_programacionmovil_bravopaul.service.JokeApiClient;
import com.example.pc4_programacionmovil_bravopaul.service.JokeApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JokeActivity extends AppCompatActivity {

    private TextView resultTextView;
    private RadioGroup categoryRadioGroup;
    private RadioGroup languageRadioGroup;
    private Button getJokeButton;

    private JokeApiService apiService;
    private TextView jsonResultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jsonResultTextView = findViewById(R.id.jsonResultTextView);
        resultTextView = findViewById(R.id.resultTextView);
        categoryRadioGroup = findViewById(R.id.categoryRadioGroup);
        languageRadioGroup = findViewById(R.id.languageRadioGroup);
        getJokeButton = findViewById(R.id.getJokeButton);

        apiService = JokeApiClient.getApiService();

        getJokeButton.setOnClickListener(view -> {
            int selectedCategoryId = categoryRadioGroup.getCheckedRadioButtonId();
            int selectedLanguageId = languageRadioGroup.getCheckedRadioButtonId();

            RadioButton categoryRadioButton = findViewById(selectedCategoryId);
            RadioButton languageRadioButton = findViewById(selectedLanguageId);

            if (categoryRadioButton != null && languageRadioButton != null) {
                String category = categoryRadioButton.getText().toString();
                String language;

                if (languageRadioButton.getText().toString().equalsIgnoreCase("Inglés")) {
                    language = "en";
                } else if (languageRadioButton.getText().toString().equalsIgnoreCase("Español")) {
                    language = "es";
                } else {
                    // Idioma no reconocido
                    return;
                }

                if (category.equalsIgnoreCase("Cualquiera")) {
                    getAnyJoke(language);
                } else if (category.equalsIgnoreCase("Programación")) {
                    getProgrammingJoke(language);
                }
            }
        });
    }

    private void getAnyJoke(String language) {
        Call<Joke> call = apiService.getAnyJoke(language, "single");
        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                if (response.isSuccessful()) {
                    Joke joke = response.body();
                    if (joke != null) {
                        System.out.println(joke.getJoke());
                        resultTextView.setText(joke.getJoke());
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        String jsonResponse = gson.toJson(joke);
                        jsonResultTextView.setText(jsonResponse);
                    }
                } else {
                    // Procesar errores de la respuesta
                    Toast.makeText(JokeActivity.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                // Manejar errores de la solicitud
                Toast.makeText(JokeActivity.this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProgrammingJoke(String language) {
        Call<Joke> call = apiService.getProgrammingJoke(language, "single");
        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                if (response.isSuccessful()) {
                    Joke joke = response.body();
                    if (joke != null) {
                        System.out.println(joke.getJoke());
                        resultTextView.setText(joke.getJoke());
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        String jsonResponse = gson.toJson(joke);
                        jsonResultTextView.setText(jsonResponse);
                    }
                } else {
                    // Procesar errores de la respuesta
                    Toast.makeText(JokeActivity.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                // Manejar errores de la solicitud
                Toast.makeText(JokeActivity.this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        });
    }
}