/**
 * Titanium SDK
 * Copyright TiDev, Inc. 04/07/2022-Present
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

/** This is generated, do not edit by hand. **/

#include "Proxy.h"

namespace net {
namespace devloops {
namespace agorartc {

class AgoraRTCModule : public titanium::Proxy
{
public:
	explicit AgoraRTCModule();

	static void bindProxy(v8::Local<v8::Object>, v8::Local<v8::Context>);
	static v8::Local<v8::FunctionTemplate> getProxyTemplate(v8::Isolate*);
	static v8::Local<v8::FunctionTemplate> getProxyTemplate(v8::Local<v8::Context>);
	static void dispose(v8::Isolate*);

	static jclass javaClass;

private:
	static v8::Persistent<v8::FunctionTemplate> proxyTemplate;
	static v8::Persistent<v8::Object> moduleInstance;

	// Methods -----------------------------------------------------------
	static void switchCamera(const v8::FunctionCallbackInfo<v8::Value>&);
	static void adjustPlaybackSignalVolume(const v8::FunctionCallbackInfo<v8::Value>&);
	static void renewToken(const v8::FunctionCallbackInfo<v8::Value>&);
	static void startPreview(const v8::FunctionCallbackInfo<v8::Value>&);
	static void enableSpeakerphone(const v8::FunctionCallbackInfo<v8::Value>&);
	static void requestPermissions(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setAudioProfile(const v8::FunctionCallbackInfo<v8::Value>&);
	static void muteAllRemoteAudioStreams(const v8::FunctionCallbackInfo<v8::Value>&);
	static void adjustRecordingSignalVolume(const v8::FunctionCallbackInfo<v8::Value>&);
	static void disableVideo(const v8::FunctionCallbackInfo<v8::Value>&);
	static void muteAllRemoteVideoStreams(const v8::FunctionCallbackInfo<v8::Value>&);
	static void stopPreview(const v8::FunctionCallbackInfo<v8::Value>&);
	static void muteRemoteAudioStream(const v8::FunctionCallbackInfo<v8::Value>&);
	static void disableAudio(const v8::FunctionCallbackInfo<v8::Value>&);
	static void joinChannel(const v8::FunctionCallbackInfo<v8::Value>&);
	static void enableInEarMonitoring(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setChannelProfile(const v8::FunctionCallbackInfo<v8::Value>&);
	static void enableDualStreamMode(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setVideoEncoderConfiguration(const v8::FunctionCallbackInfo<v8::Value>&);
	static void destroy(const v8::FunctionCallbackInfo<v8::Value>&);
	static void enableVideo(const v8::FunctionCallbackInfo<v8::Value>&);
	static void leaveChannel(const v8::FunctionCallbackInfo<v8::Value>&);
	static void muteLocalVideoStream(const v8::FunctionCallbackInfo<v8::Value>&);
	static void enableAudioVolumeIndication(const v8::FunctionCallbackInfo<v8::Value>&);
	static void muteRemoteVideoStream(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setRemoteVideoStreamType(const v8::FunctionCallbackInfo<v8::Value>&);
	static void setClientRole(const v8::FunctionCallbackInfo<v8::Value>&);
	static void initialize(const v8::FunctionCallbackInfo<v8::Value>&);
	static void enableAudio(const v8::FunctionCallbackInfo<v8::Value>&);
	static void muteLocalAudioStream(const v8::FunctionCallbackInfo<v8::Value>&);

	// Dynamic property accessors ----------------------------------------

};

} // agorartc
} // devloops
} // net
