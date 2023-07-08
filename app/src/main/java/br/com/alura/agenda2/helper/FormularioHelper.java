package br.com.alura.agenda2.helper;

import android.widget.EditText;

import br.com.alura.agenda2.R;
import br.com.alura.agenda2.modelo.Aluno;
import br.com.alura.agenda2.ui.activity.FormularioAlunoActivity;

public class FormularioHelper {

    private EditText editNome, editTelefone, editEmail;
    private Aluno aluno;

    public FormularioHelper(FormularioAlunoActivity activity) {
        editNome = activity.findViewById(R.id.activity_formulario_aluno_nome);
        editTelefone = activity.findViewById(R.id.activity_formulario_aluno_telefone);
        editEmail = activity.findViewById(R.id.activity_formulario_aluno_email);
        this.aluno = new Aluno();

    }

    public Aluno capturaDadosFormulario() {
        aluno.setNome(editNome.getText().toString());
        aluno.setTelefone(editTelefone.getText().toString());
        aluno.setEmail(editEmail.getText().toString());
        return aluno;

    }

    public void preencheCampos(Aluno alunoSelecionado) {
        editNome.setText(alunoSelecionado.getNome());
        editTelefone.setText(alunoSelecionado.getTelefone());
        editEmail.setText(alunoSelecionado.getEmail());
        this.aluno = alunoSelecionado;
    }
}
