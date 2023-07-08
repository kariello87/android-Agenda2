package br.com.alura.agenda2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda2.R;
import br.com.alura.agenda2.dao.AlunoDAO;
import br.com.alura.agenda2.helper.FormularioHelper;
import br.com.alura.agenda2.modelo.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private Button btSalvar, btCancelar;
    private AlunoDAO dao;
    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle("Novo Aluno");
        inicializarComponentes();
        Intent dados = getIntent();
        if (dados.hasExtra("alunoSelecionado")) {
            Aluno alunoSelecionado = (Aluno) dados.getSerializableExtra("alunoSelecionado");
            helper.preencheCampos(alunoSelecionado);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_cadastro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_botao_salvar:
                Aluno aluno = helper.capturaDadosFormulario();
                if (aluno.getId() != null) {
                    dao.atualizar(aluno);
                    Toast.makeText(this, "Dados do aluno(a) " + aluno.getNome() + " atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    dao.cadastrar(aluno);
                    Toast.makeText(this, "Aluno(a) " + aluno.getNome() + " cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(FormularioAlunoActivity.this, ListaAlunosActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.menu_formulario_botao_cancelar:
                Toast.makeText(this, "Operaçao cancelada", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void inicializarComponentes() {
        btSalvar = findViewById(R.id.menu_formulario_botao_salvar);
        btCancelar = findViewById(R.id.menu_formulario_botao_cancelar);
        helper = new FormularioHelper(this);
        dao = new AlunoDAO(this);
    }

}