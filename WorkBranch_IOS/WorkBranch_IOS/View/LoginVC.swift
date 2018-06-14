//
//  LoginVC.swift
//  WorkBranch_IOS
//
//  Created by 이민혁 on 2018. 6. 14..
//  Copyright © 2018년 이민혁. All rights reserved.
//

import UIKit

class LoginVC: UIViewController {
    let uInfo = UserInfoManager()
    @IBOutlet var idField: UITextField!
    @IBOutlet var passwordField: UITextField!
    
    @IBAction func doLogin(_ sender: Any) {
        uInfo.login(email: idField.text!, password: passwordField.text!, success: {
                self.performSegue(withIdentifier: "SuccessSegue", sender: self)
            }, fail: { (msg) in
                self.alert(msg)
                })
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func backLoginVC(_ segue: UIStoryboardSegue) {
        self.idField.text = ""
        self.passwordField.text = ""
    }
  
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
