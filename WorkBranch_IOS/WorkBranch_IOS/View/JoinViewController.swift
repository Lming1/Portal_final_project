//
//  JoinViewController.swift
//  WorkBranch_IOS
//
//  Created by 이민혁 on 2018. 6. 7..
//  Copyright © 2018년 이민혁. All rights reserved.
//

import UIKit

class JoinViewController: UIViewController {
    @IBOutlet var emailField: UITextField!
    @IBOutlet var nameField: UITextField!
    @IBOutlet var passwordField: UITextField!
    

    @IBAction func ok(_ sender: Any) {
        let alert = UIAlertController(title: "회원 가입", message: "입력한 정보가 정확하면 확인 버튼을 눌러주세요", preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "확인", style: .default) { (_) in
            self.joinWithJson()
            _ = self.navigationController?.popViewController(animated: true)
        })
        alert.addAction(UIAlertAction(title: "취소", style: .cancel))
        
        self.present(alert, animated: false)
    }
    
    func joinWithJson() {
        // 회원정보
        let uvo = UserVO()
        // 가입 정보 받아오기
        uvo.email = emailField.text
        uvo.name = nameField.text
        uvo.password = passwordField.text
        
        let url = URL(string: "http://localhost:8080/api/user")!
        let jsonDict = ["email": "\(String(uvo.email!))",
            "name": "\(String(uvo.name!))",
            "password": "\(String(uvo.password!))"]
        let jsonData = try! JSONSerialization.data(withJSONObject: jsonDict, options: [])
        
        var request = URLRequest(url: url)
        request.httpMethod = "post"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = jsonData
        
        let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
            if let error = error {
                print("error:", error)
                let alert = UIAlertController(title: "네트워크 연결 오류", message: nil, preferredStyle: .alert)
                alert.addAction(UIAlertAction(title: "확인", style: .cancel))
                self.present(alert, animated: false)
                return
            }
            do {
                guard let data = data else { return }
                guard let json = try JSONSerialization.jsonObject(with: data, options: []) as? [String: AnyObject] else {
                    return
                }
                print("json:", json)
            } catch {
                print("error:", error)
            }
        }
        
        task.resume()
        
        
    }
    @IBAction func cancel(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
}
