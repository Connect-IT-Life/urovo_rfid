
import 'urovo_rfid_platform_interface.dart';

class UrovoRfid {
  Future<String?> getPlatformVersion() {
    return UrovoRfidPlatform.instance.getPlatformVersion();
  }
}
