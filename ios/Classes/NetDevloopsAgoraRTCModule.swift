//
//  NetDevloopsAgoraRTCModule.swift
//  AgoraRTC — Titanium native module (iOS)
//  Bridges the Agora full RTC SDK (audio + video) v4.x via AgoraRtcKit.
//

import UIKit
import TitaniumKit
import AgoraRtcKit

@objc(NetDevloopsAgoraRTCModule)
class NetDevloopsAgoraRTCModule: TiModule, AgoraRtcEngineDelegate {

  // Shared engine so the video-view proxy can reach it.
  static var sharedEngine: AgoraRtcEngineKit?

  func moduleGUID() -> String { "ad086131-da57-44dc-93cb-5fce1654de78" }
  override func moduleId() -> String! { "net.devloops.AgoraRTC" }

  override func startup() {
    super.startup()
  }

  // MARK: - Constants

  @objc public var CLIENT_ROLE_BROADCASTER: NSNumber { NSNumber(value: AgoraClientRole.broadcaster.rawValue) }
  @objc public var CLIENT_ROLE_AUDIENCE: NSNumber { NSNumber(value: AgoraClientRole.audience.rawValue) }
  @objc public var CHANNEL_PROFILE_COMMUNICATION: NSNumber { NSNumber(value: AgoraChannelProfile.communication.rawValue) }
  @objc public var CHANNEL_PROFILE_LIVE_BROADCASTING: NSNumber { NSNumber(value: AgoraChannelProfile.liveBroadcasting.rawValue) }
  @objc public var VIDEO_STREAM_HIGH: NSNumber { NSNumber(value: AgoraVideoStreamType.high.rawValue) }
  @objc public var VIDEO_STREAM_LOW: NSNumber { NSNumber(value: AgoraVideoStreamType.low.rawValue) }
  @objc public var RENDER_MODE_HIDDEN: NSNumber { NSNumber(value: AgoraVideoRenderMode.hidden.rawValue) }
  @objc public var RENDER_MODE_FIT: NSNumber { NSNumber(value: AgoraVideoRenderMode.fit.rawValue) }

  // MARK: - Helpers

  private func opts(_ arguments: Array<Any>?) -> [String: Any] {
    (arguments?.first as? [String: Any]) ?? [:]
  }
  private func intArg(_ arguments: Array<Any>?, _ index: Int) -> Int {
    (arguments?[safe: index] as? NSNumber)?.intValue ?? 0
  }
  private func boolArg(_ arguments: Array<Any>?, _ index: Int) -> Bool {
    (arguments?[safe: index] as? NSNumber)?.boolValue ?? false
  }

  // MARK: - Lifecycle

  @objc(initialize:)
  func initialize(_ arguments: Array<Any>?) {
    if NetDevloopsAgoraRTCModule.sharedEngine != nil { return }
    let p = opts(arguments)
    let config = AgoraRtcEngineConfig()
    config.appId = p["appId"] as? String
    NetDevloopsAgoraRTCModule.sharedEngine = AgoraRtcEngineKit.sharedEngine(with: config, delegate: self)
  }

  @objc(destroy:)
  func destroy(_ arguments: Array<Any>?) {
    NetDevloopsAgoraRTCModule.sharedEngine?.stopPreview()
    NetDevloopsAgoraRTCModule.sharedEngine?.leaveChannel(nil)
    AgoraRtcEngineKit.destroy()
    NetDevloopsAgoraRTCModule.sharedEngine = nil
  }

  // MARK: - Channel

