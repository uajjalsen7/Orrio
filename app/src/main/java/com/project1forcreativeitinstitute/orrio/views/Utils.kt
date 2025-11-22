package com.project1forcreativeitinstitute.orrio.views

import android.widget.EditText

fun EditText.isEmpty(): Boolean{
    return if (this.text.toString().isEmpty()){
        this.error = "This place Need to be fill up"
        true
    }else{
        false
    }
}