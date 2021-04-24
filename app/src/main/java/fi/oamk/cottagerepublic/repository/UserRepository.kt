package fi.oamk.cottagerepublic.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class UserRepository(private val databaseReference: DatabaseReference) {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null
        fun getInstance(databaseReference: DatabaseReference): UserRepository {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = UserRepository(databaseReference)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private val auth = FirebaseAuth.getInstance()
    private lateinit var userProfile: DataSnapshot
    private var userid = auth.currentUser



    fun saveEmailOnRegister(email: String) {
        if (auth.currentUser != null)
            databaseReference.child(userid!!.uid).setValue(email)
    }

    fun getCurrentUserData(email: MutableLiveData<String>, fname: MutableLiveData<String>, lname: MutableLiveData<String>, phone: MutableLiveData<String>): Boolean {
        if (auth.currentUser != null) {
            databaseReference.child(getCurrentUserId()).get().addOnSuccessListener {
                if (it.child("email").value != null) email.postValue(it.child("email").value.toString())
                if (it.child("phone").value != null) phone.postValue(it.child("phone").value.toString())
                if (it.child("fname").value != null) fname.postValue(it.child("fname").value.toString())
                if (it.child("lname").value != null) lname.postValue(it.child("lname").value.toString())
                userProfile = it
                Log.i("data check", userProfile.toString())
            }.addOnFailureListener {
                Log.i("failure", "there is some problem getting the data")
            }
            return true
        } else {
            return false
        }
    }

    fun updateUserData(fname: String, lname: String, phone: String): Boolean {
        //if not dont have logged in user will return false
        if (userid != null) {
            if (fname != userProfile.child("fname").value.toString()) {
                databaseReference.child(userid!!.uid).child("fname").setValue(fname)
            }
            if (lname != userProfile.child("lname").value.toString()) {
                databaseReference.child(userid!!.uid).child("lname").setValue(lname)
            }
            if (phone != userProfile.child("phone").value.toString()) {
                databaseReference.child(userid!!.uid).child("phone").setValue(phone)
            }
            Log.i("data update", "data saved")
            // if there is a logged in user to save data
            return true
        } else {
            Log.i("data update", "data save failed")
            return false
        }
    }

    fun getUserData(): DataSnapshot {
        return userProfile
    }

    fun getUserReservations(): Task<DataSnapshot> {
        val testid = "6EAP6t8B8wROG6OYsPwzaHBntjE2"
        val data = databaseReference.child("users").child(testid).child("reservations").get()
        return data
    }

    fun getCurrentUserId(): String {
        return getCurrentUser().value!!.uid
    }

    // Using MutableLiveData to notify AccountScreenFragment when current user has changed
    fun getCurrentUser(): MutableLiveData<FirebaseUser> {
        return MutableLiveData(firebaseAuth.currentUser)
    }
}

