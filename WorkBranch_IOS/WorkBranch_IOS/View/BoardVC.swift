//
//  BoardVC.swift
//  WorkBranch_IOS
//
//  Created by 이민혁 on 2018. 6. 15..
//  Copyright © 2018년 이민혁. All rights reserved.
//

import Foundation
import UIKit
import Alamofire
class BoardVC: UITableViewController {
    var list = [NSDictionary]()
    @IBAction func addBoard(_ sender: Any) {
        let addAlert = UIAlertController(title: "게시판 생성", message: nil, preferredStyle: .alert)
        
        addAlert.addTextField(){ (tf) in
            tf.placeholder = "게시판 이름"
        }
        addAlert.addAction(UIAlertAction(title: "cancel", style: .cancel))
        addAlert.addAction(UIAlertAction(title: "Accept", style: .default){ (_) in
            let param: Parameters = [
                //            로그인 정보 받아오기..,
                "email" : "testuser",
                "title" : addAlert.textFields?[0].text ?? ""
            ]
            
            let url = "http://localhost:8080/api/board"
            let call = Alamofire.request(url, method: .post, parameters: param, encoding: JSONEncoding.default)
            
            call.responseJSON { res in
                
                guard let jsonObject = res.result.value as? [String: Any] else {
                    self.alert("서버 호출 과정에서 오류 발생")
                    return
                }
                let resultCode = jsonObject["result_code"] as! Int
                if resultCode == 200 {
                    self.alert("게시판 생성이 완료되었습니다.") {
                        
                    }
                    
                    
                } else {
                  
                    self.alert("항목들을 전부 입력해주세요")
                }
            }
        })
        self.present(addAlert, animated: false)
    }
    
    func boardJson() {
        self.list.removeAll()
        let url = "http://localhost:8080/api/board/list"
        let apiURI: URL! = URL(string: url)
        do {
            let stringdata = try NSString(contentsOf: apiURI!, encoding: 0x80_000_422)
            let encdata = stringdata.data(using: String.Encoding.utf8.rawValue)
            do {
                let apiArray = try JSONSerialization.jsonObject(with: encdata!, options: []) as? NSArray
                for obj in apiArray! {
                    self.list.append(obj as! NSDictionary)
                    print(self.list)
                }
            }
            catch  {
                self.alert("데이터 분석 실패")
            }
        } catch  {
            self.alert("데이터 로딩 실패")
        }
        
        
    }
    
}
