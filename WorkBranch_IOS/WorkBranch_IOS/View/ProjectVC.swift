//
//  ProjectVC.swift
//  WorkBranch_IOS
//
//  Created by 이민혁 on 2018. 6. 14..
//  Copyright © 2018년 이민혁. All rights reserved.
//

import Foundation
import UIKit
import Alamofire

class ProjectVC: UITableViewController, UISearchBarDelegate {
    var list = [NSDictionary]()
    
    @IBOutlet var searchBar: UISearchBar!
    
    
    
    override func viewDidLoad() {
        self.projectJson()
        
    }

    @IBAction func addProject(_ sender: Any) {
      
    }
    
    override func viewWillAppear(_ animated: Bool) {
//        self.projectJson()
//        self.tableView.reloadData()
    }

    
    func projectJson() {
        self.list.removeAll()
        let url = "http://172.20.10.3:8080/api/project/list"
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
        let cell = tableView.dequeueReusableCell(withIdentifier: "pCell") as! ProjectCell
        cell.title?.text = obj["projectName"] as? String
        return cell
    }
    
    
//    override func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCellEditingStyle {
//
//        }
//    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
//        let data = self.list[indexPath.row]
        if editingStyle == .delete {
            list.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
            // delete 여기다 입력해야할것..
            }
    }
    
    
  
}


extension BoardVC {
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "segue_board" {
            let cell = sender as! ProjectCell
            let path = self.tableView.indexPath(for: cell)
            let projectInfo = self.list[path!.row]
            let boardVC = segue.destination as? BoardVC
            // 값 전달 더 찾아볼것..
//            boardVC?.pvo = fromObj!
            
        }
    }
}
