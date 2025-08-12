import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'urovo_rfid_platform_interface.dart';

/// An implementation of [UrovoRfidPlatform] that uses method channels.
class MethodChannelUrovoRfid extends UrovoRfidPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('urovo_rfid');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
