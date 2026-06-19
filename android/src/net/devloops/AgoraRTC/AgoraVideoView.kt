/**
 * AgoraVideoView — renders a local or remote Agora video stream.
 *
 * JS:  var local  = AgoraRTC.createVideoView({ uid: 0 });          // local camera
 *      var remote = AgoraRTC.createVideoView({ uid: 12345 });      // remote user
 */

package net.devloops.AgoraRTC

import android.app.Activity
import android.view.SurfaceView
import android.view.View
import org.appcelerator.kroll.KrollDict
import org.appcelerator.kroll.annotations.Kroll
import org.appcelerator.kroll.common.Log
import org.appcelerator.titanium.proxy.TiViewProxy
import org.appcelerator.titanium.util.TiConvert
import org.appcelerator.titanium.view.TiUIView
import io.agora.rtc2.video.VideoCanvas

@Kroll.proxy(creatableInModule = AgoraRTCModule::class)
class AgoraVideoView : TiViewProxy() {

	private var uid = 0
	private var renderMode = VideoCanvas.RENDER_MODE_HIDDEN

	companion object {
		private const val LCAT = "AgoraVideoView"
	}

	override fun handleCreationDict(options: KrollDict) {
		super.handleCreationDict(options)
		if (options.containsKey("uid")) uid = TiConvert.toInt(options, "uid")
		if (options.containsKey("renderMode")) renderMode = TiConvert.toInt(options, "renderMode")
	}

	// Native view is wired inside the TiUIView subclass — setNativeView() is protected.
	private inner class AgoraView(proxy: TiViewProxy) : TiUIView(proxy) {
		init {
			val engine = AgoraRTCModule.engine
			if (engine == null) {
				Log.w(LCAT, "createView before AgoraRTC.initialize() — empty view")
				setNativeView(View(proxy.activity))
			} else {
				// ponytail: plain SurfaceView; add setZOrderMediaOverlay() if you need overlapping panes.
				val surface = SurfaceView(proxy.activity)
				setNativeView(surface)
				val canvas = VideoCanvas(surface, renderMode, uid)
				if (uid == 0) engine.setupLocalVideo(canvas) else engine.setupRemoteVideo(canvas)
			}
		}
	}

	override fun createView(activity: Activity): TiUIView = AgoraView(this)
}
