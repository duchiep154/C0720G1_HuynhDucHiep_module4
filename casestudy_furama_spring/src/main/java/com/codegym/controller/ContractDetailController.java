package com.codegym.controller;

import com.codegym.entity.contract.Contract;
import com.codegym.entity.contract_detail.AttachService;
import com.codegym.entity.contract_detail.ContractDetail;
import com.codegym.service.contract_detail.ContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping({"/contractDetail"})
public class ContractDetailController {
    @Autowired
    private ContractDetailService xService;

    @GetMapping()
    public String home(Model model, RedirectAttributes redirectAttributes, @PageableDefault(size = 3) Pageable pageable) {
//        List<Contract> contractList = xService.findAll();
//        model.addAttribute("contractList", contractList);
        redirectAttributes.addFlashAttribute("message", "");

        model.addAttribute("contractDetailList", xService.findAll(pageable));

        return "contract_detail/home_contract_detail";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AttachService> attachServiceList = this.xService.findAllAttachService();
        model.addAttribute("attachServiceList", attachServiceList);

        List<Contract> contractList = this.xService.findAllContract();
        model.addAttribute("contractList", contractList);

        model.addAttribute("save", new ContractDetail());
        return "contract_detail/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute(value = "save") ContractDetail contractDetail, BindingResult bindingResult,
                       RedirectAttributes redirect, Model model) {
        // ---------------------------------- Validate ---------------------------------
        new ContractDetail().validate(contractDetail, bindingResult);

        if(bindingResult.hasFieldErrors()){
            model.addAttribute("contractDetailList", xService.findAll());

            List<AttachService> attachServiceList = this.xService.findAllAttachService();
            model.addAttribute("attachServiceList", attachServiceList);

            List<Contract> contractList = this.xService.findAllContract();
            model.addAttribute("contractList", contractList);

            return "contract_detail/create";
        }

        xService.save(contractDetail);
        redirect.addFlashAttribute("message", "Create Successfully");
        return "redirect:/contractDetail";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        List<AttachService> attachServiceList = this.xService.findAllAttachService();
        model.addAttribute("attachServiceList", attachServiceList);

        List<Contract> contractList = this.xService.findAllContract();
        model.addAttribute("contractList", contractList);
        ;

        model.addAttribute("edit", xService.findById(id));
        return "contract_detail/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(value = "edit")ContractDetail contractDetail, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        // ---------------------------------- Validate ---------------------------------
        new ContractDetail().validate(contractDetail, bindingResult);

        if(bindingResult.hasFieldErrors()){
            model.addAttribute("contractDetailList", xService.findAll());

            List<AttachService> attachServiceList = this.xService.findAllAttachService();
            model.addAttribute("attachServiceList", attachServiceList);

            List<Contract> contractList = this.xService.findAllContract();
            model.addAttribute("contractList", contractList);

            return "contract_detail/edit";
        }
        xService.update(contractDetail);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/contractDetail";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, Model model) {
        model.addAttribute("delete", xService.findById(id));
//        return "contract_detail/delete";
        return "contract_detail/home_contract_detail";
    }

//    @PostMapping("/remove")
//    public String remove(ContractDetail contractDetail, RedirectAttributes redirectAttributes) {
//        xService.remove(contractDetail.getId());
//        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
//        return "redirect:/contractDetail";
//    }

    @PostMapping("/remove")
    public String remove(@RequestParam Integer deleteId, RedirectAttributes redirectAttributes) {
        xService.remove(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/contractDetail";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("view", xService.findById(id));
        return "contract_detail/detail_view";
    }
}
