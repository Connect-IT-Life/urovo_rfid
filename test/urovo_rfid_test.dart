import 'package:flutter_test/flutter_test.dart';
import 'package:urovo_rfid/urovo_rfid.dart';
import 'package:urovo_rfid/urovo_rfid_platform_interface.dart';
import 'package:urovo_rfid/urovo_rfid_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockUrovoRfidPlatform
    with MockPlatformInterfaceMixin
    implements UrovoRfidPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final UrovoRfidPlatform initialPlatform = UrovoRfidPlatform.instance;

  test('$MethodChannelUrovoRfid is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelUrovoRfid>());
  });

  test('getPlatformVersion', () async {
    UrovoRfid urovoRfidPlugin = UrovoRfid();
    MockUrovoRfidPlatform fakePlatform = MockUrovoRfidPlatform();
    UrovoRfidPlatform.instance = fakePlatform;

    expect(await urovoRfidPlugin.getPlatformVersion(), '42');
  });
}
