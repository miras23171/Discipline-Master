package com.example.disciplinemaster

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("DisciplineMaster", Context.MODE_PRIVATE)

    fun saveUserName(name: String) {
        prefs.edit().putString("user_name", name).apply()
    }

    fun getUserName(): String {
        return prefs.getString("user_name", "Guest User") ?: "Guest User"
    }

    fun saveProfileImage(bitmap: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val encoded = Base64.encodeToString(byteArray, Base64.DEFAULT)
        prefs.edit().putString("profile_image", encoded).apply()
    }

    fun getProfileImage(): Bitmap? {
        val encoded = prefs.getString("profile_image", null) ?: return null
        val byteArray = Base64.decode(encoded, Base64.DEFAULT)
        return android.graphics.BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    fun clearProfileData() {
        prefs.edit().clear().apply()
    }
}