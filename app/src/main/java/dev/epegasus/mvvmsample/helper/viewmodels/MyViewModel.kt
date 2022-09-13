package dev.epegasus.mvvmsample.helper.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "Magic"

class MyViewModel : ViewModel() {

    // Variables
    private var a = 10
    val value get() = a

    // Live Data
    private var numberMutableLiveData = MutableLiveData<String>()
    val numberLiveData: LiveData<String> get() = numberMutableLiveData

    fun updateValue() {
        a += 30
    }

    fun createLoop() {
        var n = 0
        for (i in 0..500000000) {
            n = (n + i)
        }
        // Main Thread
        numberMutableLiveData.value = n.toString()

        // Background Thread
        numberMutableLiveData.postValue(n.toString())
    }

    fun createSafeLoop() {
        CoroutineScope(Dispatchers.Main).launch {
            var n = 0
            CoroutineScope(Dispatchers.Default).launch {
                for (i in 0..500000000) {
                    n = (n + i)
                }
            }.join()
            // Main Thread
            numberMutableLiveData.value = n.toString()
        }
    }
}