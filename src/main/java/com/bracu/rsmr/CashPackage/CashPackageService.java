package com.bracu.rsmr.CashPackage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.Account.Account;

@Service
public class CashPackageService {

    @Autowired
    private CashPackageRepository cashPackageRepository;

    public CashPackage createCashPackage(Account account,CashPackageType ptype,int interlval ,Long amount){
        CashPackage cpkg = new CashPackage();
        cpkg.setPackageType(ptype);
        cpkg.setAccount(account);
        cpkg.setExpiryDate(cpkg.getDate().plusMonths(interlval));
        cpkg.setAmount(amount);
        cashPackageRepository.save(cpkg);
        return cpkg;
    }

    public CashPackage createLoan(Account account,int interlval,Long amount){
        return createCashPackage(account, CashPackageType.LOAN, interlval, amount);
    }

    public CashPackage createDPS(Account account,int interlval,Long amount){
        return createCashPackage(account, CashPackageType.DPS, interlval, amount);
    }

    public CashPackage createEMI(Account account,int interlval,Long amount){
        return createCashPackage(account, CashPackageType.EMI, interlval, amount);
    }
    
    public List<CashPackage> listEMI(){
        return cashPackageRepository.findByPackageType(CashPackageType.EMI);
    }
    
    public List<CashPackage> listDPS(){
        return cashPackageRepository.findByPackageType(CashPackageType.DPS);
    }
    
    public List<CashPackage> listLOAN(){
        return cashPackageRepository.findByPackageType(CashPackageType.LOAN);
    }
    
}
