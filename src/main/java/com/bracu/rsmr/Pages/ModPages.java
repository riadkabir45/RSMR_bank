package com.bracu.rsmr.Pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.bracu.rsmr.Transaction.TransactionRepository;


@Controller
@RequestMapping("/mod")
public class ModPages {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/backtransfer")
    public String reverseTransfer(Model model) {
        return "transback";
    }
    
    @PostMapping("/backtransfer")
    public String reverseTransferPost(Model model,@RequestParam("transactionId") String id) {
        model.addAttribute("transaction",transactionRepository.findByTransId(id).get());
        return "transback";
    }
}
