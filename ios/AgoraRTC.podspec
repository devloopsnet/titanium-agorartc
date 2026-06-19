
Pod::Spec.new do |s|

    s.name         = "AgoraRTC"
    s.version      = "1.0.0"
    s.summary      = "The AgoraRTC Titanium module."
  
    s.description  = <<-DESC
                     The AgoraRTC Titanium module.
                     DESC
  
   s.homepage     = "https://example.com"
    s.license      = { :type => "Apache 2", :file => "LICENSE" }
    s.author       = 'Author'
  
    s.platform     = :ios
    s.ios.deployment_target = '13.0'

    s.source       = { :git => "https://github.com/<organization>/<repository>.git" }

    s.ios.weak_frameworks = 'UIKit', 'Foundation'

    s.ios.dependency 'TitaniumKit'
    # Agora is provided via Swift Package Manager (see ios/spm.json) so the module compiles
    # against AgoraRtcKit and the package propagates to consuming apps. Do not also declare it
    # as a CocoaPods dependency here — that double-links AgoraRtcKit.
  
    s.public_header_files = 'Classes/*.h'
    s.source_files = 'Classes/*.{h,m,swift}'
  end