package br.com.alura.agenda2.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda2.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {


    public AlunoDAO(@Nullable Context context) {
        super(context, "Agenda2", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, telefone TEXT, email TEXT);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Alunos;";
        db.execSQL(sql);
        onCreate(db);

    }


    public void cadastrar(Aluno aluno) {
        SQLiteDatabase writableDatabase = getWritableDatabase();

        ContentValues contentValues = getContentValues(aluno);

        writableDatabase.insert("Alunos", null, contentValues);
    }

    @NonNull
    private ContentValues getContentValues(Aluno aluno) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", aluno.getNome());
        contentValues.put("telefone", aluno.getTelefone());
        contentValues.put("email", aluno.getEmail());
        return contentValues;
    }


    @SuppressLint("Range")
    public List<Aluno> carregarListaAlunos() {
        List<Aluno> alunos = new ArrayList();
        String sql = "SELECT * FROM Alunos";
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            alunos.add(aluno);
        }
        cursor.close();
        return alunos;
    }

    public void excluirAluno(Aluno alunoSelecionado) {
        String[] param = {String.valueOf(alunoSelecionado.getId())};
        getWritableDatabase().delete("Alunos", "id = ?", param);
    }


    public void atualizar(Aluno aluno) {
        ContentValues contentValues = getContentValues(aluno);
        String[] param = {String.valueOf(aluno.getId())};
        getWritableDatabase().update("Alunos", contentValues, "id = ?", param);

    }
}
