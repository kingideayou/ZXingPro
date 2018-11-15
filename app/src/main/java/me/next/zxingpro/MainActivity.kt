package me.next.zxingpro

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.client.result.ResultParser
import me.next.zxing_android_embedded_ex.decoder.BitmapDecoder
import me.next.zxing_android_embedded_ex.utils.BitmapUtils


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = AlertDialog.Builder(this)
        findViewById<View>(R.id.iv_erweima).setOnLongClickListener {
            builder.setItems(arrayOf("识别二维码")) { _, which ->
                if (which == 0) {
                    Thread(Runnable {
                        val img = BitmapUtils.decodeSampledBitmapFromResource(
                            this@MainActivity.resources, R.drawable.erweima, 500, 500
                        )
                        val decoder = BitmapDecoder(this@MainActivity)
                        val result = decoder.getRawResult(img)
                        runOnUiThread(Runnable {
                            if (result != null) {
                                val resultStr = ResultParser.parseResult(result).toString()
                                findViewById<TextView>(R.id.tv_code).text = "二维码是：$resultStr"
                                return@Runnable
                            }
                            Toast.makeText(this@MainActivity, "识别异常", Toast.LENGTH_SHORT).show()
                        })
                    }).start()
                }
            }.create().show()
            true
        }

    }
}
