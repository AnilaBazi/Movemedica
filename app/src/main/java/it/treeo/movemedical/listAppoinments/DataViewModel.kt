package it.treeo.movemedical.listAppoinments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.treeo.movemedical.database.AppDataBase
import it.treeo.movemedical.database.Data
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel  @Inject constructor(): ViewModel() {

    @Inject
    lateinit var db: AppDataBase
    val _dataList = MutableLiveData<List<Data>>()

    fun getListData():MutableLiveData<List<Data>>{

     _dataList.value= db.dataDao().getAll()

        return _dataList
    }
    fun deleteData(data:Data){
        db.dataDao().deleteById(data.id)
    }
}