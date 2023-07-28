package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionModel {

    public String from_account;
    public String to_account;
    public int amount;

    public TransactionModel (String from_account, String to_account, int amount) {
        this.from_account = from_account;
        this.to_account = to_account;
        this.amount = amount;
    }


}
