package it.treeo.movemedical.createAppointments

import android.content.Context
import android.os.Build
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import it.treeo.movemedical.R
import it.treeo.movemedical.database.AppDataBase
import it.treeo.movemedical.database.Data
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateAppointmentViewModel  @Inject constructor(): ViewModel() {
    var chooseDate = MutableLiveData("Choose Date")
    var description = MutableLiveData("")
    var isUpdate = MutableLiveData(false)

    @Inject
    lateinit var db: AppDataBase

    @RequiresApi(Build.VERSION_CODES.O)
    fun  showCalendar(context: Context, chooseTime:String) {
        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(R.layout.calendar_layout)
        val btnChoose = bottomSheetDialog.findViewById<TextView>(R.id.btnChoose)
        val datePicker = bottomSheetDialog.findViewById<DatePicker>(R.id.datePicker)
        val timePicker = bottomSheetDialog.findViewById<TimePicker>(R.id.timePicker)
        if(chooseTime!="") {
            convertDate(chooseTime, datePicker!!, timePicker!!)
        }

        btnChoose!!.setOnClickListener {
            val date = bottomSheetDialog.findViewById<DatePicker>(R.id.datePicker)
            val time = bottomSheetDialog.findViewById<TimePicker>(R.id.timePicker)
            var month=date!!.month+1

            val dateChoose = addZero(month) + "/" +
                    addZero(date!!.dayOfMonth)+ "/" + date!!.year.toString()

            val timeChoose = addZero(time!!.hour) + ":"+ addZero(time.minute)

            chooseDate.value= "$dateChoose $timeChoose"
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()
    }

    fun saveAppointments(time:String, position: Int, location:String, description:String){
      var data=Data(0,time,position,location,description)
        db.dataDao().insert(data)
    }
     fun updateAppointments(id:Int,time:String,position:Int, location:String, description:String){
      var data=Data(id,time,position,location,description)
        db.dataDao().updateData(data)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDate(date:String, datePicker:DatePicker,timePicker:TimePicker) {
        val firstApiFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")
        val localDateTime = LocalDateTime.parse(date , firstApiFormat)
        var month=0
        if(localDateTime.monthValue>0){
           month=localDateTime.monthValue.toInt() -1
        }

        datePicker!!.init(localDateTime.year, month, localDateTime.dayOfMonth, null);
        timePicker.currentHour = localDateTime.hour
        timePicker.currentMinute = localDateTime.minute
    }
    fun addZero(value:Int):String{
        var returnValue=value.toString()
        if(value<10){
            returnValue= "0"+returnValue
        }
        return  returnValue
    }

}