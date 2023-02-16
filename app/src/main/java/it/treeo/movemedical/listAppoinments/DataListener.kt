package it.treeo.movemedical.listAppoinments

import it.treeo.movemedical.database.Data

interface DataListener {
    fun onDeleteClicked(data: Data)
    fun onEditClicked(data:Data)
}