package br.com.ufsc.aplicacaoteste;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import data.CustomRequest;


public class SendNewsActivity extends AppCompatActivity {

    TextView editTextNewsTitle;
    TextView editTextNewsBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_news);
        editTextNewsTitle = (TextView) findViewById(R.id.editTextNewsTitle);
        editTextNewsBody = (TextView) findViewById(R.id.editTextNewsBody);
        Button buttonSend = (Button) findViewById(R.id.buttonSend);
        initVolley(this);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doPost();
            }
        });
    }

    RequestQueue requestQueue;
    private ProgressDialog dialog;
    private Context context;
/*
* Volley é uma biblioteca HTTP para transmissao de informação
* Criamos a fila de requisição para enfileirar nossas requisições HTTP
*/
    public void initVolley(Context applicationContext) {
        requestQueue = Volley.newRequestQueue(applicationContext);
    }

/*
Criamos o metodo doPost para realizar um POST no servidor no endereço
 */
    public void doPost() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Cadastrando noticia");
        dialog.setCancelable(false);
        dialog.show();

/*
Valores a serem passados para o corpo da requisição
 */
        Map<String, String> params = new HashMap<String, String>();
        params.put("titulo", editTextNewsTitle.getText().toString());
        params.put("mensagem", editTextNewsBody.getText().toString());

        Response.Listener<JSONObject> reponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("VOLLEY", response.toString());
                if ((dialog != null) && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if ((dialog != null) && dialog.isShowing()) {
                    dialog.dismiss();
                }
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        };

        CustomRequest jsObjRequest = new CustomRequest(
                Request.Method.POST,
                "http://oficina.demo.doutbox.com.br/sendNoticias",
                params,
                reponseListener,
                errorListener
        );
        requestQueue.add(jsObjRequest);
    }
}
