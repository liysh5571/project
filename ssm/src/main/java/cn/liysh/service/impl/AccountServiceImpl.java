package cn.liysh.service.impl;

import cn.liysh.dao.AccountDao;
import cn.liysh.domain.Account;
import cn.liysh.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Override
    public List<Account> findAll() {
        System.out.println("业务层 ，查询所有账户...");
        return accountDao.findAll();
    }

    @Override
    public void saveAccount(Account account) {

        System.out.println("业务层，保存账户...");
        accountDao.saveAccount(account);
    }
}
