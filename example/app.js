// AgoraRTC example — a minimal 1:1 voice + video call.
//
// Before running:
//   1. Set APP_ID below (Agora console → your project).
//   2. For a testing-mode project set TOKEN = null; for a secured project paste a
//      temp token generated for CHANNEL in the console.
//   3. iOS: add NSCameraUsageDescription + NSMicrophoneUsageDescription to tiapp.xml.
//      Android: permissions are bundled by the module; you still grant them at runtime.

import agora from 'net.devloops.AgoraRTC';

const APP_ID = 'YOUR_AGORA_APP_ID';
const TOKEN = null;            // null for testing-mode projects
const CHANNEL = 'demo';

const win = Ti.UI.createWindow({ backgroundColor: '#000', layout: 'vertical' });

// Local preview (uid 0) + a slot we fill when a remote user joins.
const localView = agora.createVideoView({ uid: 0, top: 0, width: Ti.UI.FILL, height: '50%' });
const remoteContainer = Ti.UI.createView({ width: Ti.UI.FILL, height: '50%' });
win.add(localView);
win.add(remoteContainer);

const status = Ti.UI.createLabel({ color: '#fff', bottom: 20, text: 'idle' });
win.add(status);

// ---- events ----
agora.addEventListener('joinChannelSuccess', e => { status.text = 'joined ' + e.channel + ' as ' + e.uid; });
agora.addEventListener('userJoined', e => {
	status.text = 'remote ' + e.uid + ' joined';
	remoteContainer.removeAllChildren && remoteContainer.removeAllChildren();
	remoteContainer.add(agora.createVideoView({ uid: e.uid, width: Ti.UI.FILL, height: Ti.UI.FILL }));
});
agora.addEventListener('userOffline', e => { status.text = 'remote ' + e.uid + ' left'; });
agora.addEventListener('error', e => { status.text = 'error ' + e.code; Ti.API.error('[Agora] ' + JSON.stringify(e)); });

function startCall() {
	agora.initialize({ appId: APP_ID });
	agora.enableVideo();
	agora.enableAudio();
	agora.setVideoEncoderConfiguration({ width: 640, height: 360, frameRate: 15 });
	agora.startPreview();
	agora.joinChannel({ token: TOKEN, channelId: CHANNEL, uid: 0, clientRole: agora.CLIENT_ROLE_BROADCASTER });
}

win.addEventListener('close', () => { agora.leaveChannel(); agora.destroy(); });

win.open();

// Grant mic + camera, then start. Android needs the runtime grant; iOS prompts on first use.
if (Ti.Platform.osname === 'android') {
	const perms = ['android.permission.RECORD_AUDIO', 'android.permission.CAMERA'];
	Ti.Android.requestPermissions(perms, e => { if (e.success) startCall(); else status.text = 'permissions denied'; });
} else {
	startCall();
}
