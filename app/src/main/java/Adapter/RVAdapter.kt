package Adapter

import Database.GlobalVar
import Interface.CardListener
import Model.Model
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.week1_0706012110048.MainActivity
import com.example.week1_0706012110048.InputActivity
import com.example.week1_0706012110048.R
import com.example.week1_0706012110048.databinding.CardLayoutBinding

class RVAdapter(val listData:ArrayList<Model>, val cardListener: CardListener):
    RecyclerView.Adapter<RVAdapter.viewHolder>() {

    class viewHolder(itemView: View, val cardlistener1: CardListener):RecyclerView.ViewHolder(itemView){
        val binding = CardLayoutBinding.bind(itemView)

        fun setdata(data:Model){
            binding.nameText.text=data.name
            binding.typeText.text=data.type
            binding.ageText.text=data.age
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_layout, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setdata(listData[position])
        holder.binding.deleteButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(it.context)
            alertDialog.apply {
                setTitle("Konfirmasi")
                setMessage("Apakah anda yakin untuk mendelete hewan ini?")
                setNegativeButton("No", { dialogInterface, i -> dialogInterface.dismiss() })
                setPositiveButton("Yes", { dialogInterface, i -> dialogInterface.dismiss()
                    GlobalVar.listData.removeAt(position)
                    notifyDataSetChanged()
                    Toast.makeText(it.context, "Hewan Berhasil Di Hapus", Toast.LENGTH_SHORT).show()
                })
                alertDialog.show()
            }
        }
        holder.binding.editButton.setOnClickListener {
            val intent = Intent(it.context, InputActivity::class.java).putExtra("position",position)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

}