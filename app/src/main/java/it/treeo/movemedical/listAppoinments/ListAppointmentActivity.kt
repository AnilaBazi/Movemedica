package it.treeo.movemedical.listAppoinments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.remove
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import it.treeo.movemedical.R
import it.treeo.movemedical.createAppointments.CreateAppointments
import it.treeo.movemedical.database.Data
import it.treeo.movemedical.databinding.ActivityListApointmentBinding

@AndroidEntryPoint
class ListAppointmentActivity : AppCompatActivity() ,DataListener{
    private lateinit var binding: ActivityListApointmentBinding
    private val viewModel by viewModels<DataViewModel>()
    private lateinit var adapterData: DataAdapter
    var listData: List<Data> = ArrayList<Data>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_apointment)

        binding.listData.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
          viewModel.getListData().observe(this,{
            listData= it
              adapterData= this.let { DataAdapter(viewModel._dataList, this,this) }
              binding.listData.adapter = adapterData

          })
         binding.addAppointment.setOnClickListener {
             val intent = Intent(this, CreateAppointments::class.java)

             startActivity(intent)
         }
    }

    override fun onDeleteClicked(data: Data) {
        viewModel.deleteData(data)
        viewModel._dataList.postValue(viewModel._dataList.value!!.toMutableList().apply {
            remove(data)
        }.toList() )

    }

    override fun onEditClicked(data: Data) {
        val intent = Intent(this, CreateAppointments::class.java)
        intent.putExtra("data",data)
        startActivity(intent)
    }

}
