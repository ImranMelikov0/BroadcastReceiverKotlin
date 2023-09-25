package com.imranmelikov.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast

class Sms:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val a=intent?.extras

        if (a!=null){
            val pdusObj=a.get("pdus") as Array<Any>

            for (i in pdusObj.indices){
                val newMessage:SmsMessage

                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    val format=a.getString("format")
                    newMessage= SmsMessage.createFromPdu(pdusObj[i] as ByteArray,format)
                }else{
                    newMessage= SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
                }

                val telNo=newMessage.displayOriginatingAddress
                val messageBody=newMessage.displayMessageBody

                Toast.makeText(context,"$telNo - $messageBody",Toast.LENGTH_SHORT).show()
            }
        }

    }
}