//
//  NetDevloopsAgoraRTCVideoViewProxy.swift
//  AgoraRTC — renders a local or remote Agora video stream.
//
//  JS:  var local  = AgoraRTC.createVideoView({ uid: 0 });       // local camera
//       var remote = AgoraRTC.createVideoView({ uid: 12345 });   // remote user
//

import UIKit
import TitaniumKit
import AgoraRtcKit

@objc(NetDevloopsAgoraRTCVideoViewProxy)
class NetDevloopsAgoraRTCVideoViewProxy: TiViewProxy {

  override func viewDidAttach() {
    super.viewDidAttach()
    guard let engine = NetDevloopsAgoraRTCModule.sharedEngine, let renderView = self.view else { return }

    let uid = (self.value(forKey: "uid") as? NSNumber)?.uintValue ?? 0
    let canvas = AgoraRtcVideoCanvas()
    canvas.view = renderView
    canvas.uid = uid
    canvas.renderMode = AgoraVideoRenderMode(rawValue: (self.value(forKey: "renderMode") as? NSNumber)?.uintValue ?? AgoraVideoRenderMode.hidden.rawValue) ?? .hidden

    if uid == 0 { engine.setupLocalVideo(canvas) } else { engine.setupRemoteVideo(canvas) }
  }
}
