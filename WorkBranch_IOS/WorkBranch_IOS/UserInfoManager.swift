//
//  UserInfoManager.swift
//  WorkBranch_IOS
//
//  Created by 이민혁 on 2018. 6. 14..
//  Copyright © 2018년 이민혁. All rights reserved.
//

import Foundation
import Alamofire
import UIKit

struct UserInfoKey {
//    // 저장에 사용할 키
    static let loginId = "LOGINID"
    static let email = "EMAIL"
    static let name = "NAME"
    static let profile = "PROFILE"
    static let tutorial = "TUTORIAL"
}

class UserInfoManager {
    var loginid: Int {
        get {
            return UserDefaults.standard.integer(forKey: UserInfoKey.loginId)
        }
        set(v) {
            let ud = UserDefaults.standard
            ud.set(v, forKey: UserInfoKey.loginId)
            ud.synchronize()
        }
    }
    
    var email: String? {
        get {
            return UserDefaults.standard.string(forKey: UserInfoKey.email)
        }
        set (v) {
            let ud = UserDefaults.standard
            ud.set(v, forKey: UserInfoKey.email)
            ud.synchronize()
        }
    }
    
    var name: String? {
        get {
            return UserDefaults.standard.string(forKey: UserInfoKey.name)
        }
        set (v) {
            let ud = UserDefaults.standard
            ud.set(v, forKey: UserInfoKey.name)
            ud.synchronize()
        }
    }
    
    var profile: UIImage? {
        get {
            let ud = UserDefaults.standard
            if let _profile = ud.data(forKey: UserInfoKey.profile) {
                return UIImage(data: _profile)
            } else {
                // 이미지가 없다면 기본 이미지로
                return UIImage(named: "account.jpg")
            }
        }
        set(v) {
            if v != nil {
                let ud = UserDefaults.standard
                ud.set(UIImagePNGRepresentation( v! ), forKey: UserInfoKey.profile)
                ud.synchronize()
            }
        }
    }
    
    var isLogin: Bool {
        // 로그인 아이디가 0이거나 계정이 비어 있으면
        if self.loginid == 0 || self.email == nil {
            return false
        } else {
            return true
        }
    }
    
    func login(email: String, password: String, success: (() -> Void)? = nil, fail: ((String)->Void)? = nil) {
        let url = "http://172.20.10.3:8080/auth/login"
        let param: Parameters = [
            "email": email,
            "password": password
        ]
        
        let call = Alamofire.request(url, method: .post, parameters: param, encoding: JSONEncoding.default)
        call.responseJSON { res in
            guard let jsonObject = res.result.value as? NSDictionary else {
                fail?("잘못된 응답 형식입니다: \(res.result.value!)")
                return
            }
            let resultCode = jsonObject["result_code"] as! Int
            if resultCode == 200 {
                
//                let user = jsonObject["member"] as! NSDictionary
//                self.loginid = user["id"] as! Int
//                self.email = user["email"] as? String
//                self.name = user["name"] as? String
                
//                if let path = user["profile_path"] as? String {
//                    if let imageData = try? Data(contentsOf: URL(string: path)!) {
//                        self.profile = UIImage(data: imageData)
//                    }
//                }
                success?()
            } else {
                let msg = (jsonObject["error_msg"] as? String) ?? "로그인이 실패했습니다."
                fail?(msg)
            }
        }
    }
    
    func logout(completion: (()->Void)? = nil) {
        let url = "http://localhost:8080/logout"
        let call = Alamofire.request(url, method: .post, encoding: JSONEncoding.default)
        call.responseJSON { _ in
            
            completion?()
        }
    }
    
}
