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
    var pvo = [Dictionary<String, Any>]()
    var list = [NSDictionary]()
    
    override func viewDidLoad() {
        self.boardJson()
    }
    @IBAction func addBoard(_ sender: Any) {
        let addAlert = UIAlertController(title: "게시판 생성", message: nil, preferredStyle: .alert)
        
        addAlert.addTextField(){ (tf) in
            tf.placeholder = "게시판 이름"
        }
        addAlert.addTextField(){ (tf) in
            tf.placeholder = "내용"
        }
        
        addAlert.addAction(UIAlertAction(title: "cancel", style: .cancel))
        addAlert.addAction(UIAlertAction(title: "Accept", style: .destructive){ (_) in
            let title = addAlert.textFields?[0].text ?? ""
            let contents = addAlert.textFields?[1].text ?? ""
            let param: Parameters = [
                //            로그인 정보 받아오기..,
                "email" : "testuser",
                "projectId" : 7,
                "title" : title,
                "contents" : contents
            ]
            
            let url = "http://172.20.10.3:8080/api/board"
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
        let url = "http://172.20.10.3/api/board/list/7"
        let apiURI: URL! = URL(string: url)
        do {
            let apidata = try Data(contentsOf: apiURI)
            do {
                let apiArray = try JSONSerialization.jsonObject(with: apidata, options: []) as? NSArray
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
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.list.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let obj = self.list[indexPath.row]
        let cell = tableView.dequeueReusableCell(withIdentifier: "bCell") as! BoardCell
        cell.title?.text = obj["title"] as? String
        cell.content?.text = obj["contents"] as? String
        return cell
    }
    
}
