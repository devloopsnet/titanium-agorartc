/**
 * Titanium SDK
 * Copyright TiDev, Inc. 04/07/2022-Present
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 * Warning: This file is GENERATED, and should not be modified
 */
var bootstrap = kroll.NativeModule.require("bootstrap"),
	invoker = kroll.NativeModule.require("invoker"),
	Titanium = kroll.binding("Titanium").Titanium;

function moduleBootstrap(moduleBinding) {
	function lazyGet(object, binding, name, namespace) {
		return bootstrap.lazyGet(object, binding,
			name, namespace, moduleBinding.getBinding);
	}

	var module = moduleBinding.getBinding("net.devloops.AgoraRTC.AgoraRTCModule")["AgoraRTC"];
	var invocationAPIs = module.invocationAPIs = [];
	module.apiName = "AgoraRTC";

	function addInvocationAPI(module, moduleNamespace, namespace, api) {
		invocationAPIs.push({ namespace: namespace, api: api });
	}

	addInvocationAPI(module, "AgoraRTC", "AgoraRTC", "createAgoraVideoView");
		if (!("__propertiesDefined__" in module)) {Object.defineProperties(module, {
"AgoraVideoView": {
get: function() {
var AgoraVideoView =  lazyGet(this, "net.devloops.AgoraRTC.AgoraVideoView", "AgoraVideoView", "AgoraVideoView");
return AgoraVideoView;
},
configurable: true
},

});
module.constructor.prototype.createAgoraVideoView = function() {
return new module["AgoraVideoView"](arguments);
}
}
module.__propertiesDefined__ = true;
return module;

}
exports.bootstrap = moduleBootstrap;
