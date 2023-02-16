package it.treeo.movemedical.listAppoinments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import it.treeo.movemedical.database.Data
import it.treeo.movemedical.databinding.RowDataBinding


class DataAdapter(
    var mClassListData: MutableLiveData<List<Data>>,
    var context: Context,
    private val dataListener: DataListener

) :
    RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: RowDataBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Data){
            binding.data=item
            binding.listener = dataListener
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val listItemBinding= RowDataBinding.inflate(inflater,parent,false)
        return MyViewHolder(listItemBinding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var arrayList=mClassListData.value
        holder.bind(arrayList!![position])
    }

    override fun getItemCount(): Int {
        return  mClassListData.value!!.size
    }


}


