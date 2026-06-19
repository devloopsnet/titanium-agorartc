/**
 * AgoraRTC — Titanium native module (Android)
 * Bridges the Agora full RTC SDK (audio + video) v4.x (io.agora.rtc2.*).
 */

package net.devloops.AgoraRTC

import org.appcelerator.kroll.KrollDict
import org.appcelerator.kroll.KrollModule
import org.appcelerator.kroll.annotations.Kroll
import org.appcelerator.kroll.common.Log
import org.appcelerator.titanium.TiApplication
import io.agora.rtc2.ChannelMediaOptions
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.RtcEngineConfig
import io.agora.rtc2.video.VideoEncoderConfiguration

@Kroll.module(name = "AgoraRTC", id = "net.devloops.AgoraRTC")
class AgoraRTCModule : KrollModule() {

	// --- Constants exposed to JS (AgoraRTC.CLIENT_ROLE_BROADCASTER, etc.) ---
	@Kroll.constant val CLIENT_ROLE_BROADCASTER = Constants.CLIENT_ROLE_BROADCASTER
	@Kroll.constant val CLIENT_ROLE_AUDIENCE = Constants.CLIENT_ROLE_AUDIENCE
	@Kroll.constant val CHANNEL_PROFILE_COMMUNICATION = Constants.CHANNEL_PROFILE_COMMUNICATION
	@Kroll.constant val CHANNEL_PROFILE_LIVE_BROADCASTING = Constants.CHANNEL_PROFILE_LIVE_BROADCASTING
	@Kroll.constant val RENDER_MODE_HIDDEN = io.agora.rtc2.video.VideoCanvas.RENDER_MODE_HIDDEN
	@Kroll.constant val RENDER_MODE_FIT = io.agora.rtc2.video.VideoCanvas.RENDER_MODE_FIT
	@Kroll.constant val VIDEO_STREAM_HIGH = Constants.VIDEO_STREAM_HIGH
	@Kroll.constant val VIDEO_STREAM_LOW = Constants.VIDEO_STREAM_LOW

	companion object {
		private const val LCAT = "AgoraRTCModule"

		// Shared singletons so the AgoraVideoView proxy can reach the live engine.
		@JvmStatic
		var engine: RtcEngine? = null
			private set

		@JvmStatic
		var instance: AgoraRTCModule? = null
			private set

		@Kroll.onAppCreate
		@JvmStatic
		fun onAppCreate(app: TiApplication?) {
		}
	}

	// ------------------------------------------------------------------
	// Lifecycle
	// ------------------------------------------------------------------

	@Kroll.method
	fun initialize(params: KrollDict) {
		if (engine != null) {
			Log.w(LCAT, "initialize() called but engine already exists")
			return
		}
		val appId = params.getString("appId")
		val config = RtcEngineConfig().apply {
			mContext = TiApplication.getInstance().applicationContext
			mAppId = appId
			mEventHandler = handler
		}
		engine = RtcEngine.create(config)
		instance = this
	}

	@Kroll.method
	fun destroy() {
		engine?.let {
			it.stopPreview()
			it.leaveChannel()
			RtcEngine.destroy()
		}
		engine = null
	}

	// ------------------------------------------------------------------
	// Channel
	// ------------------------------------------------------------------

