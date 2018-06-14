//
//  UserVC.swift
//  WorkBranch_IOS
//
//  Created by 이민혁 on 2018. 6. 14..
//  Copyright © 2018년 이민혁. All rights reserved.
//

import UIKit

class UserVC: UserViewController {
    let uInfo = UserInfoManager()
    @IBAction func doLogout(_ sender: Any) {
        let outAlert = UIAlertController(title: nil, message: "로그아웃 하시겠습니까?", preferredStyle: .alert)
        outAlert.addAction(UIAlertAction(title: "확인", style: .default){ (_) in
            self.uInfo.logout(completion: {
                self.performSegue(withIdentifier: "backLoginVC", sender: self)
            })
        })
        outAlert.addAction(UIAlertAction(title: "취소", style: .cancel))
        self.present(outAlert, animated: false)
        
    }
    
}
