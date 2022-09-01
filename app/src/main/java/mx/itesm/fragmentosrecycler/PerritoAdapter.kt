package mx.itesm.fragmentosrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PerritoAdapter(var perritos : ArrayList<String>) :
    RecyclerView.Adapter<PerritoAdapter.PerritoViewHolder>(){

    // definamos una clase interna que va a servir
    // como view holder
    // (piensen en este como algo similar al binding)

    class PerritoViewHolder(itemView : View) :
            RecyclerView.ViewHolder(itemView){

        var texto1 : TextView
        var texto2 : TextView

        // bloque de inicializacion
        // init block

        init {

            texto1 = itemView.findViewById(R.id.rowText1)
            texto2 = itemView.findViewById(R.id.rowText2)
        }
    }

    // momento en que creamos la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerritoViewHolder {

        // igualito a la cuestion del fragment
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)


        return PerritoViewHolder(view)
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