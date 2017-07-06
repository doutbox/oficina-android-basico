package br.com.ufsc.aplicacaoteste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

/*  Trabalhando com TextView (Visualizadores de texto)
* Explicação: Aqui podemos trabalhar com visualizadores de textos
* são muito utilizados para fazer o design do aplicativo e mostrar informações
* Para encontrar um item usando a classe findViewById, procura dentro da classe R pelo id passado
* Podemos pegar o conteudo de um visualizados de texto, definir propriedades dele,
* tamanho da letra, configurações estetiscas
* Também podemos definir o conteúdo dele para determinada String que a gente necessite
* Quando o aplicativo é compilado, aapt gera a classe R, que contém códigos de recursos para
* todos os recursos no diretório res/. Para cada tipo de recurso, há uma subclasse R (por
* exemplo, R.drawable para todos os recursos drawable) e, para cada recurso daquele tipo,
* há um número inteiro estático (por exemplo, R.drawable.icon). Esse número inteiro é o
* ID do recurso que pode ser usado para recuperá-lo.
*/
        TextView textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        final TextView textViewReceiver = (TextView) findViewById(R.id.textViewReceiver);
        String title;
        title = (String) textViewTitle.getText();
        textViewReceiver.setText(title);
        textViewReceiver.setTextSize(20);

/* Trabalhando com  EditText (Campos de texto)
* Explicação: Podemos trabalhar também com editores de texto, neste caso para não ficar
* um pouco sem graça vamos trabalhar juntos com botão, então encontramos o botão do mesmo
* modo que encontramos os visualizados de texto
*/
        final EditText editTextName = (EditText) findViewById(R.id.editTextName);


/* Trabalhando com Button
* Explicação: Encontramos o item pelo ID do mesmo modo como com os outros itens, só que o
* botão tem ser definido um ouvinte (Listener) - Estrutura que ouve eventos que são
* registrados a ele (Eventos podem ser focus, click.
*
* As variaveis aqui devem ser finais porque estão dentro de uma classe interna,
* isso quer dizer que a classe interna que é instanciada ainda depende das referencias
* da classe externa, por isso deve ser final, um atributo final de uma classe pode ter seu
* valor atribuído uma única vez, seja na própria declaração ou no construtor.
*/
        Button buttonOkInitial = (Button) findViewById(R.id.buttonOkInitial);
        buttonOkInitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewReceiver.setText(editTextName.getText());
            }
        });


/* Trabalhando com trocas de tela
* Explicação: Novamente vamos utilizar um botão e um listener pra fazer a troca de tela
* Quando o botão é clicado deverá ir para outra tela, como faremos isso
* Utilizando o Intent
*/
        Button buttonNextActivity = (Button) findViewById(R.id.buttonNextActivity);
        buttonNextActivity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(InitialActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }
}
