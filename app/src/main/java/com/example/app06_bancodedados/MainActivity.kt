package com.example.app06_bancodedados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.sqlite.SQLiteDatabase
import android.util.Log


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val bd = openOrCreateDatabase("app", MODE_PRIVATE, null)
            //Criando tabela
            bd.execSQL("CREATE TABLE IF NOT EXISTS produto(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, quantidade INT, preco DECIMAL(10,2), categoria VARCHAR)")
            //Inserindo dados
            bd.execSQL("INSERT INTO produto (nome, quantidade, preco, categoria) VALUES " +
                    "('Arroz', 18, 16.10, 'Alimento')," +
                    "('Feijão', 25, 7.20, 'Alimento')," +
                    "('Shampoo', 10, 9.50, 'Cosmeticos')," +
                    "('Condicionador', 10, 11.50, 'Cosmeticos')," +
                    "('Chinelo', 10, 21.50, 'Calçado')," +
                    "('Vestido', 5, 90.10, 'Vestiario')," +
                    "('Sabonete', 200, 3.50, 'Cosmeticos')," +
                    "('Boneca', 7, 63.50, 'Brinquedos')," +
                    "('Bola', 14, 15.90, 'Brinquedos')," +
                    "('Mouse', 30, 50.90, 'Perifericos')," +
                    "('Teclado', 30, 153.90, 'Perifericos')")
            //recuperar dados

            //bd.execSQL("DROP TABLE produto ")
            val cursor = bd.rawQuery("SELECT * FROM produto", null)

            val indiceId = cursor!!.getColumnIndex("id")
            val indiceNome = cursor!!.getColumnIndex("nome")
            val indiceQuant = cursor!!.getColumnIndex("quantidade")
            val indicePreco = cursor!!.getColumnIndex("preco")
            val indiceCategoria = cursor!!.getColumnIndex("categoria")
            cursor.moveToFirst()

            while(cursor != null) {
               Log.i("Resultado - Id: ", cursor.getString(indiceId) + " Produto: " + cursor.getString(indiceNome) +
                        " Quantidade: " + cursor.getString(indiceQuant) + " Preço: " + cursor.getString(indicePreco) + " Categoria: " + cursor.getString(indiceCategoria))
            cursor.moveToNext()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}