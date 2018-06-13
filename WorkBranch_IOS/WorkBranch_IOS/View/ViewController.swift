//
//  ViewController.swift
//  WorkBranch_IOS
//
//  Created by 이민혁 on 2018. 6. 1..
//  Copyright © 2018년 이민혁. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet var idField: UITextField!
    @IBOutlet var passField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func login(_ sender: Any) {
        loginWithJson()
    }
    
    func loginWithJson() {
        let encoder = JSONEncoder()
        encoder.outputFormatting = [.sortedKeys, .prettyPrinted]
        let loginUser = UserVO(email: idField.text!, password: passField.text!)
        
        let jsonData = try? encoder.encode(loginUser)
        if let jsonData = jsonData, let jsonString = String(data: jsonData, encoding: .utf8) {
            print(jsonString)
        }
        let url = URL(string: "http://localhost:8080/auth/login")!
        var request = URLRequest(url: url)
        request.httpMethod = "post"
        request.setValue("application/json;charset=UTF-8", forHTTPHeaderField: "Content-Type")
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

    
}



