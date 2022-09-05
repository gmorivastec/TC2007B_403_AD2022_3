package mx.itesm.fragmentosrecycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PerritoAdapter(
    var perritos : ArrayList<String>,
    var listener : View.OnClickListener
    ) :
    RecyclerView.Adapter<PerritoAdapter.PerritoViewHolder>(){

    // definamos una clase interna que va a servir
    // como view holder
    // (piensen en este como algo similar al binding)

    class PerritoViewHolder(itemView : View) :
            RecyclerView.ViewHolder(itemView){

        var texto1 : TextView
        var texto2 : TextView
        var boton : Button

        // bloque de inicializacion
        // init block

        init {

            texto1 = itemView.findViewById(R.id.rowText1)
            texto2 = itemView.findViewById(R.id.rowText2)
            boton = itemView.findViewById(R.id.rowButton)
        }
    }

    // momento en que creamos la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerritoViewHolder {

        // igualito a la cuestion del fragment
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)

        view.setOnClickListener(listener)

        val viewHolder = PerritoViewHolder(view)

        viewHolder.boton.setOnClickListener {

            Log.wtf("BOTON INTERNO", "BOTON PRESIONADO")
        }

        return viewHolder
    }

    // asociamos una vista en particular con un elemento de nuestra
    // fuente de datos
    override fun onBindViewHolder(holder: PerritoViewHolder, position: Int) {
        holder.texto1.text = perritos[position]
        holder.texto2.text = perritos[position]
    }

    // obtener total de elementos
    override fun getItemCount(): Int {
        return perritos.size
    }
}