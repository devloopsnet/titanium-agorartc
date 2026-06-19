# AgoraRTC — Titanium native module

Bridges the **Agora full RTC SDK** (audio + video) to Titanium for iOS and Android.

- Android: `io.agora.rtc:full-sdk:4.6.2` (wired in `android/build.gradle`)
- iOS: `AgoraRtcEngine_iOS` 4.6.2 via Swift Package Manager (wired in `ios/spm.json`, product `RtcBasic` → module `AgoraRtcKit`)

> 4.6.2 is the latest version Agora ships on **both** Maven (Android) and the iOS SwiftPM repo
> (iOS has no stable 4.6.3 — only a prerelease). Keep both platforms on the same stable version.
>
> The iOS product is marked `"linkage": "host"` in `spm.json` **on purpose**: that tells Titanium's
> app build to add the Agora package to the *app* project and embed `AgoraRtcKit` into the app
> bundle. Without it (default `"embedded"`), the module compiles but the framework never reaches the
> app's `Frameworks/` and the app crashes at launch with a missing-library error.
>
> The module's own xcodeproj also needs the package to compile — that's a one-time injection done by
> `ios/inject-spm.mjs` (Titanium only embeds `spm.json` into module metadata for *consumers*; it does
> not add it to the module project itself). Re-run that script if the module xcodeproj is regenerated.

Module id: `net.devloops.AgoraRTC`

## Setup

### tiapp.xml

```xml
<modules>
  <module platform="android">net.devloops.AgoraRTC</module>
  <module platform="iphone">net.devloops.AgoraRTC</module>
</modules>
```

### Permissions

**Android** — the module bundles the needed `<uses-permission>` entries (INTERNET, RECORD_AUDIO,
CAMERA, MODIFY_AUDIO_SETTINGS, network, bluetooth). `RECORD_AUDIO` and `CAMERA` are runtime
permissions; request them from JS before joining (see example):

```js
Ti.Android.requestPermissions(['android.permission.RECORD_AUDIO', 'android.permission.CAMERA'], cb);
```

**iOS** — add the usage descriptions to your app's `tiapp.xml` (the module can't inject Info.plist keys):

```xml
<ios>
  <plist><dict>
    <key>NSMicrophoneUsageDescription</key><string>Microphone is used for calls.</string>
    <key>NSCameraUsageDescription</key><string>Camera is used for video calls.</string>
  </dict></plist>
</ios>
```

## Usage

```js
import agora from 'net.devloops.AgoraRTC';

agora.addEventListener('joinChannelSuccess', e => console.log('joined', e.channel, e.uid));
agora.addEventListener('userJoined', e => { /* add agora.createVideoView({uid: e.uid}) */ });

agora.initialize({ appId: 'YOUR_APP_ID' });
agora.enableVideo();
agora.enableAudio();
agora.startPreview();
agora.joinChannel({ token: null, channelId: 'demo', uid: 0, clientRole: agora.CLIENT_ROLE_BROADCASTER });

// later
agora.leaveChannel();
agora.destroy();
```

A complete 1:1 voice + video call is in `example/app.js`. Set your App ID and (if your project
uses authentication) a temp token before running.

## API

### Lifecycle
| Method | Notes |
|---|---|
| `initialize({ appId })` | Create the engine (once). |
| `destroy()` | Leave, stop preview, release the engine. |
| `requestPermissions()` | Android convenience (fire-and-forget); prefer `Ti.Android.requestPermissions` so you get the result. No-op on iOS. |

### Channel
| Method | |
|---|---|
| `joinChannel({ token, channelId, uid, clientRole, channelProfile, publishMicrophone, publishCamera, autoSubscribeAudio, autoSubscribeVideo })` | `token` may be `null` for testing-mode projects; `uid: 0` lets Agora assign one. |
| `leaveChannel()` | |
| `renewToken(token)` | Call on `tokenPrivilegeWillExpire`. |
| `setClientRole(role)` | `CLIENT_ROLE_BROADCASTER` / `CLIENT_ROLE_AUDIENCE`. |
| `setChannelProfile(profile)` | `CHANNEL_PROFILE_COMMUNICATION` / `CHANNEL_PROFILE_LIVE_BROADCASTING`. |

### Audio
`enableAudio()` · `disableAudio()` · `muteLocalAudioStream(bool)` · `muteAllRemoteAudioStreams(bool)` ·
`muteRemoteAudioStream(uid, bool)` · `setAudioProfile(profile, scenario)` · `adjustRecordingSignalVolume(0–400)` ·
`adjustPlaybackSignalVolume(0–400)` · `enableAudioVolumeIndication(interval, smooth, reportVad)` ·
`enableSpeakerphone(bool)` · `enableInEarMonitoring(bool)`

### Video
`enableVideo()` · `disableVideo()` · `startPreview()` · `stopPreview()` · `muteLocalVideoStream(bool)` ·
`muteAllRemoteVideoStreams(bool)` · `muteRemoteVideoStream(uid, bool)` · `switchCamera()` ·
`enableDualStreamMode(bool)` · `setRemoteVideoStreamType(uid, type)` ·
`setVideoEncoderConfiguration({ width, height, frameRate, bitrate })`

### Video view
```js
const local  = agora.createVideoView({ uid: 0 });           // local camera (preview)
const remote = agora.createVideoView({ uid: 12345 });       // remote user, after 'userJoined'
```
A standard view proxy — add it to any window/view and use normal Ti layout properties
(`width`, `height`, `top`, …). Optional `renderMode`: `RENDER_MODE_HIDDEN` (default) / `RENDER_MODE_FIT`.

### Events
`joinChannelSuccess {channel, uid, elapsed}` · `rejoinChannelSuccess` · `userJoined {uid, elapsed}` ·
`userOffline {uid, reason}` · `leaveChannel {duration, txBytes, rxBytes}` ·
`connectionStateChanged {state, reason}` · `audioVolumeIndication {speakers:[{uid,volume}], totalVolume}` ·
`firstRemoteVideoDecoded {uid, width, height, elapsed}` · `remoteAudioStateChanged {uid, state, reason}` ·
`tokenPrivilegeWillExpire {token}` · `error {code, message}`

### Constants
`CLIENT_ROLE_BROADCASTER`, `CLIENT_ROLE_AUDIENCE`, `CHANNEL_PROFILE_COMMUNICATION`,
`CHANNEL_PROFILE_LIVE_BROADCASTING`, `RENDER_MODE_HIDDEN`, `RENDER_MODE_FIT`,
`VIDEO_STREAM_HIGH`, `VIDEO_STREAM_LOW`

## Building

```sh
ti build -p android --build-only
ti build -p ios --build-only
```

To bump the Agora version, change it in **both** `android/build.gradle` and `ios/spm.json`.
```
