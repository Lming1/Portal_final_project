package kr.ac.jejunu.workbranch.Model;

import lombok.Data;

@Data
public class ResultStatus {
    private Integer result_code;
    private String message;

    public ResultStatus(int result_code){
        this.result_code = result_code;
    }
}
