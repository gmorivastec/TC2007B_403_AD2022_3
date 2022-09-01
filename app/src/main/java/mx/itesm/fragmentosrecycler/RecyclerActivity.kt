package mx.itesm.fragmentosrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var datos : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        // recycler view es un widget utilizado
        // para mostrar muchos elementos idénticos con datos distintos

        // DATOS (abstracto) - Adapter (traductor de datos a GUI) - GUI (concreto)

        // DATOS
        datos = ArrayList()

        datos.add("Fifi")
        datos.add("Fido")
        datos.add("Firulais")

        // GUI
        recyclerView = findViewById(R.id.recyclerView)

        // vinculando DATOS con GUI

        // creamos adaptador
        val adapter = PerritoAdapter(datos)

        // declarar layout manager
        // es el encargado de organizar los elementos en recyclerview
        val llm = LinearLayoutManager(this)
        llm. orientation = LinearLayoutManager.VERTICAL

        val glm = GridLayoutManager(this, 2)

        // asignamos layout manager a GUI
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter

    }
}