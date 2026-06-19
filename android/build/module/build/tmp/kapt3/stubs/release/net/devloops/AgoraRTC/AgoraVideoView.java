package net.devloops.AgoraRTC;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000f\u0010B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lnet/devloops/AgoraRTC/AgoraVideoView;", "Lorg/appcelerator/titanium/proxy/TiViewProxy;", "<init>", "()V", "uid", "", "renderMode", "handleCreationDict", "", "options", "Lorg/appcelerator/kroll/KrollDict;", "createView", "Lorg/appcelerator/titanium/view/TiUIView;", "activity", "Landroid/app/Activity;", "Companion", "AgoraView", "module_release"})
@org.appcelerator.kroll.annotations.Kroll.proxy(creatableInModule = net.devloops.AgoraRTC.AgoraRTCModule.class)
public final class AgoraVideoView extends org.appcelerator.titanium.proxy.TiViewProxy {
    private int uid = 0;
    private int renderMode = io.agora.rtc2.video.VideoCanvas.RENDER_MODE_HIDDEN;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String LCAT = "AgoraVideoView";
    @org.jetbrains.annotations.NotNull()
    public static final net.devloops.AgoraRTC.AgoraVideoView.Companion Companion = null;
    
    public AgoraVideoView() {
        super();
    }
    
    @java.lang.Override()
    public void handleCreationDict(@org.jetbrains.annotations.NotNull()
    org.appcelerator.kroll.KrollDict options) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public org.appcelerator.titanium.view.TiUIView createView(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lnet/devloops/AgoraRTC/AgoraVideoView$AgoraView;", "Lorg/appcelerator/titanium/view/TiUIView;", "proxy", "Lorg/appcelerator/titanium/proxy/TiViewProxy;", "<init>", "(Lnet/devloops/AgoraRTC/AgoraVideoView;Lorg/appcelerator/titanium/proxy/TiViewProxy;)V", "module_release"})
    final class AgoraView extends org.appcelerator.titanium.view.TiUIView {
        
        public AgoraView(@org.jetbrains.annotations.NotNull()
        org.appcelerator.titanium.proxy.TiViewProxy proxy) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lnet/devloops/AgoraRTC/AgoraVideoView$Companion;", "", "<init>", "()V", "LCAT", "", "module_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}