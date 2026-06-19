// One-shot: add the Agora SPM package to the module's own AgoraRTC.xcodeproj so the
// module compiles against AgoraRtcKit. (ti's module build only embeds spm.json for the
// consuming app; it does NOT inject the package into the module project itself.)
// Run: node ios/inject-spm.mjs   — re-run safely; injectSPMPackage is idempotent.

import { createRequire } from 'module';
const require = createRequire(import.meta.url);

const SDK = process.env.TI_SDK ||
	`${process.env.HOME}/Library/Titanium/mobilesdk/osx/13.2.0.GA`;

const xcode = require(`${SDK}/node_modules/xcode`);
const { injectSPMPackage } = await import(`${SDK}/iphone/cli/lib/ios/spm.js`);

const pbxPath = new URL('./AgoraRTC.xcodeproj/project.pbxproj', import.meta.url).pathname;
const proj = xcode.project(pbxPath).parseSync();
const xobjs = proj.hash.project.objects;

injectSPMPackage(xobjs, {
	remotePackageReference: 'AgoraRtcEngine_iOS',
	repositoryURL: 'https://github.com/AgoraIO/AgoraRtcEngine_iOS',
	requirementKind: 'upToNextMinorVersion',
	requirementMinimumVersion: '4.6.2',
	products: [{ productName: 'RtcBasic', frameworkName: 'AgoraRtcKit' }]
}, { generateUUID: () => proj.generateUuid() });

require('fs').writeFileSync(pbxPath, proj.writeSync());
console.log('Injected Agora SPM package into AgoraRTC.xcodeproj');