	@Kroll.method
	fun joinChannel(params: KrollDict) {
		val e = engine ?: return warnNoEngine("joinChannel")
		val token = if (params.containsKeyAndNotNull("token")) params.getString("token") else null
		val channelId = params.getString("channelId")
		val uid = params.optInt("uid", 0)

		val options = ChannelMediaOptions().apply {
			channelProfile = params.optInt("channelProfile", Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
			clientRoleType = params.optInt("clientRole", Constants.CLIENT_ROLE_BROADCASTER)
			publishMicrophoneTrack = params.optBoolean("publishMicrophone", true)
			publishCameraTrack = params.optBoolean("publishCamera", true)
			autoSubscribeAudio = params.optBoolean("autoSubscribeAudio", true)
			autoSubscribeVideo = params.optBoolean("autoSubscribeVideo", true)
		}
		e.joinChannel(token, channelId, uid, options)
	}

	@Kroll.method
	fun leaveChannel() {
		engine?.leaveChannel()
	}

	@Kroll.method
	fun renewToken(token: String) {
		engine?.renewToken(token)
	}

	@Kroll.method
	fun setClientRole(role: Int) {
		engine?.setClientRole(role)
	}

	@Kroll.method
	fun setChannelProfile(profile: Int) {
		engine?.setChannelProfile(profile)
	}

	// ------------------------------------------------------------------
	// Audio
	// ------------------------------------------------------------------

	@Kroll.method fun enableAudio() { engine?.enableAudio() }
	@Kroll.method fun disableAudio() { engine?.disableAudio() }
	@Kroll.method fun muteLocalAudioStream(mute: Boolean) { engine?.muteLocalAudioStream(mute) }
	@Kroll.method fun muteAllRemoteAudioStreams(mute: Boolean) { engine?.muteAllRemoteAudioStreams(mute) }
	@Kroll.method fun muteRemoteAudioStream(uid: Int, mute: Boolean) { engine?.muteRemoteAudioStream(uid, mute) }
	@Kroll.method fun adjustRecordingSignalVolume(volume: Int) { engine?.adjustRecordingSignalVolume(volume) }
	@Kroll.method fun adjustPlaybackSignalVolume(volume: Int) { engine?.adjustPlaybackSignalVolume(volume) }
	@Kroll.method fun enableSpeakerphone(enabled: Boolean) { engine?.setEnableSpeakerphone(enabled) }
	@Kroll.method fun enableInEarMonitoring(enabled: Boolean) { engine?.enableInEarMonitoring(enabled) }

	@Kroll.method
	fun setAudioProfile(profile: Int, scenario: Int) {
		engine?.setAudioProfile(profile)
		engine?.setAudioScenario(scenario)
	}

	@Kroll.method
	fun enableAudioVolumeIndication(interval: Int, smooth: Int, reportVad: Boolean) {
		engine?.enableAudioVolumeIndication(interval, smooth, reportVad)
	}

	// ------------------------------------------------------------------
	// Video
	// ------------------------------------------------------------------

	@Kroll.method fun enableVideo() { engine?.enableVideo() }
	@Kroll.method fun disableVideo() { engine?.disableVideo() }
	@Kroll.method fun startPreview() { engine?.startPreview() }
	@Kroll.method fun stopPreview() { engine?.stopPreview() }
	@Kroll.method fun muteLocalVideoStream(mute: Boolean) { engine?.muteLocalVideoStream(mute) }
	@Kroll.method fun muteAllRemoteVideoStreams(mute: Boolean) { engine?.muteAllRemoteVideoStreams(mute) }
	@Kroll.method fun muteRemoteVideoStream(uid: Int, mute: Boolean) { engine?.muteRemoteVideoStream(uid, mute) }
	@Kroll.method fun switchCamera() { engine?.switchCamera() }
	@Kroll.method fun enableDualStreamMode(enabled: Boolean) { engine?.enableDualStreamMode(enabled) }
	@Kroll.method fun setRemoteVideoStreamType(uid: Int, streamType: Int) { engine?.setRemoteVideoStreamType(uid, streamType) }

	@Kroll.method
	fun setVideoEncoderConfiguration(params: KrollDict) {
		val e = engine ?: return warnNoEngine("setVideoEncoderConfiguration")
		val width = params.optInt("width", 640)
		val height = params.optInt("height", 360)
		val bitrate = params.optInt("bitrate", VideoEncoderConfiguration.STANDARD_BITRATE)
		val frameRate = when (params.optInt("frameRate", 15)) {
			1 -> VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_1
			7 -> VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_7
			10 -> VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_10
			24 -> VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_24
			30 -> VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_30
			60 -> VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_60
			else -> VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15
		}
		e.setVideoEncoderConfiguration(
			VideoEncoderConfiguration(
				VideoEncoderConfiguration.VideoDimensions(width, height),
				frameRate,
				bitrate,
				VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_ADAPTIVE
			)
		)
	}

	// ------------------------------------------------------------------
	// Permissions — convenience only. The host app should request via
	// Ti.Media.requestCameraPermissions / requestAudioRecorderPermissions so it
	// can react to the grant result. This fires a native request fire-and-forget.
	// ponytail: no result callback wired; use Ti permission APIs if you need one.
	// ------------------------------------------------------------------

	@Kroll.method
	fun requestPermissions() {
		val activity = TiApplication.getInstance().currentActivity ?: return
		val perms = arrayOf(
			android.Manifest.permission.RECORD_AUDIO,
			android.Manifest.permission.CAMERA
		)
		androidx.core.app.ActivityCompat.requestPermissions(activity, perms, 0xA60A)
	}

	// ------------------------------------------------------------------
	// Event handler — Agora callbacks → Titanium events (marshalled to JS thread)
	// ------------------------------------------------------------------

	private val handler = object : IRtcEngineEventHandler() {
		override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
			fire("joinChannelSuccess", KrollDict().apply {
				put("channel", channel); put("uid", uid); put("elapsed", elapsed)
			})
		}

		override fun onRejoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
			fire("rejoinChannelSuccess", KrollDict().apply {
				put("channel", channel); put("uid", uid); put("elapsed", elapsed)
			})
		}

