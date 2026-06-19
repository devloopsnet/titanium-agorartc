package net.devloops.AgoraRTC;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 M2\u00020\u0001:\u0001MB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0017J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001c\u001a\u00020\u0017J\u000e\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0005J\u000e\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0005J\u0006\u0010$\u001a\u00020\u0017J\u0006\u0010%\u001a\u00020\u0017J\u000e\u0010&\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020(J\u000e\u0010)\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020(J\u0016\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(J\u000e\u0010,\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\u0005J\u000e\u0010.\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\u0005J\u000e\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u00020(J\u000e\u00101\u001a\u00020\u00172\u0006\u00100\u001a\u00020(J\u0016\u00102\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u0005J\u001e\u00104\u001a\u00020\u00172\u0006\u00105\u001a\u00020\u00052\u0006\u00106\u001a\u00020\u00052\u0006\u00107\u001a\u00020(J\u0006\u00108\u001a\u00020\u0017J\u0006\u00109\u001a\u00020\u0017J\u0006\u0010:\u001a\u00020\u0017J\u0006\u0010;\u001a\u00020\u0017J\u000e\u0010<\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020(J\u000e\u0010=\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020(J\u0016\u0010>\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u00052\u0006\u0010\'\u001a\u00020(J\u0006\u0010?\u001a\u00020\u0017J\u000e\u0010@\u001a\u00020\u00172\u0006\u00100\u001a\u00020(J\u0016\u0010A\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u00052\u0006\u0010B\u001a\u00020\u0005J\u000e\u0010C\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010D\u001a\u00020\u0017J\u0018\u0010H\u001a\u00020\u00172\u0006\u0010I\u001a\u00020\u001f2\u0006\u0010J\u001a\u00020\u0019H\u0002J\u0010\u0010K\u001a\u00020\u00172\u0006\u0010L\u001a\u00020\u001fH\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0014\u0010\u000e\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0014\u0010\u0010\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0014\u0010\u0012\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0014\u0010\u0014\u001a\u00020\u0005X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0010\u0010E\u001a\u00020FX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010G\u00a8\u0006N"}, d2 = {"Lnet/devloops/AgoraRTC/AgoraRTCModule;", "Lorg/appcelerator/kroll/KrollModule;", "<init>", "()V", "CLIENT_ROLE_BROADCASTER", "", "getCLIENT_ROLE_BROADCASTER", "()I", "CLIENT_ROLE_AUDIENCE", "getCLIENT_ROLE_AUDIENCE", "CHANNEL_PROFILE_COMMUNICATION", "getCHANNEL_PROFILE_COMMUNICATION", "CHANNEL_PROFILE_LIVE_BROADCASTING", "getCHANNEL_PROFILE_LIVE_BROADCASTING", "RENDER_MODE_HIDDEN", "getRENDER_MODE_HIDDEN", "RENDER_MODE_FIT", "getRENDER_MODE_FIT", "VIDEO_STREAM_HIGH", "getVIDEO_STREAM_HIGH", "VIDEO_STREAM_LOW", "getVIDEO_STREAM_LOW", "initialize", "", "params", "Lorg/appcelerator/kroll/KrollDict;", "destroy", "joinChannel", "leaveChannel", "renewToken", "token", "", "setClientRole", "role", "setChannelProfile", "profile", "enableAudio", "disableAudio", "muteLocalAudioStream", "mute", "", "muteAllRemoteAudioStreams", "muteRemoteAudioStream", "uid", "adjustRecordingSignalVolume", "volume", "adjustPlaybackSignalVolume", "enableSpeakerphone", "enabled", "enableInEarMonitoring", "setAudioProfile", "scenario", "enableAudioVolumeIndication", "interval", "smooth", "reportVad", "enableVideo", "disableVideo", "startPreview", "stopPreview", "muteLocalVideoStream", "muteAllRemoteVideoStreams", "muteRemoteVideoStream", "switchCamera", "enableDualStreamMode", "setRemoteVideoStreamType", "streamType", "setVideoEncoderConfiguration", "requestPermissions", "handler", "Lio/agora/rtc2/IRtcEngineEventHandler;", "Lio/agora/rtc2/IRtcEngineEventHandler;", "fire", "name", "data", "warnNoEngine", "method", "Companion", "module_release"})
@org.appcelerator.kroll.annotations.Kroll.module(name = "AgoraRTC", id = "net.devloops.AgoraRTC")
public final class AgoraRTCModule extends org.appcelerator.kroll.KrollModule {
    @org.appcelerator.kroll.annotations.Kroll.constant()
    private final int CLIENT_ROLE_BROADCASTER = io.agora.rtc2.Constants.CLIENT_ROLE_BROADCASTER;
    @org.appcelerator.kroll.annotations.Kroll.constant()
    private final int CLIENT_ROLE_AUDIENCE = io.agora.rtc2.Constants.CLIENT_ROLE_AUDIENCE;
    @org.appcelerator.kroll.annotations.Kroll.constant()
    private final int CHANNEL_PROFILE_COMMUNICATION = io.agora.rtc2.Constants.CHANNEL_PROFILE_COMMUNICATION;
    @org.appcelerator.kroll.annotations.Kroll.constant()
    private final int CHANNEL_PROFILE_LIVE_BROADCASTING = io.agora.rtc2.Constants.CHANNEL_PROFILE_LIVE_BROADCASTING;
    @org.appcelerator.kroll.annotations.Kroll.constant()
    private final int RENDER_MODE_HIDDEN = io.agora.rtc2.video.VideoCanvas.RENDER_MODE_HIDDEN;
    @org.appcelerator.kroll.annotations.Kroll.constant()
    private final int RENDER_MODE_FIT = io.agora.rtc2.video.VideoCanvas.RENDER_MODE_FIT;
    @org.appcelerator.kroll.annotations.Kroll.constant()
    private final int VIDEO_STREAM_HIGH = io.agora.rtc2.Constants.VIDEO_STREAM_HIGH;
    @org.appcelerator.kroll.annotations.Kroll.constant()
    private final int VIDEO_STREAM_LOW = io.agora.rtc2.Constants.VIDEO_STREAM_LOW;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String LCAT = "AgoraRTCModule";
    @org.jetbrains.annotations.Nullable()
    private static io.agora.rtc2.RtcEngine engine;
    @org.jetbrains.annotations.Nullable()
    private static net.devloops.AgoraRTC.AgoraRTCModule instance;
    @org.jetbrains.annotations.NotNull()
    private final io.agora.rtc2.IRtcEngineEventHandler handler = null;
    @org.jetbrains.annotations.NotNull()
    public static final net.devloops.AgoraRTC.AgoraRTCModule.Companion Companion = null;
    
    public AgoraRTCModule() {
        super();
    }
    
    public final int getCLIENT_ROLE_BROADCASTER() {
        return 0;
    }
    
    public final int getCLIENT_ROLE_AUDIENCE() {
        return 0;
    }
    
    public final int getCHANNEL_PROFILE_COMMUNICATION() {
        return 0;
    }
    
    public final int getCHANNEL_PROFILE_LIVE_BROADCASTING() {
        return 0;
    }
    
    public final int getRENDER_MODE_HIDDEN() {
        return 0;
    }
    
    public final int getRENDER_MODE_FIT() {
        return 0;
    }
    
    public final int getVIDEO_STREAM_HIGH() {
        return 0;
    }
    
    public final int getVIDEO_STREAM_LOW() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final io.agora.rtc2.RtcEngine getEngine() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final net.devloops.AgoraRTC.AgoraRTCModule getInstance() {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    @org.appcelerator.kroll.annotations.Kroll.onAppCreate()
    public static final void onAppCreate(@org.jetbrains.annotations.Nullable()
    org.appcelerator.titanium.TiApplication app) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void initialize(@org.jetbrains.annotations.NotNull()
    org.appcelerator.kroll.KrollDict params) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void destroy() {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void joinChannel(@org.jetbrains.annotations.NotNull()
    org.appcelerator.kroll.KrollDict params) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void leaveChannel() {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void renewToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void setClientRole(int role) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void setChannelProfile(int profile) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void enableAudio() {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void disableAudio() {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void muteLocalAudioStream(boolean mute) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void muteAllRemoteAudioStreams(boolean mute) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void muteRemoteAudioStream(int uid, boolean mute) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void adjustRecordingSignalVolume(int volume) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void adjustPlaybackSignalVolume(int volume) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void enableSpeakerphone(boolean enabled) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void enableInEarMonitoring(boolean enabled) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void setAudioProfile(int profile, int scenario) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void enableAudioVolumeIndication(int interval, int smooth, boolean reportVad) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void enableVideo() {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void disableVideo() {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void startPreview() {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void stopPreview() {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void muteLocalVideoStream(boolean mute) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void muteAllRemoteVideoStreams(boolean mute) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void muteRemoteVideoStream(int uid, boolean mute) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void switchCamera() {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void enableDualStreamMode(boolean enabled) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void setRemoteVideoStreamType(int uid, int streamType) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void setVideoEncoderConfiguration(@org.jetbrains.annotations.NotNull()
    org.appcelerator.kroll.KrollDict params) {
    }
    
    @org.appcelerator.kroll.annotations.Kroll.method()
    public final void requestPermissions() {
    }
    
    private final void fire(java.lang.String name, org.appcelerator.kroll.KrollDict data) {
    }
    
    private final void warnNoEngine(java.lang.String method) {
    }
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R*\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@BX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0003\u001a\u0004\b\n\u0010\u000bR*\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u0006\u001a\u0004\u0018\u00010\f8\u0006@BX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0015"}, d2 = {"Lnet/devloops/AgoraRTC/AgoraRTCModule$Companion;", "", "<init>", "()V", "LCAT", "", "value", "Lio/agora/rtc2/RtcEngine;", "engine", "getEngine$annotations", "getEngine", "()Lio/agora/rtc2/RtcEngine;", "Lnet/devloops/AgoraRTC/AgoraRTCModule;", "instance", "getInstance$annotations", "getInstance", "()Lnet/devloops/AgoraRTC/AgoraRTCModule;", "onAppCreate", "", "app", "Lorg/appcelerator/titanium/TiApplication;", "module_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final io.agora.rtc2.RtcEngine getEngine() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        @java.lang.Deprecated()
        public static void getEngine$annotations() {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final net.devloops.AgoraRTC.AgoraRTCModule getInstance() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        @java.lang.Deprecated()
        public static void getInstance$annotations() {
        }
        
        @kotlin.jvm.JvmStatic()
        @org.appcelerator.kroll.annotations.Kroll.onAppCreate()
        public final void onAppCreate(@org.jetbrains.annotations.Nullable()
        org.appcelerator.titanium.TiApplication app) {
        }
    }
}