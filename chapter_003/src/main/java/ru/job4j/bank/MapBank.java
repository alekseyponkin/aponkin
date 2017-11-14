package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class MapBank.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 14.11.2017
 */
public class MapBank {
    /**
     * Map contents user and list account of user.
     */
    Map<User, List<Account>> map = new HashMap<>();

    /**
     * Add new user.
     * @param user for add.
     */
    public void addUser(User user) {
        map.put(user, new ArrayList<>());
    }

    /**
     * Delete user.
     * @param user for delete.
     */
    public void deleteUser(User user) {
        map.remove(user);
    }

    /**
     * Add new account to user.
     * @param user which to add the account.
     * @param account for add.
     */
    public void addAccountToUser(User user, Account account) {
        if (this.map.containsKey(user)) {
            List<Account> accountArrayList = this.map.get(user);
            accountArrayList.add(account);
        }
    }

    /**
     * Delete account to user.
     * @param user which to delete the account.
     * @param account for delete.
     */
    public void deleteAccountFromUser(User user, Account account) {
        if (this.map.containsKey(user)) {
            List<Account> accountArrayList = this.map.get(user);
            accountArrayList.remove(account);
        }
    }

    /**
     * Get List account user.
     * @param user who receive an accounts.
     * @return list account user.
     */
    public List<Account> getUserAccount(User user) {
        List<Account> result = new ArrayList<>();
        if (this.map.containsKey(user)) {
            result = this.map.get(user);
        }
        return result;
    }

    /**
     * Transfer money.
     * @param srcUser source user.
     * @param srcAccount source account.
     * @param dstUser destination user.
     * @param dstAccount destination account.
     * @param amount sum from transfer.
     * @return tru if transfer is successful.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = false;
        if (this.map.containsKey(srcUser) && this.map.containsKey(dstUser)) {
            List<Account> accountListSrc = this.map.get(srcUser);
            List<Account> accountListDst = this.map.get(dstUser);
            if (!(accountListSrc.isEmpty() && accountListDst.isEmpty())) {
                Double moneySrc = accountListSrc.get(accountListSrc.indexOf(srcAccount)).value;
                Double moneyDst = accountListDst.get(accountListDst.indexOf(dstAccount)).value;
                if (moneySrc - amount >= 0) {
                    accountListSrc.get(accountListSrc.indexOf(srcAccount)).value = moneySrc - amount;
                    accountListDst.get(accountListDst.indexOf(dstAccount)).value = moneyDst + amount;
                    result = true;
                }
            }
        }
        return result;
    }
}