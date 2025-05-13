package top.resty.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.resty.project.dto.QcReportsDTO;
import top.resty.project.service.QcReportsDTOService;
import top.resty.project.vo.ResultVO;

/**
 * <p>
 *
 * </p>
 *
 * @author : resty
 * @since : 2025年02月16 - 17:11
 */
@RestController
@RequestMapping("/qcReportsDto")
public class QcReportsDTOController {

  @Autowired
  private QcReportsDTOService qcReportsDTOService;
  @PostMapping("/save")
  public ResultVO save(@RequestBody QcReportsDTO qcReportsDTO ){
    return ResultVO.success(qcReportsDTOService.save(qcReportsDTO));
  }

  @DeleteMapping("/delete/{id}")
  public ResultVO delete(@PathVariable String id){
    return ResultVO.success(qcReportsDTOService.delete(id));
  }


  @GetMapping("/get/{id}")
  public ResultVO get(@PathVariable String id){
    return ResultVO.success(qcReportsDTOService.get(id));
  }
}
