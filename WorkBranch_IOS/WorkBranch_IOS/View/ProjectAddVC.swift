//
//  ProjectAddVC.swift
//  WorkBranch_IOS
//
//  Created by 이민혁 on 2018. 6. 15..
//  Copyright © 2018년 이민혁. All rights reserved.
//

import Foundation
import UIKit
import Alamofire

class ProjectAddVC: UIViewController {
    @IBOutlet var projectName: UITextField!
    
    override func viewDidLoad() {
        
    }
    
  
    
    @IBAction func submit(_ sender: Any) {
        
        let param: Parameters = [
//            로그인 정보 받아오기..,
            "p_email" : "testuser",
            "projectName" : self.projectName.text!
        ]
        
        let url = "http://localhost:8080/api/project"
        let call = Alamofire.request(url, method: .post, parameters: param, encoding: JSONEncoding.default)
        
        call.responseJSON { res in
          
            guard let jsonObject = res.result.value as? [String: Any] else {
                self.alert("서버 호출 과정에서 오류 발생")
                return
            }
            let resultCode = jsonObject["result_code"] as! Int
            if resultCode == 200 {
                self.alert("프로젝트 생성이 완료되었습니다.") {
                    self.navigationController?.popViewController(animated: false)
                }
                
                
            } else {
                //                let errorMsg = jsonObject["result_code"] as! Int
                self.alert("항목들을 전부 입력해주세요")
            }
        }
    }
    
}
