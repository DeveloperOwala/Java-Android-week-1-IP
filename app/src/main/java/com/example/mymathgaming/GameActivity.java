package com.example.mymathgaming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int partA = 9;
        int partB = 9;
        int correctAnswer = partA * partB;
        int wrongAnswer1 = correctAnswer - 1;
        int wrongAnswer2 = correctAnswer + 1;

        TextView textObjectPartA =  findViewById(R.id.textPartA);
        TextView textObjectPartB =  findViewById(R.id.textPartB);
        Button buttonObjectChoice1 = findViewById(R.id.buttonChoice1);
        Button buttonObjectChoice2 = findViewById(R.id.buttonChoice2);
        Button buttonObjectChoice3 = findViewById(R.id.buttonChoice3);

        textObjectPartA.setText("" + partA);
        textObjectPartB.setText("" + partB);

        buttonObjectChoice1.setText("" +correctAnswer);
        buttonObjectChoice2.setText("" + wrongAnswer1);
        buttonObjectChoice3.setText("" + wrongAnswer2);

        buttonObjectChoice1.setOnClickListener((View.OnClickListener) this);
        buttonObjectChoice2.setOnClickListener((View.OnClickListener) this);
        buttonObjectChoice3.setOnClickListener((View.OnClickListener) this);
    }
    public void onClick(View view){

        int answerGiven = 0  ;
        int correctAnswer = 0;

        switch(view.getId()){

            case R.id.buttonChoice1:
            case R.id.buttonChoice2:
                BreakIterator buttonObjectChoice1 = null;
                answerGiven =Integer.parseInt(""+ buttonObjectChoice1.getText());

                if(answerGiven == correctAnswer ){
                    Toast.makeText(getApplicationContext(),"WELL DONE",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Sorry that's wrong",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.buttonChoice3:
                BreakIterator buttonObjectChoice3 = null;
                answerGiven= Integer.parseInt(""+buttonObjectChoice3.getText());
                if(answerGiven==correctAnswer){
                    Toast.makeText(getApplicationContext(),"WELL DONE",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Sorry that's wrong",Toast.LENGTH_LONG).show();
                    break;
                }
        }
    }
    }
