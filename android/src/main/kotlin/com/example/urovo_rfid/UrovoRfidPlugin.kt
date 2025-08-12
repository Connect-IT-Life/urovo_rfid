package com.example.urovo_rfid

import android.os.Handler
import android.os.Looper
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

// The following imports correspond to the Urovo RFID SDK.  They are not
// included with this repository but illustrate the expected integration.
// import com.urovo.sdk.rfid.IRfidCallback
// import com.urovo.sdk.rfid.InitListener
// import com.urovo.sdk.rfid.RFIDSDKManager
// import com.urovo.sdk.rfid.RfidManager
// import com.urovo.sdk.rfid.RfidParameter

/** UrovoRfidPlugin */
class UrovoRfidPlugin : FlutterPlugin, MethodCallHandler, EventChannel.StreamHandler {
  /// Method channel bridging Flutter calls to the native SDK.
  private lateinit var channel: MethodChannel

  /// Event channel used to emit inventory callbacks back to Flutter.
  private lateinit var eventChannel: EventChannel

  private var eventSink: EventChannel.EventSink? = null

  // Reference to the native RFID manager instance.
  private var rfidManager: Any? = null // RfidManager

  private val mainHandler = Handler(Looper.getMainLooper())

  override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(binding.binaryMessenger, "urovo_rfid")
    channel.setMethodCallHandler(this)

    eventChannel = EventChannel(binding.binaryMessenger, "urovo_rfid/events")
    eventChannel.setStreamHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    when (call.method) {
      "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
      "init" -> initRfid(result)
      "startInventory" -> {
        val session = call.argument<Int>("session") ?: 0
        // (rfidManager as? RfidManager)?.startInventory(session)
        result.success(null)
      }
      "stopInventory" -> {
        // (rfidManager as? RfidManager)?.stopInventory()
        result.success(null)
      }
      "readTag" -> {
        // Parameters for readTag
        val epc = call.argument<String>("epc")
        val memBank = call.argument<Int>("memBank") ?: 0
        val wordAdd = call.argument<Int>("wordAdd") ?: 0
        val wordCnt = call.argument<Int>("wordCnt") ?: 0
        val password = call.argument<List<Int>>("password")?.toIntArray()

        // val data = (rfidManager as? RfidManager)?.readTag(epc, memBank.toByte(), wordAdd.toByte(), wordCnt.toByte(), password)
        // Placeholder response map
        val map = hashMapOf<String, Any?>("data" to null)
        result.success(map)
      }
      "writeTag" -> {
        val epc = call.argument<String>("epc")
        val password = call.argument<List<Int>>("password")?.toIntArray()
        val memBank = call.argument<Int>("memBank") ?: 0
        val wordAdd = call.argument<Int>("wordAdd") ?: 0
        val data = call.argument<List<Int>>("data")?.toIntArray()
        // val ret = (rfidManager as? RfidManager)?.writeTag(epc, password, memBank.toByte(), wordAdd.toByte(), data)
        val ret: Int? = 0
        result.success(ret)
      }
      "writeTagEpc" -> {
        val epc = call.argument<String>("epc")
        val password = call.argument<String>("password")
        val data = call.argument<String>("data")
        // val ret = (rfidManager as? RfidManager)?.writeTagEpc(epc, password, data)
        val ret: Int? = 0
        result.success(ret)
      }
      "getOutputPower" -> {
        // val power = (rfidManager as? RfidManager)?.getOutputPower()
        val power: Int? = 0
        result.success(power)
      }
      "setOutputPower" -> {
        val power = call.argument<Int>("power") ?: 0
        // val ret = (rfidManager as? RfidManager)?.setOutputPower(power)
        val ret: Int? = 0
        result.success(ret)
      }
      "setInventoryParameter" -> {
        // val params = RfidParameter()
        // Map incoming arguments to params fields
        result.success(null)
      }
      "enableScanHead" -> {
        val enable = call.argument<Boolean>("enable") ?: false
        // RFIDSDKManager.getInstance().enableScanHead(enable)
        result.success(null)
      }
      else -> result.notImplemented()
    }
  }

  private fun initRfid(result: Result) {
    // RFIDSDKManager.getInstance().init(object : InitListener {
    //   override fun onStatus(status: Boolean) {
    //     if (status) {
    //       rfidManager = RFIDSDKManager.getInstance().getRfidManager()
    //       (rfidManager as? RfidManager)?.setRfidCallback(object : IRfidCallback {
    //         override fun onInventoryTag(epc: String, tid: String, rssi: String) {
    //           mainHandler.post { eventSink?.success(mapOf("epc" to epc, "tid" to tid, "rssi" to rssi)) }
    //         }

    //         override fun onInventoryTagEnd() {
    //           mainHandler.post { eventSink?.endOfStream() }
    //         }
    //       })
    //       result.success(true)
    //     } else {
    //       result.success(false)
    //     }
    //   }
    // })
    result.success(true)
  }

  override fun onListen(arguments: Any?, events: EventChannel.EventSink) {
    eventSink = events
  }

  override fun onCancel(arguments: Any?) {
    eventSink = null
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
    eventChannel.setStreamHandler(null)
  }
}
