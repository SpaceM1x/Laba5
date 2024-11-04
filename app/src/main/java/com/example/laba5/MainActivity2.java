package com.example.laba5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class MainActivity2 extends AppCompatActivity {


    private String FName;
    private String SName;

    private Button b1;
    private Button b2;
    private Button b3;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second_act);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button4);
        text = findViewById(R.id.textView);

        Intent intent = getIntent();// Создаю интент для получения данных из прошлого интента
        FName = intent.getStringExtra("FName");
        SName = intent.getStringExtra("SName");

        text.setText("Привет, " + FName + " " + SName + "!");

        b1.setText("Привет, " + FName + " " + SName + "!");
        b2.setText("Здравствуйте, " + FName + " " + SName + ". Рад вас видеть!");
        b3.setText("Приветик, " + FName + "! Как дела?");

        b1.setOnClickListener(v -> onClick(b1));
        b2.setOnClickListener(v -> onClick(b2));
        b3.setOnClickListener(v -> onClick(b3));

    }

    public void onClick(Button button){// сразу передаю не View а кнопки
        String x = button.getText().toString();

        Intent resultIntent = new Intent(MainActivity2.this, MainActivity.class);
// Я создал intent чтобы отправить данные в result предыдущего activity
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        intent.putExtra("message",x);
//        startActivity(intent);

        resultIntent.putExtra("message", x);
        setResult(Activity.RESULT_OK, resultIntent); // Возвращаем результат
        finish();

    }
}