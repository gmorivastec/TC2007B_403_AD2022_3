package mx.itesm.fragmentosrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RecyclerActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "request"
    private lateinit var queue : RequestQueue
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
        val adapter = PerritoAdapter(datos, this)

        // declarar layout manager
        // es el encargado de organizar los elementos en recyclerview
        val llm = LinearLayoutManager(this)
        llm. orientation = LinearLayoutManager.VERTICAL

        val glm = GridLayoutManager(this, 2)

        // asignamos layout manager a GUI
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter


        // PRIMERA COSA QUE CHECAR: JSON
        // JSON - javascript object notation
        // lenguaje de modelación de datos
        var jsonTest = "{'nombre': 'Juan', 'edad': 20}"
        var jsonTest2 = "{'nombre': 'Pedro', 'calificaciones': [80, 80, 70]}"
        var jsonTest3 = "[{'nombre': 'Juan', 'edad': 20}, {'nombre': 'Pedro', 'edad': 19}, {'nombre': 'Maria', 'edad': 21}]"

        // ejemplos de json
        try {

            // parsing - interpretación de un texto a un objeto
            val objeto = JSONObject(jsonTest)
            Log.wtf("JSON", objeto.getString("nombre"))
            Log.wtf("JSON", objeto.getInt("edad").toString())

            val objeto2 = JSONObject(jsonTest2)
            Log.wtf("JSON", objeto2.getString("nombre"))
            val calificaciones = objeto2.getJSONArray("calificaciones")

            for(i in 0 until calificaciones.length()){

                Log.wtf("JSON", calificaciones.getInt(i).toString())
            }

            val objeto3 = JSONArray(jsonTest3)

            for(i in 0 until objeto3.length()){

                val actual = objeto3.getJSONObject(i)
                Log.wtf("JSON", actual.getString("nombre"))
                Log.wtf("JSON", actual.getInt("edad").toString())
            }

        }catch(e : JSONException){
            e.printStackTrace()
        }

        // REQUESTS CON VOLLEY

        // 1ero - vamos a necesitar un objeto queue
        // queue?
        queue = Volley.newRequestQueue(this)

        // tipos de datos
        // - texto plano
        // - binario

        // request para texto plano
        // - algún tipo de JSON
        // - texto regular

        var url = "https://www.google.com"

        var stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                Toast.makeText(
                    this,
                    "response $response",
                    Toast.LENGTH_SHORT
                ).show()
            },
            { error ->
                Toast.makeText(
                    this,
                    "error $error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ).apply {
            tag = TAG
        }

        // existe JsonArrayRequest
        // existe JsonObjectRequest

        url = "https://bitbucket.org/itesmguillermorivas/partial2/raw/992b45809954609ff8521e14f8f70f359c68a3ea/videojuegos.json"
        var jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->

                for(i in 0 until response.length()){

                    val actual = response.getJSONObject(i)
                    Log.wtf("JSON-REQUEST", actual.getString("nombre"))
                    Log.wtf("JSON-REQUEST", actual.getString("anio"))
                    Log.wtf("JSON-REQUEST", actual.getString("imagen"))

                    val plataformas = actual.getJSONArray("plataformas")
                    for(j in 0 until plataformas.length()){

                        Log.wtf("JSON-REQUEST", plataformas.getString(j))
                    }

                }
            },
            { error ->
                Toast.makeText(
                    this,
                    "error: $error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ).apply{
            tag = TAG
        }

        queue.add(jsonArrayRequest)
    }

    // a considerar -
    // los request suceden asíncronos, significa que siguen corriendo
    // no importa el estado de la actividad
    override fun onStop() {
        super.onStop()
        queue.cancelAll(TAG)
    }

    override fun onClick(row: View) {

        val position = recyclerView.getChildLayoutPosition(row)
        Toast.makeText(
            this,
            datos[position],
            Toast.LENGTH_SHORT
        ).show();
    }
}