  @objc(joinChannel:)
  func joinChannel(_ arguments: Array<Any>?) {
    guard let engine = NetDevloopsAgoraRTCModule.sharedEngine else { return }
    let p = opts(arguments)
    let token = p["token"] as? String
    let channelId = p["channelId"] as? String ?? ""
    let uid = (p["uid"] as? NSNumber)?.uintValue ?? 0

    let options = AgoraRtcChannelMediaOptions()
    options.clientRoleType = AgoraClientRole(rawValue: (p["clientRole"] as? NSNumber)?.intValue ?? AgoraClientRole.broadcaster.rawValue) ?? .broadcaster
    options.channelProfile = AgoraChannelProfile(rawValue: (p["channelProfile"] as? NSNumber)?.intValue ?? AgoraChannelProfile.liveBroadcasting.rawValue) ?? .liveBroadcasting
    options.publishMicrophoneTrack = (p["publishMicrophone"] as? NSNumber)?.boolValue ?? true
    options.publishCameraTrack = (p["publishCamera"] as? NSNumber)?.boolValue ?? true
    options.autoSubscribeAudio = (p["autoSubscribeAudio"] as? NSNumber)?.boolValue ?? true
    options.autoSubscribeVideo = (p["autoSubscribeVideo"] as? NSNumber)?.boolValue ?? true

    engine.joinChannel(byToken: token, channelId: channelId, uid: uid, mediaOptions: options, joinSuccess: nil)
  }

  @objc(leaveChannel:)
  func leaveChannel(_ arguments: Array<Any>?) {
    NetDevloopsAgoraRTCModule.sharedEngine?.leaveChannel(nil)
  }

  @objc(renewToken:)
  func renewToken(_ arguments: Array<Any>?) {
    guard let token = arguments?.first as? String else { return }
    NetDevloopsAgoraRTCModule.sharedEngine?.renewToken(token)
  }

  @objc(setClientRole:)
  func setClientRole(_ arguments: Array<Any>?) {
    let role = AgoraClientRole(rawValue: intArg(arguments, 0)) ?? .broadcaster
    NetDevloopsAgoraRTCModule.sharedEngine?.setClientRole(role)
  }

  @objc(setChannelProfile:)
  func setChannelProfile(_ arguments: Array<Any>?) {
    let profile = AgoraChannelProfile(rawValue: intArg(arguments, 0)) ?? .liveBroadcasting
    NetDevloopsAgoraRTCModule.sharedEngine?.setChannelProfile(profile)
  }

  // MARK: - Audio

  @objc(enableAudio:)  func enableAudio(_ a: Array<Any>?)  { NetDevloopsAgoraRTCModule.sharedEngine?.enableAudio() }
  @objc(disableAudio:) func disableAudio(_ a: Array<Any>?) { NetDevloopsAgoraRTCModule.sharedEngine?.disableAudio() }
  @objc(muteLocalAudioStream:)       func muteLocalAudioStream(_ a: Array<Any>?)       { NetDevloopsAgoraRTCModule.sharedEngine?.muteLocalAudioStream(boolArg(a, 0)) }
  @objc(muteAllRemoteAudioStreams:)  func muteAllRemoteAudioStreams(_ a: Array<Any>?)  { NetDevloopsAgoraRTCModule.sharedEngine?.muteAllRemoteAudioStreams(boolArg(a, 0)) }
  @objc(muteRemoteAudioStream:)      func muteRemoteAudioStream(_ a: Array<Any>?)      { NetDevloopsAgoraRTCModule.sharedEngine?.muteRemoteAudioStream(UInt(intArg(a, 0)), mute: boolArg(a, 1)) }
  @objc(adjustRecordingSignalVolume:) func adjustRecordingSignalVolume(_ a: Array<Any>?) { NetDevloopsAgoraRTCModule.sharedEngine?.adjustRecordingSignalVolume(intArg(a, 0)) }
  @objc(adjustPlaybackSignalVolume:)  func adjustPlaybackSignalVolume(_ a: Array<Any>?)  { NetDevloopsAgoraRTCModule.sharedEngine?.adjustPlaybackSignalVolume(intArg(a, 0)) }
  @objc(enableSpeakerphone:)   func enableSpeakerphone(_ a: Array<Any>?)   { NetDevloopsAgoraRTCModule.sharedEngine?.setEnableSpeakerphone(boolArg(a, 0)) }
    @objc(enableInEarMonitoring:) func enableInEarMonitoring(_ a: Array<Any>?) { NetDevloopsAgoraRTCModule.sharedEngine?.enable(inEarMonitoring: boolArg(a, 0)) }

