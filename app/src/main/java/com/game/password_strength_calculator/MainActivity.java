package com.game.password_strength_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    View root;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        root = findViewById(R.id.root);

        // Установим наблюдатель текста на текст редактирования, чтоб обновлять надежность в реальном времени
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                calculatePasswordStrength(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void calculatePasswordStrength(String str){
        //мы должны определить перечисление прочности пароля с помощью статического метода вычесления, возвращающего надежность пароля
       PasswordStrength passwordStrength = PasswordStrength.calculate(str);
       textView.setText(passwordStrength.msg);
       root.setBackgroundColor(passwordStrength.color);


    }
}