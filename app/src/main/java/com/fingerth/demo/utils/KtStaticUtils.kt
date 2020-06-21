package com.fingerth.demo.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

infix fun String.beginsWith(prefix: String) = startsWith(prefix)
infix fun String.showToast(context: Context) = Toast.makeText(context,this,Toast.LENGTH_SHORT).show()

inline fun <reified T : Activity> ktStartActivity(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)
}

inline fun <reified T : Activity> ktStartActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}