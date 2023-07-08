package br.com.alura.agenda2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.alura.agenda2.R;
import br.com.alura.agenda2.dao.AlunoDAO;
import br.com.alura.agenda2.modelo.Aluno;
import br.com.alura.agenda2.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaDeAlunos;
    private FloatingActionButton btNovoAluno;
    private AlunoDAO dao;
    private ListaAlunosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle("Lista de Alunos");

        inicializarComponentes();
        carregarListaDeAlunos();


        btNovoAluno.setOnClickListener(view -> {
            Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
            startActivity(intent);
        });

        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Aluno alunoSelecionado = (Aluno) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
                intent.putExtra("alunoSelecionado", alunoSelecionado);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaDeAlunos); //chama o metodo interno para aparecer o menu de contexto ao clique longo em cima do nome na lista
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarListaDeAlunos();

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.menu_contexto_lista_alunos, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_contexto_excluir:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Aluno alunoSelecionado = (Aluno) this.adapter.getItem(menuInfo.position);
                dao.excluirAluno(alunoSelecionado);
                dao.close();
                Toast.makeText(ListaAlunosActivity.this, "Aluno(a) " + alunoSelecionado + " excluído com sucesso!", Toast.LENGTH_SHORT).show();
                carregarListaDeAlunos();
                return false;
        }
        return super.onContextItemSelected(item);

    }


    private void carregarListaDeAlunos() {
        List<Aluno> alunos = dao.carregarListaAlunos();
        adapter = new ListaAlunosAdapter(this, alunos);
        listaDeAlunos.setAdapter(adapter);
    }

    private void inicializarComponentes() {
        listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        btNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        dao = new AlunoDAO(this);
    }
}
