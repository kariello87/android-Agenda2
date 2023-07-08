package br.com.alura.agenda2.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.alura.agenda2.R;
import br.com.alura.agenda2.modelo.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Context context;

    public ListaAlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Aluno alunoDevolvido = alunos.get(posicao);
        vincular(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vincular(View viewCriada, Aluno alunoDevolvido) {
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        nome.setText(alunoDevolvido.getNome());
        telefone.setText(alunoDevolvido.getTelefone());
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(this.context)
                .inflate(R.layout.item_aluno, viewGroup, false);
    }
}