		override fun onUserJoined(uid: Int, elapsed: Int) {
			fire("userJoined", KrollDict().apply { put("uid", uid); put("elapsed", elapsed) })
		}

		override fun onUserOffline(uid: Int, reason: Int) {
			fire("userOffline", KrollDict().apply { put("uid", uid); put("reason", reason) })
		}

		override fun onLeaveChannel(stats: IRtcEngineEventHandler.RtcStats?) {
			fire("leaveChannel", KrollDict().apply {
				put("duration", stats?.totalDuration ?: 0)
				put("txBytes", stats?.txBytes ?: 0)
				put("rxBytes", stats?.rxBytes ?: 0)
			})
		}

		override fun onConnectionStateChanged(state: Int, reason: Int) {
			fire("connectionStateChanged", KrollDict().apply { put("state", state); put("reason", reason) })
		}

		override fun onAudioVolumeIndication(speakers: Array<out IRtcEngineEventHandler.AudioVolumeInfo>?, totalVolume: Int) {
			val list = speakers?.map { KrollDict().apply { put("uid", it.uid); put("volume", it.volume) } }
			fire("audioVolumeIndication", KrollDict().apply {
				put("speakers", list?.toTypedArray() ?: emptyArray<KrollDict>())
				put("totalVolume", totalVolume)
			})
		}

		override fun onFirstRemoteVideoDecoded(uid: Int, width: Int, height: Int, elapsed: Int) {
			fire("firstRemoteVideoDecoded", KrollDict().apply {
				put("uid", uid); put("width", width); put("height", height); put("elapsed", elapsed)
			})
		}

		override fun onRemoteAudioStateChanged(uid: Int, state: Int, reason: Int, elapsed: Int) {
			fire("remoteAudioStateChanged", KrollDict().apply {
				put("uid", uid); put("state", state); put("reason", reason)
			})
		}

		override fun onTokenPrivilegeWillExpire(token: String?) {
			fire("tokenPrivilegeWillExpire", KrollDict().apply { put("token", token) })
		}

		override fun onError(err: Int) {
			fire("error", KrollDict().apply {
				put("code", err); put("message", RtcEngine.getErrorDescription(err))
			})
		}
	}

	private fun fire(name: String, data: KrollDict) {
		if (hasListeners(name)) fireEvent(name, data)
	}

	private fun warnNoEngine(method: String) {
		Log.w(LCAT, "$method() called before initialize()")
	}
}
