package com.project1forcreativeitinstitute.orrio

import android.widget.EditText

fun EditText.isEmpty(): Boolean{
    return if (this.text.toString().isEmpty()){
        this.error = "This place Need to be fill up"
        true
    }else{
        false
    }
}