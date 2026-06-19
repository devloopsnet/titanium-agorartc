/**
 * Titanium SDK
 * Copyright TiDev, Inc. 04/07/2022-Present
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

/** This code is generated, do not edit by hand. **/

#include "net.devloops.AgoraRTC.AgoraVideoView.h"

#include "JSException.h"
#include "TypeConverter.h"


#include "org.appcelerator.titanium.proxy.TiViewProxy.h"

#define TAG "AgoraVideoView"

using namespace v8;

namespace net {
namespace devloops {
namespace agorartc {
	namespace agorartc {


Persistent<FunctionTemplate> AgoraVideoView::proxyTemplate;
Persistent<Object> AgoraVideoView::moduleInstance;
jclass AgoraVideoView::javaClass = NULL;

AgoraVideoView::AgoraVideoView() : titanium::Proxy()
{
}

void AgoraVideoView::bindProxy(Local<Object> exports, Local<Context> context)
{
	Isolate* isolate = context->GetIsolate();

	Local<FunctionTemplate> pt = getProxyTemplate(isolate);

	v8::TryCatch tryCatch(isolate);
	Local<Function> constructor;
	MaybeLocal<Function> maybeConstructor = pt->GetFunction(context);
	if (!maybeConstructor.ToLocal(&constructor)) {
		titanium::V8Util::fatalException(isolate, tryCatch);
		return;
	}

	Local<String> nameSymbol = NEW_SYMBOL(isolate, "AgoraVideoView"); // use symbol over string for efficiency
	exports->Set(context, nameSymbol, constructor);
	exports->Set(context, constructorSymbol.Get(isolate), constructor);
}

void AgoraVideoView::dispose(Isolate* isolate)
{
	LOGD(TAG, "dispose()");
	if (!proxyTemplate.IsEmpty()) {
		proxyTemplate.Reset();
	}
	if (!moduleInstance.IsEmpty()) {
	    moduleInstance.Reset();
	}

	titanium::TiViewProxy::dispose(isolate);
}

Local<FunctionTemplate> AgoraVideoView::getProxyTemplate(v8::Isolate* isolate)
{
	Local<Context> context = isolate->GetCurrentContext();
	if (!proxyTemplate.IsEmpty()) {
		return proxyTemplate.Get(isolate);
	}

	LOGD(TAG, "AgoraVideoView::getProxyTemplate()");

	javaClass = titanium::JNIUtil::findClass("net/devloops/AgoraRTC/AgoraVideoView");
	EscapableHandleScope scope(isolate);

	// use symbol over string for efficiency
	Local<String> nameSymbol = NEW_SYMBOL(isolate, "AgoraVideoView");

	Local<FunctionTemplate> t = titanium::Proxy::inheritProxyTemplate(
		isolate,
		titanium::TiViewProxy::getProxyTemplate(isolate),
		javaClass,
		nameSymbol);

	proxyTemplate.Reset(isolate, t);
	t->Set(titanium::Proxy::inheritSymbol.Get(isolate), FunctionTemplate::New(isolate, titanium::Proxy::inherit<AgoraVideoView>));

	// Method bindings --------------------------------------------------------

	Local<ObjectTemplate> prototypeTemplate = t->PrototypeTemplate();
	Local<ObjectTemplate> instanceTemplate = t->InstanceTemplate();

	// Delegate indexed property get and set to the Java proxy.
	instanceTemplate->SetIndexedPropertyHandler(titanium::Proxy::getIndexedProperty,
		titanium::Proxy::setIndexedProperty);

	// Constants --------------------------------------------------------------

	// Dynamic properties -----------------------------------------------------

	// Accessors --------------------------------------------------------------

	return scope.Escape(t);
}

Local<FunctionTemplate> AgoraVideoView::getProxyTemplate(v8::Local<v8::Context> context)
{
	return getProxyTemplate(context->GetIsolate());
}

// Methods --------------------------------------------------------------------

// Dynamic property accessors -------------------------------------------------


	} // namespace agorartc
} // agorartc
} // devloops
} // net
