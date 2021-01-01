package com.codegym.controlles;

import com.codegym.entity.contract_detail.AttachService;
import com.codegym.entity.contract_detail.ContractDetail;
import com.codegym.service.contract.ContractDetailService;
import com.codegym.service.contract.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@RequestMapping("/contractDetail")
public class ContractDetailController {
    @Autowired
    private ContractDetailService contractDetailService;

    @Autowired
    private ContractService contractService;

    @GetMapping
    public String contractDetailNew(Model model) {
        model.addAttribute("contractList", this.contractService.allContract());
        model.addAttribute("attachServiceSetList", this.contractDetailService.allAttachService());
        model.addAttribute("contractDetail", new ContractDetail());
        return "contract-detail";
    }

    @PostMapping("/createContractDetail")
    public String createContractDetail(RedirectAttributes redirectAttributes, Model model,
                                       @Validated @ModelAttribute ContractDetail contractDetail,
                                       BindingResult bindingResult) {
        new ContractDetail().validate(contractDetail, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("contractList", this.contractService.allContract());
            model.addAttribute("attachServiceSetList", this.contractDetailService.allAttachService());
            return "contract-detail";
        }
        this.contractDetailService.save(contractDetail);
        redirectAttributes.addFlashAttribute("message", "Create New Contract Detail " +
                "Complete !");
        return "redirect:/home";
    }
}
