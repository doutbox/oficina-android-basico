package br.com.ufsc.aplicacaoteste;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import data.HttpClient;
import data.JSONNoticiasParser;

public class SecondActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private Context context;
    // Context é a maneira fornecida pelo sistema para
    // seu aplicativo acessar determinados recursos (como o recurso de iniciar uma activity)

    ListView listViewNews;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

/* ListView
 * Explicação: A ListView é um grupo de exibições que exibe uma lista de itens roláveis.
 * Os itens da lista são inseridos automaticamente na lista usando um Adapter que obtém
 * conteúdo de uma origem como uma matriz ou consulta de banco de dados e converte cada
 * resultado de item em uma exibição, que é colocada na lista.
 *
 * */
        listViewNews = (ListView) findViewById(R.id.listViewNews);


        SecondActivity.getNoticiasTask getNoticiasTask = new SecondActivity.getNoticiasTask();
        getNoticiasTask.execute();

        Button buttonSendNews = (Button) findViewById(R.id.buttonSendNews);
        buttonSendNews.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(SecondActivity.this, SendNewsActivity.class);
                startActivity(i);
            }
        });

    }

/* AsyncTask
* Explicacao: O AsyncTask é projetado para ser uma classe auxiliar, funcionamento semelhante ao
* de uma thread. Os AsyncTasks devem idealmente ser usados ​​para operações curtas
* (alguns segundos no máximo.)
* Quatro metodos podem ser reescritos onPreExecute, doInBackground, onPostExecute, onProgressUpdate
*/
    private class getNoticiasTask extends AsyncTask<Void, Void, Void> {
        String dataFromHttp;
        HttpClient httpClient = new HttpClient();
        ArrayList<String> noticiasArrayList;

        public getNoticiasTask() {
            context = SecondActivity.this;
            dialog = new ProgressDialog(context);
        }

        protected void onPreExecute() {
            dialog.setMessage("Atualizando informações");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
/*
*Utilizando nosso cliente, que na verdade é uma instancia do OkHttp
*Fazemos uma requisição GET para o endereço que informar aqui como parametro
*Este metodo vai retornar uma String, essa String é o texto puro que veio do Web Service
*/
                dataFromHttp = httpClient.getData("http://oficina.demo.doutbox.com.br/getNoticias");
/*Criamos um ArrayList das Noticias que vao vir em formato do texto, mas so como string
*fica dificil de trabalhar com algo dentro de um aplicativo, precisamos transformar
*este conteudo em um objeto, para isso vamos criar um Parser de JSON para ArrayList,
*que possui metodos herdados de outra classe Collection
*/
                noticiasArrayList = new JSONNoticiasParser().createArray(dataFromHttp);
/*
*Para que nossa ListView receba dados, ela precisa de um adapter, o que é um adapter
*Ele é responsavel por fazer a fonte entre os dados e nosso visualizador,
*Ele também é responsavel por criar um meio de visualizar cada objeto da lista
*No nosso caso o Adapter vai adaptar tudo que temos no arraylist para o modelo
*Simple list item 1
*/
                arrayAdapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1, noticiasArrayList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
/*
* Após criar instanciar o adapter, temos que associa-lo a nossa ListView para que as
* informações estejam disponiveis
*/
            listViewNews.setAdapter(arrayAdapter);
            if ((dialog != null) && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
