package com.example.modelviewproject4

import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var counter=0;
    fun increment(){
        counter++;
    }
}