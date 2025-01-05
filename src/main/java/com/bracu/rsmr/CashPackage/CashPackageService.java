package com.bracu.rsmr.CashPackage;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.bracu.rsmr.Account.Account;
import com.bracu.rsmr.Account.AccountService;

@Service
public class CashPackageService {

    @Autowired
    private CashPackageRepository cashPackageRepository;

    @Autowired
    @Lazy
    protected AccountService accountService;

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

    public void expirePKG(CashPackage cpkg){
        if(cpkg.getPackageType() == CashPackageType.LOAN){
            Period period = Period.between(cpkg.getExpiryDate(), LocalDate.now());
            accountService.deductBalance(cpkg.getAccount(),cpkg.getAmount()*Math.pow((1+cpkg.getInterest()), period.getMonths()+period.getYears()*12));
        }else{
            cashPackageRepository.delete(cpkg);
        }
    }

    public CashPackage updatePKG(CashPackage cashPackage){
        cashPackage.setPassed(cashPackage.getPassed()+1);
        cashPackageRepository.save(cashPackage);
        return cashPackage;
    }
    
}
