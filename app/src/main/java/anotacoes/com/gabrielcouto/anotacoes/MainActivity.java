package anotacoes.com.gabrielcouto.anotacoes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private ImageView btn;
    private static final String ARQUIVO ="Anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.anotacaoId);
        btn = findViewById(R.id.btn_salvarId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = et.getText().toString();
                gravarArquivo(texto);
            }
        });
        
        //Recuperar Texto
        String texto = lerArquivo();
        if(texto!=null){
            et.setText(texto);
        }
        
    }

    private String lerArquivo() {
        String resultado="";
        try{
            InputStream arquivo = openFileInput(ARQUIVO);
            if(arquivo!= null){
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String linha =bufferedReader.readLine();
                while( linha != null){
                    resultado+=linha;
                    linha= bufferedReader.readLine();
                }
                arquivo.close();
            }

        }catch (IOException e){
            Log.v("Main",e.toString());
        }
    return resultado;
    }

    private void gravarArquivo(String texto) {
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(texto);
            outputStreamWriter.close();

        }catch (IOException e){
            Log.v("mainA",e.toString());
        }
    }
}
