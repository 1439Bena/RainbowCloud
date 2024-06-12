package com.service;

import com.bean.AccountInfo;
import com.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cc
 * 2023-11-27
 */
@Service
public class AccountInfoService {
    @Autowired
    private AccountMapper accountMapper;

    public List<AccountInfo> getAccountInfo(AccountInfo accountInfo, Integer page, Integer limit) {
        return accountMapper.selectAccountsByPage(accountInfo, page, limit);
    }

    public Long getAccountInfoCount(AccountInfo accountInfo) {
        return accountMapper.selectByPageCount(accountInfo);
    }

    public int signUpAccount(AccountInfo accountInfo) {
        return accountMapper.signUpAccount(accountInfo);
    }

    public AccountInfo signInAccount(AccountInfo accountInfo) {
        return accountMapper.signInAccount(accountInfo.getUsername(), accountInfo.getPassword());
    }

    public int updateStatus(String uid, int statu) {
        return accountMapper.updateAstatus(uid, statu);
    }

    public int updateAccountInfo(AccountInfo accountInfo) {
        return accountMapper.updateAccount(accountInfo);
    }

    public int addAccount(AccountInfo accountInfo) {
        return accountMapper.addAccount(accountInfo);
    }

    public AccountInfo selectSomeOneAccount(String uid){
        return accountMapper.selectSomeOneAccount(uid);
    }
}