  @objc(setAudioProfile:)
  func setAudioProfile(_ arguments: Array<Any>?) {
    if let profile = AgoraAudioProfile(rawValue: intArg(arguments, 0)) {
      NetDevloopsAgoraRTCModule.sharedEngine?.setAudioProfile(profile)
    }
    if let scenario = AgoraAudioScenario(rawValue: intArg(arguments, 1)) {
      NetDevloopsAgoraRTCModule.sharedEngine?.setAudioScenario(scenario)
    }
  }

  @objc(enableAudioVolumeIndication:)
  func enableAudioVolumeIndication(_ arguments: Array<Any>?) {
    NetDevloopsAgoraRTCModule.sharedEngine?.enableAudioVolumeIndication(intArg(arguments, 0), smooth: intArg(arguments, 1), reportVad: boolArg(arguments, 2))
  }

  // MARK: - Video

  @objc(enableVideo:)  func enableVideo(_ a: Array<Any>?)  { NetDevloopsAgoraRTCModule.sharedEngine?.enableVideo() }
  @objc(disableVideo:) func disableVideo(_ a: Array<Any>?) { NetDevloopsAgoraRTCModule.sharedEngine?.disableVideo() }
  @objc(startPreview:) func startPreview(_ a: Array<Any>?) { NetDevloopsAgoraRTCModule.sharedEngine?.startPreview() }
  @objc(stopPreview:)  func stopPreview(_ a: Array<Any>?)  { NetDevloopsAgoraRTCModule.sharedEngine?.stopPreview() }
  @objc(muteLocalVideoStream:)      func muteLocalVideoStream(_ a: Array<Any>?)      { NetDevloopsAgoraRTCModule.sharedEngine?.muteLocalVideoStream(boolArg(a, 0)) }
  @objc(muteAllRemoteVideoStreams:) func muteAllRemoteVideoStreams(_ a: Array<Any>?) { NetDevloopsAgoraRTCModule.sharedEngine?.muteAllRemoteVideoStreams(boolArg(a, 0)) }
  @objc(muteRemoteVideoStream:)     func muteRemoteVideoStream(_ a: Array<Any>?)     { NetDevloopsAgoraRTCModule.sharedEngine?.muteRemoteVideoStream(UInt(intArg(a, 0)), mute: boolArg(a, 1)) }
  @objc(switchCamera:)              func switchCamera(_ a: Array<Any>?)              { NetDevloopsAgoraRTCModule.sharedEngine?.switchCamera() }
  @objc(enableDualStreamMode:)      func enableDualStreamMode(_ a: Array<Any>?)      { NetDevloopsAgoraRTCModule.sharedEngine?.enableDualStreamMode(boolArg(a, 0)) }

  @objc(setRemoteVideoStreamType:)
  func setRemoteVideoStreamType(_ arguments: Array<Any>?) {
    let type = AgoraVideoStreamType(rawValue: intArg(arguments, 1)) ?? .high
    NetDevloopsAgoraRTCModule.sharedEngine?.setRemoteVideoStream(UInt(intArg(arguments, 0)), type: type)
  }

  @objc(setVideoEncoderConfiguration:)
  func setVideoEncoderConfiguration(_ arguments: Array<Any>?) {
    let p = opts(arguments)
    let width = (p["width"] as? NSNumber)?.intValue ?? 640
    let height = (p["height"] as? NSNumber)?.intValue ?? 360
    let frameRate = AgoraVideoFrameRate(rawValue: (p["frameRate"] as? NSNumber)?.intValue ?? 15) ?? .fps15
    let config = AgoraVideoEncoderConfiguration(
      size: CGSize(width: width, height: height),
      frameRate: frameRate.rawValue,
      bitrate: AgoraVideoBitrateStandard,
      orientationMode: .adaptative,
      mirrorMode: .auto
    )
    NetDevloopsAgoraRTCModule.sharedEngine?.setVideoEncoderConfiguration(config)
  }

