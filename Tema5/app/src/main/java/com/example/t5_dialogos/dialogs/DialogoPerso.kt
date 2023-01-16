package com.example.t5_dialogos.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.t5_dialogos.R

class DialogoPerso: DialogFragment() {

    lateinit var vista: View;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vista = LayoutInflater.from(context).inflate(R.layout.dialogo_login,null)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext());

       builder.setView(vista)

        return builder.create()
    }
}