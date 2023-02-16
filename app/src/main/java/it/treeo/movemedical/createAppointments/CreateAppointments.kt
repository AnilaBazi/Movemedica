package it.treeo.movemedical.createAppointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import it.treeo.movemedical.R
import it.treeo.movemedical.databinding.ActivityCreateAppointmentsBinding
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import it.treeo.movemedical.database.Data
import it.treeo.movemedical.listAppoinments.ListAppointmentActivity


@AndroidEntryPoint
class CreateAppointments : AppCompatActivity() {
    private val createViewModel by viewModels<CreateAppointmentViewModel>()

    private lateinit var binding: ActivityCreateAppointmentsBinding
    var location = arrayOf("San Diego", "St. George", "Park City", "Dallas", "Memphis","Orlando")
    lateinit var data: Data

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_appointments)

        binding.apply {
            createViewModel = this@CreateAppointments.createViewModel
            lifecycleOwner = this@CreateAppointments
        }
        var aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, location)
        binding.locationList.adapter = aa

        if(intent.extras !=null) {
            data = intent.extras!!.get("data") as Data
            createViewModel.isUpdate.value=true
            createViewModel.description.value = data.description.toString()
            createViewModel.chooseDate.value = data.time_value
            binding.locationList.setSelection(data.position!!.toInt())
            binding.btnCreate.text="Update"
        }
        else{
            data=Data(0,"",0,"","")
        }


        binding.icCalendar.setOnClickListener {

          createViewModel.showCalendar(this,data.time_value.toString())
        }

        binding.btnCreate.setOnClickListener {
            if(createViewModel.isUpdate.value==true){
                createViewModel.updateAppointments(data.id,createViewModel.chooseDate.value.toString(),
                                                    binding.locationList.selectedItemId.toInt(),
                                                    binding.locationList.selectedItem.toString(),
                                                    binding.etDescription.text.toString())
                val intent = Intent(this, ListAppointmentActivity::class.java)
                startActivity(intent)
            }
            else{
                createViewModel.saveAppointments(createViewModel.chooseDate.value.toString(),
                                                    binding.locationList.selectedItemId.toInt(),
                                                    binding.locationList.selectedItem.toString(),
                                                    binding.etDescription.text.toString())
                val intent = Intent(this, ListAppointmentActivity::class.java)
                startActivity(intent)
            }

        }
    }
}