import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'urovo_rfid_method_channel.dart';

abstract class UrovoRfidPlatform extends PlatformInterface {
  /// Constructs a UrovoRfidPlatform.
  UrovoRfidPlatform() : super(token: _token);

  static final Object _token = Object();

  static UrovoRfidPlatform _instance = MethodChannelUrovoRfid();

  /// The default instance of [UrovoRfidPlatform] to use.
  ///
  /// Defaults to [MethodChannelUrovoRfid].
  static UrovoRfidPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [UrovoRfidPlatform] when
  /// they register themselves.
  static set instance(UrovoRfidPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
