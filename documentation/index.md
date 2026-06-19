# AgoraRTC Module

## Description

Bridges the [Agora full RTC SDK](https://docs.agora.io/en/voice-calling/get-started/get-started-sdk)
(audio + video) to Titanium for iOS and Android. One JavaScript API drives both platforms.

- Android: `io.agora.rtc:full-sdk:4.6.3`
- iOS: `AgoraRtcEngine_iOS` 4.6.3

## Accessing the AgoraRTC Module

```js
import agora from 'net.devloops.AgoraRTC';   // ES6+
// var agora = require('net.devloops.AgoraRTC'); // ES5
```

## Permissions

- **Android** — the module bundles the required `<uses-permission>` entries. `RECORD_AUDIO` and
  `CAMERA` are runtime permissions; request them from JS (`Ti.Android.requestPermissions`) before joining.
- **iOS** — add `NSMicrophoneUsageDescription` and `NSCameraUsageDescription` to your app's `tiapp.xml`.

## Reference

### Lifecycle

#### agora.initialize({ appId })
Creates the underlying RTC engine. Call once before anything else.

#### agora.destroy()
Leaves the channel, stops preview, and releases the engine.

#### agora.requestPermissions()
Android convenience that fires a native mic+camera request (no result callback — prefer
`Ti.Android.requestPermissions`). No-op on iOS.

### Channel

#### agora.joinChannel(options)
`options`: `token` (String or null for testing-mode projects), `channelId` (String),
`uid` (Number, 0 = auto-assign), `clientRole`, `channelProfile`, `publishMicrophone` (Boolean),
`publishCamera` (Boolean), `autoSubscribeAudio` (Boolean), `autoSubscribeVideo` (Boolean).

#### agora.leaveChannel()
#### agora.renewToken(token)
Call in response to the `tokenPrivilegeWillExpire` event.
#### agora.setClientRole(role)
`CLIENT_ROLE_BROADCASTER` or `CLIENT_ROLE_AUDIENCE`.
#### agora.setChannelProfile(profile)
`CHANNEL_PROFILE_COMMUNICATION` or `CHANNEL_PROFILE_LIVE_BROADCASTING`.

### Audio

`enableAudio()`, `disableAudio()`, `muteLocalAudioStream(bool)`, `muteAllRemoteAudioStreams(bool)`,
`muteRemoteAudioStream(uid, bool)`, `setAudioProfile(profile, scenario)`,
`adjustRecordingSignalVolume(0–400)`, `adjustPlaybackSignalVolume(0–400)`,
`enableAudioVolumeIndication(interval, smooth, reportVad)`, `enableSpeakerphone(bool)`,
`enableInEarMonitoring(bool)`.

### Video

`enableVideo()`, `disableVideo()`, `startPreview()`, `stopPreview()`, `muteLocalVideoStream(bool)`,
`muteAllRemoteVideoStreams(bool)`, `muteRemoteVideoStream(uid, bool)`, `switchCamera()`,
`enableDualStreamMode(bool)`, `setRemoteVideoStreamType(uid, type)`,
`setVideoEncoderConfiguration({ width, height, frameRate, bitrate })`.

### agora.createVideoView({ uid, renderMode })
Returns a view proxy that renders a video stream. `uid: 0` shows the local camera; any other uid
shows that remote user (create it after the `userJoined` event). Add it to a window/view and lay it
out with standard Ti properties. `renderMode`: `RENDER_MODE_HIDDEN` (default) or `RENDER_MODE_FIT`.

```js
const local  = agora.createVideoView({ uid: 0, width: Ti.UI.FILL, height: '50%' });
const remote = agora.createVideoView({ uid: 12345, width: Ti.UI.FILL, height: '50%' });
```

### Events

| Event | Payload |
|---|---|
| `joinChannelSuccess` | `{ channel, uid, elapsed }` |
| `rejoinChannelSuccess` | `{ channel, uid, elapsed }` |
| `userJoined` | `{ uid, elapsed }` |
| `userOffline` | `{ uid, reason }` |
| `leaveChannel` | `{ duration, txBytes, rxBytes }` |
| `connectionStateChanged` | `{ state, reason }` |
| `audioVolumeIndication` | `{ speakers: [{ uid, volume }], totalVolume }` |
| `firstRemoteVideoDecoded` | `{ uid, width, height, elapsed }` |
| `remoteAudioStateChanged` | `{ uid, state, reason }` |
| `tokenPrivilegeWillExpire` | `{ token }` |
| `error` | `{ code, message }` |

### Constants

`CLIENT_ROLE_BROADCASTER`, `CLIENT_ROLE_AUDIENCE`, `CHANNEL_PROFILE_COMMUNICATION`,
`CHANNEL_PROFILE_LIVE_BROADCASTING`, `RENDER_MODE_HIDDEN`, `RENDER_MODE_FIT`,
`VIDEO_STREAM_HIGH`, `VIDEO_STREAM_LOW`.

## Usage

```js
import agora from 'net.devloops.AgoraRTC';

agora.addEventListener('joinChannelSuccess', e => Ti.API.info('joined ' + e.channel));
agora.addEventListener('userJoined', e => {
  win.add(agora.createVideoView({ uid: e.uid, width: Ti.UI.FILL, height: '50%' }));
});

agora.initialize({ appId: 'YOUR_APP_ID' });
agora.enableVideo();
agora.enableAudio();
agora.startPreview();
agora.joinChannel({ token: null, channelId: 'demo', uid: 0, clientRole: agora.CLIENT_ROLE_BROADCASTER });
```

See `example/app.js` for a complete 1:1 call.

## Author

Abdullah Al-Faqeir

## License

See the `LICENSE` file included with this module.