  // MARK: - Permissions
  // iOS prompts automatically when the engine first uses the mic/camera, provided
  // NSMicrophoneUsageDescription / NSCameraUsageDescription are in the app's Info.plist.
  // No-op kept for cross-platform API parity with Android.
  @objc(requestPermissions:)
  func requestPermissions(_ arguments: Array<Any>?) {}

  // MARK: - AgoraRtcEngineDelegate → Titanium events

  func rtcEngine(_ engine: AgoraRtcEngineKit, didJoinChannel channel: String, withUid uid: UInt, elapsed: Int) {
    fire("joinChannelSuccess", ["channel": channel, "uid": uid, "elapsed": elapsed])
  }
  func rtcEngine(_ engine: AgoraRtcEngineKit, didRejoinChannel channel: String, withUid uid: UInt, elapsed: Int) {
    fire("rejoinChannelSuccess", ["channel": channel, "uid": uid, "elapsed": elapsed])
  }
  func rtcEngine(_ engine: AgoraRtcEngineKit, didJoinedOfUid uid: UInt, elapsed: Int) {
    fire("userJoined", ["uid": uid, "elapsed": elapsed])
  }
  func rtcEngine(_ engine: AgoraRtcEngineKit, didOfflineOfUid uid: UInt, reason: AgoraUserOfflineReason) {
    fire("userOffline", ["uid": uid, "reason": reason.rawValue])
  }
  func rtcEngine(_ engine: AgoraRtcEngineKit, didLeaveChannelWith stats: AgoraChannelStats) {
    fire("leaveChannel", ["duration": stats.duration, "txBytes": stats.txBytes, "rxBytes": stats.rxBytes])
  }
  func rtcEngine(_ engine: AgoraRtcEngineKit, connectionChangedTo state: AgoraConnectionState, reason: AgoraConnectionChangedReason) {
    fire("connectionStateChanged", ["state": state.rawValue, "reason": reason.rawValue])
  }
  func rtcEngine(_ engine: AgoraRtcEngineKit, reportAudioVolumeIndicationOfSpeakers speakers: [AgoraRtcAudioVolumeInfo], totalVolume: Int) {
    let list = speakers.map { ["uid": $0.uid, "volume": $0.volume] }
    fire("audioVolumeIndication", ["speakers": list, "totalVolume": totalVolume])
  }
  func rtcEngine(_ engine: AgoraRtcEngineKit, firstRemoteVideoDecodedOfUid uid: UInt, size: CGSize, elapsed: Int) {
    fire("firstRemoteVideoDecoded", ["uid": uid, "width": Int(size.width), "height": Int(size.height), "elapsed": elapsed])
  }
  func rtcEngine(_ engine: AgoraRtcEngineKit, remoteAudioStateChangedOfUid uid: UInt, state: AgoraAudioRemoteState, reason: AgoraAudioRemoteReason, elapsed: Int) {
    fire("remoteAudioStateChanged", ["uid": uid, "state": state.rawValue, "reason": reason.rawValue])
  }
  func rtcEngine(_ engine: AgoraRtcEngineKit, tokenPrivilegeWillExpire token: String) {
    fire("tokenPrivilegeWillExpire", ["token": token])
  }
  func rtcEngine(_ engine: AgoraRtcEngineKit, didOccurError errorCode: AgoraErrorCode) {
    fire("error", ["code": errorCode.rawValue])
  }

  private func fire(_ name: String, _ data: [String: Any]) {
    if _hasListeners(name) { fireEvent(name, with: data) }
  }
}

private extension Array {
  subscript(safe index: Int) -> Element? {
    indices.contains(index) ? self[index] : nil
  }
}
